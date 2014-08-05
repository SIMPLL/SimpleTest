
/*
 * The MIT License
 *
 * Copyright 2014 Bhathiya.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package info.simpll.simpletest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *
 * @author Bhathiya
 */
public class Tester {

    private static int successCount = 0;
    private static int testCount = 0;

    //--------------------------------------
    //Test annonation
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public static @interface Test {

        public String name() default "unnamed"; // name of test

        public int repeat() default 1; // repeat count if calcTime==true

        public boolean calcTime() default false; // calculate Time
    }
    //--------------------------------------

    //---------------------------------------
    //Logger
    private static enum Logger {

        INFO(">"), ERROR("Error -->");

        private final String val;

        private Logger(String val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return val;
        }

        public static void log(Logger level, CharSequence message) {
            System.out.printf("%s %s", level, message);
            System.out.println();
        }

        public static void info(CharSequence message) {
            log(INFO, message);
        }

        public static void error(CharSequence message) {
            log(ERROR, message);
        }
    }

    //----------------------------------------
    //----------------------------------------
    //Assertion Mechanism
    public static class SuccessException extends RuntimeException {
    }

    public static class FailException extends RuntimeException {
    }

    /**
     * state that a scenario is confidently true
     *
     * @param trueScenario this should be true to be success
     */
    public static void assertTrue(boolean trueScenario) {
        if (trueScenario) {
            throw new SuccessException();
        } else {
            throw new FailException();
        }
    }

    //-----------------------------------------
    /**
     * execute a single test case
     *
     * @param test test annotation object
     * @param method method
     * @param object instance
     * @return true only if SuccessException in InvocationTargetException
     */
    private static boolean executeTest(Test test, Method method, Object object) {
        boolean ret = false;
        Logger.info("------------------------");
        try {
            Logger.info("Test case:" + test.name());
            Logger.info("Method:" + method.getName());
            Logger.info("Class:" + object.getClass().getName());
            method.invoke(object);
        } catch (IllegalAccessException | IllegalArgumentException ex) {
            Logger.error("Problem when calling method : " + method.getName());
            ex.printStackTrace();
        } catch (InvocationTargetException ex) {
            if (ex.getCause() instanceof SuccessException) {
                ret = true;
            } else {
                ex.getCause().printStackTrace();
            }
        }

        if (ret) {
            Logger.info("Test case success!");
            //now calculate time if needed
            if (test.calcTime()) {
                calculateTime(test, method, object);
            }
        } else {
            Logger.info("Test case failed!");
        }

        Logger.info("------------------------\n");
        return ret;
    }

    /**
     * calculate time by running a test n times
     *
     * @param test test annotation object
     * @param method method
     * @param object instance
     */
    private static void calculateTime(Test test, Method method, Object object) {

        if (test.repeat() <= 0) {
            Logger.error("Invalid repeat count");
            return;
        }
        Logger.info("");
        Logger.info("Time test started");
        Logger.info(String.format("Repeat count:%d", test.repeat()));
        long start = System.currentTimeMillis();
        for (int i = 0; i < test.repeat(); i++) {

            try {
                method.invoke(object);
            } catch (IllegalAccessException | IllegalArgumentException ex) {
                Logger.error("Problem when calling method : " + method.
                        getName());
                ex.printStackTrace();
                return;
            } catch (InvocationTargetException ex) {
                if (!(ex.getCause() instanceof SuccessException)) {
                    //not success
                    Logger.error("Test failed, time testing halted");
                    ex.getCause().printStackTrace();
                    return;
                }
            }
        }
        Logger.info(String.format("Time elapsed:%d mili seconds", System.
                currentTimeMillis() - start));

    }

    private static void printHelp() {
        Logger.info("Welcome to Simple Test");
        Logger.info("usage: java info.simpll.simpletest.Tester"
                + " your.package.name.YourClass");
    }

    public static void main(String[] args) {
        if (args == null || args.length != 1) {
            printHelp();
            return;
        }

        try {
            Class clazz = Class.forName(args[0]);
            Object object = clazz.newInstance();
            Method[] methods = clazz.getMethods();

            for (Method method : methods) {
                Test test = method.getAnnotation(Test.class);
                if (test != null) {
                    //we can test it
                    testCount++;
                    if (executeTest(test, method, object)) {
                        successCount++;
                    }
                }
            }

            Logger.info(String.format("Tests %d out of %d passed", successCount,
                    testCount));
        } catch (ClassNotFoundException | InstantiationException |
                IllegalAccessException ex) {
            Logger.error("Class instantiation faild, see if package "
                    + " name and class name are correct");
        }
    }
}
