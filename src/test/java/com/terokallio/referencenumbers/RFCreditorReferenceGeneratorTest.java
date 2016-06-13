package com.terokallio.referencenumbers;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by tero.kallio on 13/06/16.
 */
public class RFCreditorReferenceGeneratorTest extends TestCase {

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public RFCreditorReferenceGeneratorTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( RFCreditorReferenceGeneratorTest.class );
    }

    public void testValidateNullBase() {
        try {
            RFCreditorReferenceGenerator.generate(null, 1);
        } catch (InvalidAlgorithmParameterException e) {

            assertTrue(e.getMessage().contains("base length MUST be between 3 and 19 characters"));
            return;
        }
        fail();
    }

    public void testValidateEmptyBase() {
        try {
            RFCreditorReferenceGenerator.generate("", 1);
        } catch (InvalidAlgorithmParameterException e) {
            assertTrue(e.getMessage().contains("base length MUST be between 3 and 19 characters"));
            return;
        }
        fail();
    }

    public void testValidateNotANumber() {
        try {
            RFCreditorReferenceGenerator.generate("1231A12", 10);
        } catch (InvalidAlgorithmParameterException e) {
            assertTrue(e.getMessage().contains("base MUST be a number"));
            return;
        }
        fail();
    }

    public void testNegativeAmount() {
        int amount = -1;
        List<String> result = new ArrayList<String>();
        try {
            result = RFCreditorReferenceGenerator.generate("111", amount);
        } catch (InvalidAlgorithmParameterException e) {
            fail();
        }
        assertEquals(0, result.size());
    }

    public void testZeroAmount() {
        int amount = 0;
        List<String> result = new ArrayList<String>();
        try {
            result = RFCreditorReferenceGenerator.generate("111", amount);
        } catch (InvalidAlgorithmParameterException e) {
            fail();
        }
        assertEquals(0, result.size());
    }

    public void testValidateBaseLengthMin() {
        try {
            RFCreditorReferenceGenerator.generate("12", 1);
        } catch (InvalidAlgorithmParameterException e) {
            assertTrue(e.getMessage().contains("base length MUST be between 3 and 19 characters"));
            return;
        }
        fail();
    }

    public void testValidateBaseLengthMin2() {
        List<String> result = new ArrayList<String>();
        try {
            result = RFCreditorReferenceGenerator.generate("123", 1);
        } catch (InvalidAlgorithmParameterException e) {
            fail();
        }
        assertTrue(result.get(0).length() == 8);
    }

    public void testValidateBaseLengthMax() {
        List<String> result = new ArrayList<String>();
        try {
            result = RFCreditorReferenceGenerator.generate("1234567890123456789", 1);
        } catch (InvalidAlgorithmParameterException e) {
            fail();
        }
        assertTrue(result.get(0).length() == 24);
    }

    public void testValidateBaseLengthMax2() {
        try {
            RFCreditorReferenceGenerator.generate("12345678901234567890", 1);
        } catch (InvalidAlgorithmParameterException e) {
            assertTrue(e.getMessage().contains("base length MUST be between 3 and 19 characters"));
            return;
        }
        fail();
    }

    public void testGenerateMillionRefCodes() {
        List<String> result = new ArrayList<String>();
        try {
            result = RFCreditorReferenceGenerator.generate("999", 1000000);
        } catch (InvalidAlgorithmParameterException e) {
            fail("Failed to generate ref codes.");
        }
        assertEquals(1000000, result.size());
    }

    public void testGenerateTwentyValidRFCreditorCodes() {
        List<String> result = new ArrayList<String>();
        try {
            result = RFCreditorReferenceGenerator.generate("1234567890123456789", 20);
        } catch (InvalidAlgorithmParameterException e) {
            fail("Failed to generate ref codes.");
        }
        assertEquals(20, result.size());
        assertEquals("RF0912345678901234567894", result.get(0));
        assertEquals("RF3012345678901234567904", result.get(1));
        assertEquals("RF6712345678901234567917", result.get(2));
        assertEquals("RF8312345678901234567920", result.get(3));
        assertEquals("RF2312345678901234567933", result.get(4));
        assertEquals("RF6012345678901234567946", result.get(5));
        assertEquals("RF9712345678901234567959", result.get(6));
        assertEquals("RF1612345678901234567962", result.get(7));
        assertEquals("RF5312345678901234567975", result.get(8));
        assertEquals("RF9012345678901234567988", result.get(9));
        assertEquals("RF0912345678901234567991", result.get(10));
        assertEquals("RF5712345678901234568000", result.get(11));
        assertEquals("RF9412345678901234568013", result.get(12));
        assertEquals("RF3412345678901234568026", result.get(13));
        assertEquals("RF7112345678901234568039", result.get(14));
        assertEquals("RF8712345678901234568042", result.get(15));
        assertEquals("RF2712345678901234568055", result.get(16));
        assertEquals("RF6412345678901234568068", result.get(17));
        assertEquals("RF8012345678901234568071", result.get(18));
        assertEquals("RF2012345678901234568084", result.get(19));
    }

    public void testGenerateTwentyValidRFCreditorCodes2() {
        List<String> result = new ArrayList<String>();
        try {
            result = RFCreditorReferenceGenerator.generate("123", 20);
        } catch (InvalidAlgorithmParameterException e) {
            fail("Failed to generate ref codes.");
        }
        assertEquals(20, result.size());
        assertEquals("RF111232", result.get(0));
        assertEquals("RF481245", result.get(1));
        assertEquals("RF851258", result.get(2));
        assertEquals("RF041261", result.get(3));
        assertEquals("RF411274", result.get(4));
        assertEquals("RF781287", result.get(5));
        assertEquals("RF941290", result.get(6));
        assertEquals("RF181300", result.get(7));
        assertEquals("RF551313", result.get(8));
        assertEquals("RF921326", result.get(9));
        assertEquals("RF321339", result.get(10));
        assertEquals("RF481342", result.get(11));
        assertEquals("RF851355", result.get(12));
        assertEquals("RF251368", result.get(13));
        assertEquals("RF411371", result.get(14));
        assertEquals("RF781384", result.get(15));
        assertEquals("RF181397", result.get(16));
        assertEquals("RF391407", result.get(17));
        assertEquals("RF551410", result.get(18));
        assertEquals("RF921423", result.get(19));
    }

}
