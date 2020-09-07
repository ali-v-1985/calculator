package me.valizadeh.challenges.airwallex.utils;

import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * A general helper class which helps calculator on number detection.
 */
public class NumberHelper {

    private NumberHelper() {
    }

    private static final Predicate<String> numericPatternPredicate;

    static {
        numericPatternPredicate = Pattern.compile("-?\\d+(\\.\\d+)?").asPredicate();
    }

    public static Predicate<String> isNumeric() {
        return strNum -> {
            if (strNum == null) {
                return false;
            }
            return numericPatternPredicate.test(strNum);
        };
    }
}
