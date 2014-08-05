SimpleTest
==========

Single Class Unit Test Framework for Java


**This is yet in alpha state.**

<h1>How to use </h1>

 1. Mark test case methods of a test case class with `@Test` annonation.
 2. Compile everything including test case class and `Tester` class.
 3. Run using `java info.simpll.simpletest.Tester your.package.name.YourClass` (add any related jars,etc. to classpath)

<h1>Sample Test Case</h1>

```java
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
```

Sample should be executed as `java info.simpll.simpletest.Tester dummy.pkg.TheTest`


This should print

```
> ------------------------
> Test case:unnamed
> Method:testJustTrueUnnamed
> Class:dummy.pkg.TheTest
> Test case success!
> ------------------------

> ------------------------
> Test case:This should fail
> Method:testFailException
> Class:dummy.pkg.TheTest
java.lang.ArithmeticException: / by zero
	at dummy.pkg.TheTest.testFailException(TheTest.java:57)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:483)
	at info.simpll.simpletest.Tester.executeTest(Tester.java:126)
	at info.simpll.simpletest.Tester.main(Tester.java:218)
> Test case failed!
> ------------------------

> ------------------------
> Test case:This should fail
> Method:testFail
> Class:dummy.pkg.TheTest
> Test case failed!
> ------------------------

> ------------------------
> Test case:two not equal to one
> Method:testTwoNotOne
> Class:dummy.pkg.TheTest
> Test case success!
> ------------------------

> ------------------------
> Test case:one equals one
> Method:testOneEqOne
> Class:dummy.pkg.TheTest
> Test case success!
> 
> Time test started
> Repeat count:50000
> Time elapsed:259 mili seconds
> ------------------------

> Tests 3 out of 5 passed
```
