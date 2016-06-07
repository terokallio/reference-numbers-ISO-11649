package com.terokallio.referencenumbers;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for ReferenceNumberGenerator
 */
public class ReferenceNumberGeneratorTest extends TestCase {

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public ReferenceNumberGeneratorTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( ReferenceNumberGeneratorTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testReferenceNumberGenerator()
    {
        assertTrue( true );
    }

    public void testValidateNullBase() {
        try {
            ReferenceNumberGenerator.generate(null, 1);
        } catch (InvalidAlgorithmParameterException e) {
            assertTrue(e.getMessage().contains("base length MUST be between 3 and 19 characters"));
            return;
        }
        fail();
    }

    public void testValidateEmptyBase() {
        try {
            ReferenceNumberGenerator.generate("", 1);
        } catch (InvalidAlgorithmParameterException e) {
            assertTrue(e.getMessage().contains("base length MUST be between 3 and 19 characters"));
            return;
        }
        fail();
    }

    public void testValidateNotANumber() {
        try {
            ReferenceNumberGenerator.generate("1231A12", 10);
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
            result = ReferenceNumberGenerator.generate("111", amount);
        } catch (InvalidAlgorithmParameterException e) {
            fail();
        }
        assertEquals(0, result.size());
    }

    public void testZeroAmount() {
        int amount = 0;
        List<String> result = new ArrayList<String>();
        try {
            result = ReferenceNumberGenerator.generate("111", amount);
        } catch (InvalidAlgorithmParameterException e) {
            fail();
        }
        assertEquals(0, result.size());
    }


    public void testValidateBaseLengthMin() {
        try {
            ReferenceNumberGenerator.generate("12", 1);
        } catch (InvalidAlgorithmParameterException e) {
            assertTrue(e.getMessage().contains("base length MUST be between 3 and 19 characters"));
            return;
        }
        fail();
    }

    public void testValidateBaseLengthMin2() {
        List<String> result = new ArrayList<String>();
        try {
            result = ReferenceNumberGenerator.generate("123", 1);
        } catch (InvalidAlgorithmParameterException e) {
           fail();
        }
        assertTrue(result.get(0).length() == 4);
    }

    public void testValidateBaseLengthMax() {
        List<String> result = new ArrayList<String>();
        try {
            result = ReferenceNumberGenerator.generate("1234567890123456789", 1);
        } catch (InvalidAlgorithmParameterException e) {
            fail();
        }
        assertTrue(result.get(0).length() == 20);
    }

    public void testValidateBaseLengthMax2() {
        try {
            ReferenceNumberGenerator.generate("12345678901234567890", 1);
        } catch (InvalidAlgorithmParameterException e) {
            assertTrue(e.getMessage().contains("base length MUST be between 3 and 19 characters"));
            return;
        }
        fail();
    }

    public void testGenerateTwentyValidRefCodes() {
        List<String> result = new ArrayList<String>();
        try {
            result = ReferenceNumberGenerator.generate("123123", 20);
        } catch (InvalidAlgorithmParameterException e) {
           fail("Failed to generate ref codes.");
        }
        assertEquals(20, result.size());
        assertEquals("1231234", result.get(0));
        assertEquals("1231247", result.get(1));
        assertEquals("1231250", result.get(2));
        assertEquals("1231263", result.get(3));
        assertEquals("1231276", result.get(4));
        assertEquals("1231289", result.get(5));
        assertEquals("1231292", result.get(6));
        assertEquals("1231302", result.get(7));
        assertEquals("1231315", result.get(8));
        assertEquals("1231328", result.get(9));
        assertEquals("1231328", result.get(9));
        assertEquals("1231331", result.get(10));
        assertEquals("1231344", result.get(11));
        assertEquals("1231357", result.get(12));
        assertEquals("1231360", result.get(13));
        assertEquals("1231373", result.get(14));
        assertEquals("1231386", result.get(15));
        assertEquals("1231399", result.get(16));
        assertEquals("1231409", result.get(17));
        assertEquals("1231412", result.get(18));
        assertEquals("1231425", result.get(19));
    }

    public void testGenerateTwentyValidRefCodes2() {
        List<String> result = new ArrayList<String>();
        try {
            result = ReferenceNumberGenerator.generate("999", 20);
        } catch (InvalidAlgorithmParameterException e) {
            fail("Failed to generate ref codes.");
        }
        assertEquals(20, result.size());
        assertEquals("9991", result.get(0));
        assertEquals("10003", result.get(1));
        assertEquals("10016", result.get(2));
        assertEquals("10029", result.get(3));
        assertEquals("10032", result.get(4));
        assertEquals("10045", result.get(5));
        assertEquals("10058", result.get(6));
        assertEquals("10061", result.get(7));
        assertEquals("10074", result.get(8));
        assertEquals("10087", result.get(9));
        assertEquals("10090", result.get(10));
        assertEquals("10100", result.get(11));
        assertEquals("10113", result.get(12));
        assertEquals("10126", result.get(13));
        assertEquals("10139", result.get(14));
        assertEquals("10142", result.get(15));
        assertEquals("10155", result.get(16));
        assertEquals("10168", result.get(17));
        assertEquals("10171", result.get(18));
        assertEquals("10184", result.get(19));
    }
}
