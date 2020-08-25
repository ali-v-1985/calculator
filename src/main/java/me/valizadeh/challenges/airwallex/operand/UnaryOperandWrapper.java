package me.valizadeh.challenges.airwallex.operand;

import me.valizadeh.challenges.airwallex.operator.Statement;

public class UnaryOperandWrapper implements OperandWrapper {

    private final Statement operand;

    public UnaryOperandWrapper(Statement operand) {
        this.operand = operand;
    }

    public Statement getOperand() {
        return operand;
    }
}
