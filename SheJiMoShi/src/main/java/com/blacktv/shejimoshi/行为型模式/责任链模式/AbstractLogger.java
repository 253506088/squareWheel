package com.blacktv.shejimoshi.行为型模式.责任链模式;

/**
 * 日志的抽象类
 */
public abstract class AbstractLogger {
    /**
     * 日志等级
     */
    public static int INFO = 1;
    public static int DEBUG = 2;
    public static int ERROR = 3;

    protected int level;

    /**
     * 责任链中的下一个元素
     */
    private AbstractLogger nextLogger;

    /**
     * 设置下一层责任链
     *
     * @param nextLogger
     */
    public void setNextLogger(AbstractLogger nextLogger) {
        this.nextLogger = nextLogger;
    }

    /**
     * 输出日志信息，如果当前日志等级小于等于输出日志等级，则打印
     * 如果存在下一层责任链，则调用下一层
     *
     * @param level   本次日志输出的等级
     * @param message 日志信息
     */
    public void logMessage(int level, String message) {
        if (this.level <= level) {
            write(message);
        }
        if (nextLogger != null) {
            nextLogger.logMessage(level, message);
        }
    }

    abstract protected void write(String message);
}
