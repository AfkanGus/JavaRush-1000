package com.javarush.task.task31.task3105;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/*
Добавление файла в архив
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) return;

        HashMap<String, byte[]> zlist = new HashMap<>();

        ZipInputStream zis = new ZipInputStream(new FileInputStream(args[1]));
        ZipEntry temp;
        while ((temp = zis.getNextEntry()) != null) {
            byte[] content = new byte[1024 * 8];
            int count = 0;
            ByteArrayOutputStream baos=new ByteArrayOutputStream();
            while ((count = zis.read(content)) > 0) {
                baos.write(content,0,count);
            }
            zlist.put(temp.toString(), baos.toByteArray());
        }
        zis.close();
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(args[1]));
        Path file = Paths.get(args[0]);
        boolean hasfile = false;
        for (HashMap.Entry<String, byte[]> entry : zlist.entrySet()) {
            if (Paths.get(entry.getKey()).getFileName().equals(file.getFileName())) {
                StringBuilder filename = new StringBuilder();
                if (Paths.get(entry.getKey()).getParent() != null)
                    filename.append(Paths.get(entry.getKey()).getParent()).append("/");
                filename.append(file.getFileName());
                zos.putNextEntry(new ZipEntry(filename.toString()));
                Files.copy(file, zos);
                hasfile = true;
            } else {
                zos.putNextEntry(new ZipEntry(entry.getKey()));
                zos.write(entry.getValue());
            }
        }
        if (!hasfile) {
            zos.putNextEntry(new ZipEntry("new/" + file.getFileName()));
            Files.copy(file, zos);
        }
        zos.close();



    }
}
