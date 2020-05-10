package com.javarush.task.task25.task2511;

import java.util.TimerTask;

/* 
Вооружаемся до зубов!
*/
public class Solution extends TimerTask {
    protected TimerTask original;
    protected final Thread.UncaughtExceptionHandler handler;

    public Solution(TimerTask original) {
        Thread.UncaughtExceptionHandler handler1;
        if (original == null) {
            throw new NullPointerException();
        }
        this.original = original;
        handler1 = null;
        class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
            public MyUncaughtExceptionHandler() {
            }

            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println(e.getMessage().replaceAll(t.getName(), mask(t.getName())));
            }
        }
        this.handler = new MyUncaughtExceptionHandler();
    }

    public void run() {
        try {
            original.run();
        } catch (Throwable cause) {
            Thread currentThread = Thread.currentThread();
            handler.uncaughtException(currentThread, new Exception("Blah " + mask(currentThread.getName()) + " blah-blah-blah", cause));
        }
    }

    public long scheduledExecutionTime() {
        return original.scheduledExecutionTime();
    }

    public boolean cancel() {
        return original.cancel();
    }
    public static String mask(String t){
        String result = "";
        for (int i = 0; i<t.length();i++){
            result+="*";
        }
        return result;
    }

    public static void main(String[] args) {
    }
}