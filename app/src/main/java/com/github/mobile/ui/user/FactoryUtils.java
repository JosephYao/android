package com.github.mobile.ui.user;

public class FactoryUtils {
    public static boolean isTrimmedTextEmpty(String name) {
        return name == null || name.trim().length() == 0;
    }
}