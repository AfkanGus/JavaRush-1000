package com.javarush.task.task27.task2710;

public class Person implements Runnable {
    private final Mail mail;

    public Person(Mail mail) {
        this.mail = mail;
    }

    @Override
    public void run() {
        {

                try
                {
                    synchronized (mail) {
                        String name = Thread.currentThread().getName();
                        Thread.sleep(1000);
                        mail.setText("Person [" + name + "] wrote an email 'AAA'");
                        mail.notifyAll();
                    }
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                    }
                }
        }
    }

