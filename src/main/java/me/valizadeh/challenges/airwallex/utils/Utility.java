package me.valizadeh.challenges.airwallex.utils;

import java.util.regex.Pattern;

public class Utility {

    private Utility() {
    }

    private static final Pattern numericPattern;

    static {
        numericPattern = Pattern.compile("-?\\d+(\\.\\d+)?");
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        return numericPattern.matcher(strNum).matches();
    }
}
