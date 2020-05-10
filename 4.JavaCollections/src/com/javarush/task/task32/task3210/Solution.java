package com.javarush.task.task32.task3210;

/* 
Используем RandomAccessFile
*/

import java.io.IOException;
import java.io.RandomAccessFile;

public class Solution {
    public static void main(String... args) throws IOException {
        String fileName = args[0];
        long Number = Long.parseLong(args[1]);
        String text = args[2];
        RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
        raf.seek(Number);
        byte[]tmp= new byte[text.length()];
        raf.read(tmp, 0, text.length());
        String fileText = new String(tmp);
        raf.seek(raf.length());
        if (text.equals(fileText)){
            raf.write("true".getBytes());
        }
        else {
            raf.write("false".getBytes());
        }
    }
}
