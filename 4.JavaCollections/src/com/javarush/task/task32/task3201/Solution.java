package com.javarush.task.task32.task3201;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/*
Запись в существующий файл
*/
public class Solution {
    public static void main(String... args) throws IOException {
        String fileName = args[0];
        long Number = Long.parseLong(args[1]);
        String text = args[2];
        RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
        if (Number>raf.length()){
            raf.seek(raf.length());
        }
        else if (Number<0){
            raf.seek(0);
        }
        else {
            raf.seek(Number);
        }
        raf.write(text.getBytes());
    }
}
