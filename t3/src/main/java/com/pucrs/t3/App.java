package com.pucrs.t3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import org.apache.commons.codec.binary.Hex;

public class App 
{
    public static void main( String[] args ) throws IOException, NoSuchAlgorithmException
    {
    	String filePath = args[0];
    	ArrayList<byte[]> blocks = HashFile.hash(filePath);
    	byte[] b = blocks.get(0);
    	System.out.println("H0 = " + new String(Hex.encodeHex(b)));	
    	BufferedWriter writer = new BufferedWriter(new FileWriter(filePath+".hex"));
    	for(byte[] block : blocks)
    		writer.write(new String(Hex.encodeHex(block)) + '\n');
    	
    	writer.close();
    }
}
