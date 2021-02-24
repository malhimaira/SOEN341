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

        String content = null;
        readAsmFile(asmSourceFile);

        try {
            content = readAsmFile(asmSourceFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Now we can parse it!

    }

    public static String readAsmFile(File file) throws IOException {

        StringBuilder sb = new StringBuilder();
        String[] asmFileArray = null;

        InputStream inputStream = new FileInputStream(file);
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line + System.lineSeparator());
        }

        return sb.toString();
        // Testing if file was correctly input
        // System.out.println(sb.toString());

    }

}