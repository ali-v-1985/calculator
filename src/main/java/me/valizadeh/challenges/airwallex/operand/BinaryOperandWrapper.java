package me.valizadeh.challenges.airwallex.operand;

import me.valizadeh.challenges.airwallex.operator.Statement;

public class BinaryOperandWrapper implements OperandWrapper {

    private final Statement operand1;
    private final Statement operand2;

    public BinaryOperandWrapper(Statement operand1, Statement operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    public Statement getOperand1() {
        return operand1;
    }

    public Statement getOperand2() {
        return operand2;
    }
}
