package me.valizadeh.challenges.airwallex.operator;

public abstract class UnaryOperator implements Operator {
    public static final int NEEDED_OPERANDS = 1;

    @Override
    public int neededOperands() {
        return NEEDED_OPERANDS;
    }
}
