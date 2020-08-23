package me.valizadeh.challenges.airwallex.operator;

import me.valizadeh.challenges.airwallex.exception.UnknownOperator;

public class OperatorFactory {

    public static final String ADDITION_SIGN = "+";
    public static final String SUBTRACTION_SIGN = "-";
    public static final String MULTIPLICATION_SIGN = "*";
    public static final String DIVISION_SIGN = "/";
    public static final String SQRT_SIGN = "sqrt";
    public static final String CLEAR_SIGN = "clear";
    public static final String UNDO_SIGN = "undo";

    private Operator addition;
    private Operator subtraction;
    private Operator multiplication;
    private Operator division;
    private Operator sqrt;
    private Operator clear;
    private Operator undo;

    public Operator get(String operator) throws UnknownOperator {
        switch (operator) {
            case ADDITION_SIGN:
                return addition;
            case SUBTRACTION_SIGN:
                return subtraction;
            case MULTIPLICATION_SIGN:
                return multiplication;
            case DIVISION_SIGN:
                return division;
            case SQRT_SIGN:
                return sqrt;
            case CLEAR_SIGN:
                return clear;
            case UNDO_SIGN:
                return undo;
            default:
                throw new UnknownOperator(operator);
        }
    }

    public void setAddition(Operator addition) {
        this.addition = addition;
    }

    public void setSubtraction(Operator subtraction) {
        this.subtraction = subtraction;
    }

    public void setMultiplication(Operator multiplication) {
        this.multiplication = multiplication;
    }

    public void setDivision(Operator division) {
        this.division = division;
    }

    public void setSqrt(Operator sqrt) {
        this.sqrt = sqrt;
    }

    public void setClear(Operator clear) {
        this.clear = clear;
    }

    public void setUndo(Operator undo) {
        this.undo = undo;
    }
}
