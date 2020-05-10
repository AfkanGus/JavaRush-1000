package com.javarush.task.task31.task3106;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.zip.ZipInputStream;

/*
Разархивируем файл
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        String resultName = args[0];
        String[]files = new String[args.length-1];
        System.arraycopy(args,1,files,0,args.length -1);
        Arrays.sort(files);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        for (String x: files){
            Files.copy(Paths.get(x), baos);
        }
        baos.flush();
        byte[]bytes = baos.toByteArray();
        baos.close();
        try(FileOutputStream fos = new FileOutputStream(resultName)) {
            try(ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(bytes))) {
                zis.getNextEntry();
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                byte[] buffer1 = new byte[8 * 1024];
                int len;
                while ((len = zis.read(buffer1)) > 0) {
                    buffer.write(buffer1, 0, len);
                }
                buffer.flush();
                buffer.writeTo(fos);
                buffer.close();
                fos.flush();
            }
        }

    }
}
