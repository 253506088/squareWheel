package com.blacktv.shejimoshi.行为型模式.责任链模式;

public class ErrorLogger extends AbstractLogger{
    public ErrorLogger() {
        this.level = 3;
    }

    @Override
    protected void write(String message) {
        System.out.println("Error Console::Logger: " + message);
    }
}