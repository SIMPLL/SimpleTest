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
