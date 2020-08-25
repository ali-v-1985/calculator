package me.valizadeh.challenges.airwallex.operator;

import java.math.BigDecimal;

public class Multiplication extends BinaryOperator {

    public Multiplication(Operator operand1, Operator operand2) {
        super(operand1, operand2);
    }

    @Override
    public BigDecimal execute() {
        return operand1.execute().multiply(operand2.execute());
    }
}
