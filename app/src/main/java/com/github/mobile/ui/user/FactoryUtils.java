package com.github.mobile.ui.user;

public class FactoryUtils {
    public static boolean isTrimmedTextNotEmpty(String name) {
        return name == null || name.trim().length() == 0;
    }
}