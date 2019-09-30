package com.pucrs.t3;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.commons.lang3.ArrayUtils;

public class HashFile {

	public static ArrayList<byte[]> hash(String filePath) throws IOException, NoSuchAlgorithmException {
    	byte[] prevShaBuffer, currShaBuffer = null;
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
    	ArrayList<byte[]> listOfBuffers = readFile(filePath);
    	ArrayList<byte[]> result = new ArrayList<byte[]>(listOfBuffers.size());

    	currShaBuffer = digest.digest(listOfBuffers.get(listOfBuffers.size()-1));
    	
        for(int i = listOfBuffers.size() - 2; i >= 0; i --) {
        	prevShaBuffer = currShaBuffer;
        	byte[] buffer = ArrayUtils.addAll(listOfBuffers.get(i), prevShaBuffer);
        	result.add(buffer);
            currShaBuffer = digest.digest(buffer);
        }
        
        result.add(currShaBuffer);
        Collections.reverse(result);
        return result;
	}
	
	private static ArrayList<byte[]> readFile(String filePath) throws IOException, NoSuchAlgorithmException {
    	int BUFFERSIZE = 1024;
        FileInputStream fin = new FileInputStream(new File(filePath));
    	ArrayList<byte[]> listOfBuffers = new ArrayList<byte[]>();

        while(fin.available() > 0) {
        	BUFFERSIZE = fin.available() < 1024 ? fin.available() : BUFFERSIZE;
        	byte[] buffer = new byte[BUFFERSIZE];
        	fin.read(buffer);
        	listOfBuffers.add(buffer);
        }
        fin.close();
        return listOfBuffers;
	}
}
