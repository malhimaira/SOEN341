package com.example.soen341;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class AssemblySourceReader {

    public static void main(String[] args) throws IOException {
        String asmPath = "/Users/marina/AndroidStudioProjects/Soen341/soen341/TestInherentMnemonics.asm";
        File asmSourceFile = new File(asmPath);

        InputStream inputStream = null;
        readAsmFile(asmSourceFile);

        inputStream = readAsmFile(asmSourceFile);

    }

    public static InputStream readAsmFile(File file) {

        String[] asmFileArray = null;

        try {
            InputStream inputStream = new FileInputStream(file);
            return inputStream;

        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        /* catch (IOException e) {
            e.printStackTrace();
        } */
        return null;
    }
}