package me.valizadeh.challenges.airwallex.exception;

public class UnknownOperator extends RuntimeException {

    public UnknownOperator(String operator) {
        super("Unknown operator " + operator);
    }
}
