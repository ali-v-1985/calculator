package me.valizadeh.challenges.airwallex.exception;

public class UnknownOperator extends Exception {

    public UnknownOperator(String operator) {
        super("Unknown operator " + operator);
    }
}
