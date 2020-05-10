package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

/* 
Что внутри папки?
*/
public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fullPath = reader.readLine();

        Path temp = Paths.get(fullPath);
        if (!Files.isDirectory(temp)) {
            System.out.println(fullPath + " - не папка");
            return;
            }
        Map<String, Long> results = new HashMap<>();
        results.put("fullSize", 0L);
        results.put("dirCount", -1L);
        results.put("filesCount", 0L);
        Files.walkFileTree(temp, new FileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                if (attrs.isDirectory()) {
                    long value = results.get("dirCount");
                    results.put("dirCount", ++value);
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (attrs.isRegularFile()) {
                    long value = results.get("filesCount");
                    results.put("filesCount", ++value);
                    value = results.get("fullSize");
                    value += attrs.size();
                    results.put("fullSize", value);
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return FileVisitResult.SKIP_SUBTREE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }
        });

        System.out.println("Всего папок - " + results.get("dirCount"));
            System.out.println("Всего файлов - " + results.get("filesCount"));
            System.out.println("Общий размер - " + results.get("fullSize"));
            reader.close();


    }
}
