package me.valizadeh.challenges.airwallex.utils;

import java.util.regex.Pattern;

/**
 * A general helper class which helps calculator on number detection.
 */
public class NumberHelper {

    private NumberHelper() {
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
