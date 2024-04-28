package com.onezhan.util;

public class ThreadLocalUtil {
    private static final ThreadLocal Thread_LOCAL = new ThreadLocal();
    public static <T> T get() {
        return (T) Thread_LOCAL.get();
    }

    public static void set(Object obj) {
        Thread_LOCAL.set(obj);
    }

    public static void remove() {
        Thread_LOCAL.remove();
    }
}
