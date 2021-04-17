// Dump.java - Useful dump tool to visualize any files, especially binary files.

import java.io.*;

public class Dump {
    // hexByte -- print a byte in hex
    private static void hexByte(byte b) {
        System.out.print( Integer.toString((b >>> 4) & 0x0F, 16) ); // msNibble
        System.out.print( Integer.toString( b        & 0x0F, 16) ); // lsNibble
    }
    // hexWord -- print a word in hex
    private static void hexWord(short word) {
        hexByte((byte)(word>>>8)); /* MSB - Most  Significant Byte */
        hexByte((byte) word);      /* LSB - Least Significant Byte */
    }

    private static boolean isPrint(char c) {
        return  (c >= ' ' && c < 0x7f);
    }

    public static void main(String args[]) throws IOException {
        File  file;
        int i, buffer[] = new int[16];
        long offset = 0;

        if (args.length < 2 || args.length > 3) {
            System.out.println("Error: wrong number of arguments");
            System.out.println("Use: dump file [starting offset in hex]\n");
            System.exit(1);
        }
        file = new File(args[0]);              // open file for binary read.
        if ( !file.canRead() ) {
            System.out.println("Can't open file '" + args[0] + "'");
            System.exit(1);
        }

        FileInputStream f = new FileInputStream(file);
        while (true) {
            for (i = 0; i < 16; i++)
                buffer[i] = f.read();

            if (buffer[0] == -1) break; // done.

            hexWord((short)offset); System.out.print( ": " );
            for (i = 0; i < 16; i++) {
                if (buffer[i] != -1) {
                    hexByte((byte)buffer[i]);
                    System.out.print( " ");
                } else
                    System.out.print("   ");
            }
            System.out.print("   ");
            for (i = 0; i < 16; i++) {
                if (buffer[i] != -1) {
                    if ( !isPrint((char)buffer[i]) ) buffer[i] = '.';
                        System.out.print((char)buffer[i]);
                } else
                    System.out.print(' ');
            }
            System.out.println();
            offset += 16;
        }
    }
}
