package com.example.soen341;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static com.example.soen341.AssemblySourceReader.readAsmFile;
import static org.junit.Assert.assertEquals;

public class MyUnitTest {
    @Test
    public void testReader() throws IOException {
        // Wht if its not the right file

        String asmPath = "/Users/marina/AndroidStudioProjects/Soen341/soen341/TestInherentMnemonics.asm";
        File asmSourceFile = new File(asmPath);

        InputStream inputStream = null;
        inputStream = readAsmFile(asmSourceFile);

        String firstString = " halt";

        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

        StringBuilder sb = new StringBuilder();
        String line = br.readLine();
        System.out.println(line);

        assertEquals(firstString, line);
    }
}
