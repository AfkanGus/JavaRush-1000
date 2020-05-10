package com.javarush.task.task31.task3102;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/* 
Находим все файлы
*/
public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        List<String>result = new ArrayList<>();
        Queue<File>fileQueue = new PriorityQueue<>();
        fileQueue.add(new File(root));
        while (!fileQueue.isEmpty()){
            File x = fileQueue.remove();
            if (x.isFile()){
                result.add(x.getAbsolutePath());
            }
            else if (x.isDirectory()){
                for (File f: x.listFiles()){
                    fileQueue.add(f);
                }
            }
        }
        return result;
        }

    public static void main(String[] args) {

    }
}
