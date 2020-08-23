package me.valizadeh.challenges.airwallex.operator;

public abstract class BinaryOperator implements Operator {

    public static final int NEEDED_OPERANDS = 2;

    @Override
    public int neededOperands() {
        return NEEDED_OPERANDS;
    }
}
