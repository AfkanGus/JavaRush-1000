package com.javarush.task.task25.task2510;

/* 
Поживем - увидим
*/
public class Solution extends Thread {

    public Solution() {
        class MyUncaughtExceptionHandler implements UncaughtExceptionHandler{
            public MyUncaughtExceptionHandler() {
            }

            @Override
            public void uncaughtException(Thread t, Throwable e) {
                if (e instanceof Error){
                    System.out.println("Нельзя дальше работать");
                }
                else if (e instanceof Exception){
                    System.out.println("Надо обработать");
                }
                else {
                    System.out.println("Поживем - увидим");
                }
            }
        }
        setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
    }

    public static void main(String[] args) {
    }
}
