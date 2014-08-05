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
package dummy.pkg;

import info.simpll.simpletest.Tester;
import info.simpll.simpletest.Tester.Test;

/**
 *
 * @author Bhathiya
 */
public class TheTest {

    @Test(name = "one equals one", repeat = 50000, calcTime = true)
    public void testOneEqOne() {
        Tester.assertTrue(1 == 1);
    }

    @Test(name = "two not equal to one")
    public void testTwoNotOne() {
        Tester.assertTrue(2 != 1);
    }

    @Test
    public void testJustTrueUnnamed() {
        Tester.assertTrue(true);
    }

    @Test(name = "This should fail")
    public void testFail() {
        Tester.assertTrue(false);
    }

    @Test(name = "This should fail")
    public void testFailException() {
        Tester.assertTrue(5 / 0 > 5);
    }

}
