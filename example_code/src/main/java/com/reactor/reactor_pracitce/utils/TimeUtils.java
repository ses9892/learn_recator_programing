package com.reactor.reactor_pracitce.utils;

public class TimeUtils {

    public static void sleep(long seconds) {
        try {
            Thread.sleep(seconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
