package com.blacktv.shejimoshi.行为型模式.责任链模式;

public class Test {
    public static void main(String[] args) {
        ConsoleLogger consoleLogger = new ConsoleLogger();
        FileLogger fileLogger = new FileLogger();
        ErrorLogger errorLogger = new ErrorLogger();
        consoleLogger.setNextLogger(fileLogger);
        fileLogger.setNextLogger(errorLogger);

        consoleLogger.logMessage(1,"应该不会到file");
        System.out.println();
        consoleLogger.logMessage(2,"应该不会到error");
        System.out.println();
        consoleLogger.logMessage(3,"应该会到error");
    }
}
