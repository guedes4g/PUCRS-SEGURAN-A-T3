package com.pucrs.t3;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import org.apache.commons.codec.binary.Hex;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple HashFileTest.
 */
public class HashFileTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public HashFileTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( HashFileTest.class );
    }

    /**
     * Test Required By Professor *
     * @throws IOException 
     * @throws NoSuchAlgorithmException 
     */
    public void testApp() throws NoSuchAlgorithmException, IOException
    {
        ArrayList<byte[]> result = HashFile.hash("video05.mp4");
        String expected = "8e423302209494d266a7ab7e1a58ca8502c9bfdaa31dfba70aa8805d20c087bd";
        assertEquals(expected, new String(Hex.encodeHex(result.get(0))));
    }
}
