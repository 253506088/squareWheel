package com.blacktv.shejimoshi.行为型模式.责任链模式;

public class ConsoleLogger extends AbstractLogger{
    public ConsoleLogger() {
        this.level = 1;
    }

    @Override
    protected void write(String message) {
        System.out.println("Standard Console::Logger: " + message);
    }
}
