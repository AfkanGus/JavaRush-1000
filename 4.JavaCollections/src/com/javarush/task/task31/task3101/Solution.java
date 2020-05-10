package com.javarush.task.task31.task3101;

import java.io.*;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

/*
Проход по дереву файлов
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        File path = new File(args[0]);
        File outputFileTmp = new File(args[1]);
        File resultFile = new File(outputFileTmp.getParent() + "/allFilesContent.txt");
        FileUtils.renameFile(outputFileTmp, resultFile);
        ArrayList<File>files = new ArrayList<>();
        Files.walkFileTree(path.toPath(), new FileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (attrs.isRegularFile() && attrs.size() <= 50) {
                    files.add(new File(file.toUri()));
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

        files.sort((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(resultFile));
            for (File filePath : files) {
                BufferedReader reader = new BufferedReader(new FileReader(filePath));
                    String str;
                    while ((str = reader.readLine()) != null) {
                        writer.write(str);
                        writer.write('\n');
                    }
            }
            writer.close();
            } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
