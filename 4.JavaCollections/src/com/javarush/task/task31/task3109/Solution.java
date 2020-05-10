package com.javarush.task.task31.task3109;

import java.io.*;
import java.util.Properties;

/* 
Читаем конфиги
*/
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        Properties properties = solution.getProperties("4.JavaCollections/src/com/javarush/task/task31/task3109/properties.xml");
        properties.list(System.out);

        properties = solution.getProperties("4.JavaCollections/src/com/javarush/task/task31/task3109/properties.txt");
        properties.list(System.out);

        properties = solution.getProperties("4.JavaCollections/src/com/javarush/task/task31/task3109/notExists");
        properties.list(System.out);
    }

    public Properties getProperties(String fileName){
        try {
            if (fileName.endsWith(".xml")) {
                File file = new File(fileName);
                Properties properties = new Properties();
                properties.loadFromXML(new FileInputStream(file));
                return properties;
            } else {
                File file = new File(fileName);
                Properties properties = new Properties();
                properties.load(new FileReader(file));
                return properties;
            }
        }
        catch (IOException e) {
            return new Properties();
        }
    }
}
