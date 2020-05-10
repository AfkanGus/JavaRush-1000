package com.javarush.task.task29.task2913;

import java.util.Random;

/* 
Замена рекурсии
*/

public class Solution {
    private static int numberA;
    private static int numberB;

    public static void main(String[] args) {
        Random random = new Random();
        numberA = random.nextInt(1000);
        numberB = random.nextInt(1000);
        System.out.println(getAllNumbersBetween(numberA, numberB));
        System.out.println(getAllNumbersBetween(numberB, numberA));
    }

    private static String getAllNumbersBetween(int a, int b) {
        StringBuilder result = new StringBuilder(String.valueOf(a));
        while(a != b) {
            if (a > b) {
                result.append(" ").append(--a);
            } else {
                result.append(" ").append(++a);
            }
        }
        return result.toString();
    }


}