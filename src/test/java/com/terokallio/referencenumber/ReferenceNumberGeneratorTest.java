package com.terokallio.referencenumber;

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


    public void testValidateBaseLengthMin() {
        try {
            ReferenceNumberGenerator.generate("1", 1);
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
}
