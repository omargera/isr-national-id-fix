package com.omar.nationalid;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class NationalIdFixerTest {

    @Test public void testSomeLibraryMethod() {
        NationalIdFixer classUnderTest = new NationalIdFixer();
        assertTrue("someLibraryMethod should return 'true'", classUnderTest.someLibraryMethod());
    }

    @Test(expected = AssertionError.class)
    public void testShortId() {
        List<Integer> integerList = Arrays.asList(1, 2, 3);
        NationalIdFixer.fixId(integerList);
    }

    @Test(expected = AssertionError.class)
    public void testLongId() {
        List<Integer> integerList = Arrays.asList(0, 1, 2, 3, 0, 1, 2, 3, 9, 8);
        NationalIdFixer.fixId(integerList);
    }

    @Test
    public void testInvalidId() {
        List<Integer> integerList = Arrays.asList(0, 1, 2, 3, 0, 1, 2, 3, 8);
        NationalIdFixer.fixId(integerList);
    }


    @Test
    public void testValidId() {
        List<Integer> inputIntegerList = Arrays.asList(1,2,3,4,5,6,7,8,2);
        List<Integer> expctIntegerList = Arrays.asList(1,2,3,4,5,6,7,8,2);
        NationalIdFixer.fixId(inputIntegerList);
        Assert.assertEquals(expctIntegerList, inputIntegerList);
    }

    @Test
    public void testFixDigit0ZeroMissingMul2() {
        List<Integer> inputIntegerList = Arrays.asList(3,null,0,4,5,4,5,2,7);
        List<Integer> expctIntegerList = Arrays.asList(3,0,0,4,5,4,5,2,7);
        NationalIdFixer.fixId(inputIntegerList);
        Assert.assertEquals(expctIntegerList, inputIntegerList);
    }

    @Test
    public void testFixDigit0ZeroMissingMul1() {
        List<Integer> inputIntegerList = Arrays.asList(3,0,null,4,5,4,5,2,7);
        List<Integer> expctIntegerList = Arrays.asList(3,0,0,4,5,4,5,2,7);
        NationalIdFixer.fixId(inputIntegerList);
        Assert.assertEquals(expctIntegerList, inputIntegerList);
    }

    @Test
    public void testFixDigit0() {
        List<Integer> inputIntegerList = Arrays.asList(null,2,3,4,5,6,7,8,2);
        List<Integer> expctIntegerList = Arrays.asList(1,2,3,4,5,6,7,8,2);
        NationalIdFixer.fixId(inputIntegerList);
        Assert.assertEquals(expctIntegerList, inputIntegerList);
    }

    @Test
    public void testFixDigit1() {
        List<Integer> inputIntegerList = Arrays.asList(1,null,3,4,5,6,7,8,2);
        List<Integer> expctIntegerList = Arrays.asList(1,2,3,4,5,6,7,8,2);
        NationalIdFixer.fixId(inputIntegerList);
        Assert.assertEquals(expctIntegerList, inputIntegerList);
    }

    @Test
    public void testFixDigit2() {
        List<Integer> inputIntegerList = Arrays.asList(1,2,null,4,5,6,7,8,2);
        List<Integer> expctIntegerList = Arrays.asList(1,2,3,4,5,6,7,8,2);
        NationalIdFixer.fixId(inputIntegerList);
        Assert.assertEquals(expctIntegerList, inputIntegerList);
    }

    @Test
    public void testFixDigit3() {
        List<Integer> inputIntegerList = Arrays.asList(1,2,3,null,5,6,7,8,2);
        List<Integer> expctIntegerList = Arrays.asList(1,2,3,4,5,6,7,8,2);
        NationalIdFixer.fixId(inputIntegerList);
        Assert.assertEquals(expctIntegerList, inputIntegerList);
    }

    @Test
    public void testFixDigit4() {
        List<Integer> inputIntegerList = Arrays.asList(1,2,3,4,null,6,7,8,2);
        List<Integer> expctIntegerList = Arrays.asList(1,2,3,4,5,6,7,8,2);
        NationalIdFixer.fixId(inputIntegerList);
        Assert.assertEquals(expctIntegerList, inputIntegerList);
    }

    @Test
    public void testFixDigit5() {
        List<Integer> inputIntegerList = Arrays.asList(1,2,3,4,5,null,7,8,2);
        List<Integer> expctIntegerList = Arrays.asList(1,2,3,4,5,6,7,8,2);
        NationalIdFixer.fixId(inputIntegerList);
        Assert.assertEquals(expctIntegerList, inputIntegerList);
    }

    @Test
    public void testFixDigit6() {
        List<Integer> inputIntegerList = Arrays.asList(1,2,3,4,5,6,null,8,2);
        List<Integer> expctIntegerList = Arrays.asList(1,2,3,4,5,6,7,8,2);
        NationalIdFixer.fixId(inputIntegerList);
        Assert.assertEquals(expctIntegerList, inputIntegerList);
    }

    @Test
    public void testFixDigit7() {
        List<Integer> inputIntegerList = Arrays.asList(1,2,3,4,5,6,7,null,2);
        List<Integer> expctIntegerList = Arrays.asList(1,2,3,4,5,6,7,8,2);
        NationalIdFixer.fixId(inputIntegerList);
        Assert.assertEquals(expctIntegerList, inputIntegerList);
    }

    @Test
    public void testFixDigit8() {
        List<Integer> inputIntegerList = Arrays.asList(1,2,3,4,5,6,7,8,null);
        List<Integer> expctIntegerList = Arrays.asList(1,2,3,4,5,6,7,8,2);
        NationalIdFixer.fixId(inputIntegerList);
        Assert.assertEquals(expctIntegerList, inputIntegerList);
    }



    @Test
    public void testEqualLists() {
        List<Integer> integerList1 = Arrays.asList(0, 1, 2, 3, 0, 1, 2, 3);
        List<Integer> integerList2 = Arrays.asList(0, 1, 2, 3, 0, 1, 2, 3);
        assert integerList1.equals(integerList2);
    }

    @Test
    public void testValidationWithCorrectId() {
        List<Integer> integerList1 = Arrays.asList(1,2,3,4,5,6,7,8,2);
        Assert.assertTrue(NationalIdFixer.validateNationalId(integerList1));
    }

    @Test
    public void testValidationWithIncorrectId() {
        List<Integer> integerList1 = Arrays.asList(1,2,3,4,5,6,7,8,1);
        Assert.assertFalse(NationalIdFixer.validateNationalId(integerList1));
    }
}
