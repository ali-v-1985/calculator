package me.valizadeh.challenges.airwallex.operator;

import me.valizadeh.challenges.airwallex.exception.UnknownOperator;

public class OperatorFactory {

    public static final String ADDITION = "+";
    public static final String SUBTRACTION = "-";
    public static final String MULTIPLICATION = "*";
    public static final String DIVISION = "/";
    public static final String SQRT = "sqrt";
    public static final String CLEAR = "clear";
    public static final String UNDO = "undo";

    private Operator addition;
    private Operator subtraction;
    private Operator multiplication;
    private Operator division;
    private Operator sqrt;
    private Operator clear;
    private Operator undo;

    public Operator get(String operator) throws UnknownOperator {
        switch (operator) {
            case ADDITION:
                return addition;
            case SUBTRACTION:
                return subtraction;
            case MULTIPLICATION:
                return multiplication;
            case DIVISION:
                return division;
            case SQRT:
                return sqrt;
            case CLEAR:
                return clear;
            case UNDO:
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
