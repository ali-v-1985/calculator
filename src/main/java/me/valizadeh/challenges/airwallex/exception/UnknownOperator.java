package me.valizadeh.challenges.airwallex.exception;

/**
 * Will be thrown when the input
 * contains tokens which are not recognized by the {@link me.valizadeh.challenges.airwallex.calculator.Calculator}
 */
public class UnknownOperator extends RuntimeException {

    public UnknownOperator(String operator) {
        super("Unknown operator " + operator);
    }
}
