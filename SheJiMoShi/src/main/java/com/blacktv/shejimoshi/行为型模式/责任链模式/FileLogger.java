package com.blacktv.shejimoshi.行为型模式.责任链模式;

public class FileLogger extends AbstractLogger{
    public FileLogger() {
        this.level = 2;
    }

    @Override
    protected void write(String message) {
        System.out.println("File::Logger: " + message);
    }
}