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
public class SplitTest {

    private final static String PART = "part";
    private final static char DELIMITER = ' ';

    private final static String TEST = createTestStr();

    private static String createTestStr() {
        StringBuilder sb = new StringBuilder(PART);
        for (int i = 1; i < 200000; i++) {
            sb.append(DELIMITER);
            sb.append(PART);
        }
        return sb.toString();
    }

    @Test(name = "splitOriginal", calcTime = true,repeat = 100)
    public void testOriginal() {
        SplitFunctions.splitOriginal(TEST, DELIMITER);
        Tester.assertTrue(true);
    }

    @Test(name = "splitUsingIndexOf", calcTime = true,repeat = 100)
    public void testIndexOf() {
        SplitFunctions.splitUsingIndexOf(TEST, DELIMITER);
        Tester.assertTrue(true);
    }

    @Test(name = "splitStrBuilderSetLenZero", calcTime = true,repeat = 100)
    public void testStrBuilderSetLenZero() {
        SplitFunctions.splitStrBuilderSetLenZero(TEST, DELIMITER);
        Tester.assertTrue(true);
    }

    @Test(name = "splitSubString", calcTime = true,repeat = 100)
    public void testSubString() {
        SplitFunctions.splitSubString(TEST, DELIMITER);
        Tester.assertTrue(true);
    }
    //speed test
}
