import java.io.File;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import java.util.Scanner;

public class pa01 {

    public static void main(String[] args) {

        String keyText = null;
        String plainTextSorted = null;
        String keyTextPadded = null;

        System.out.println("/*=============================================================================\n" +
        "| Assignment: pa01 - Encrypting a plaintext file using the Vigenere cipher\n" +
        "|\n" +
        "| Author: Austin Brown\n" +
        "| Language: Java\n" +
        "|\n" + 
        "| To Compile: javac pa01.java\n" + 
        "|\n" + 
        "| To Execute: java -> java pa01 kX.txt pX.txt\n" +
        "| where kX.txt is the keytext file\n" + 
        "| and pX.txt is plaintext file\n" +
        "|\n" +
        "| Note: All input files are simple 8 bit ASCII input\n" +
        "|\n" +
        "| Class: CIS3360 - Security in Computing - Spring 2022\n" +
        "| Instructor: McAlpin\n" +
        "| Due Date: Feb 28\n" +
        "|\n" +
        "+=============================================================================*/\n");

        File file0 = new File(args[0]);
        try {
            BufferedReader br0 = new BufferedReader(new FileReader(file0));
            // System.out.println("file opened successfully?\n");
            Scanner sc0 = new Scanner(new FileInputStream(file0));
            ArrayList<String> arr0 = new ArrayList<String>();
            while (sc0.hasNext()) {
                arr0.add(sc0.next());
            }
            sc0.close();
            br0.close();

            keyText = String.join("", arr0);
            keyText = keyText.toLowerCase();
            keyText = keyText.replaceAll("[^a-z]", "");

            //System.out.println("cipher:\n" + keyText);

        } catch (Exception e) {
            e.printStackTrace();
        }

        File file1 = new File(args[1]);
        try {
            BufferedReader br1 = new BufferedReader(new FileReader(file1));
            // System.out.println("file opened successfully?\n");
            Scanner sc1 = new Scanner(new FileInputStream(file1));
            ArrayList<String> arr1 = new ArrayList<String>();
            while (sc1.hasNext()) {
                arr1.add(sc1.next());
            }
            sc1.close();
            br1.close();

            // System.out.println(arr1);

            plainTextSorted = String.join("", arr1);
            plainTextSorted = plainTextSorted.toLowerCase();

            // System.out.println(plainTextSorted);

            plainTextSorted = plainTextSorted.replaceAll("[^a-z]", "");

            if (plainTextSorted.length() < 512) {
                int padAmount = 512 - plainTextSorted.length();
                String padChar = "x";

                plainTextSorted = plainTextSorted + padChar.repeat(padAmount);
                // System.out.println("plaintext:\n" + plainTextSorted + "\n");
            } else {
                // System.out.println("plaintext:\n" + plainTextSorted + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        int ptsLength = plainTextSorted.length();
        int ktLength = keyText.length();
        char extendKey[] = new char[ptsLength];
        StringBuffer newSb = new StringBuffer();
        for (int i = 0, j = 0; i < ptsLength; ++i, ++j) {
            if (j == ktLength) {
                j = 0;
            }
            extendKey[i] = keyText.charAt(j);
        }

        for (int i = 0; i < extendKey.length; i++) {
            newSb.append(extendKey[i]);
        }
        keyTextPadded = new String(extendKey);
        keyTextPadded = keyTextPadded.replaceAll(".{80}(?=.)", "$0\n");
        plainTextSorted = plainTextSorted.replaceAll(".{80}(?=.)", "$0\n");

        System.out.println("Cipher:\n" + keyTextPadded);
        System.out.println("Plaintext:\n" + plainTextSorted);

        char cipherValue[] = new char [512];
        String cipherText = null;
        for (int i = 0; i < 512; i++) {
            char keyC = keyTextPadded.charAt(i);
            char plainTextC = plainTextSorted.charAt(i);
            int kAscii = (int) keyC - 'a';
            int ptAscii = (int) plainTextC - 'a';
            cipherValue[i] = (char) (((kAscii + ptAscii) % 26) + 'a');

        }

        StringBuffer finalSb = new StringBuffer();
        for (int i = 0; i < cipherValue.length; i++) {
            finalSb.append(cipherValue[i]);
        }
        cipherText = new String(cipherValue);
        cipherText = cipherText.toLowerCase();
        cipherText = cipherText.replaceAll(".{80}(?=.)", "$0\n");
        System.out.println("Ciphertext:\n" + cipherText + "\n");
        
        

        System.out.println("/*=============================================================================\n" +
        "| I Austin Brown (au644881) affirm that this program is\n" +
        "| entirely my own work and that I have neither developed my code together with\n" +
        "| any another person, nor copied any code from any other person, nor permitted\n" +
        "| my code to be copied or otherwise used by any other person, nor have I\n" +
        "| copied, modified, or otherwise used programs created by others. I acknowledge\n" +
        "| that any violation of the above terms will be treated as academic dishonesty.\n" +
        "+=============================================================================*/\n");

    }



}
