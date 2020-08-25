package me.valizadeh.challenges.airwallex.operator;

import java.math.BigDecimal;

public class Subtraction extends BinaryOperator {

    public Subtraction(Operator operand1, Operator operand2) {
        super(operand1, operand2);
    }

    @Override
    public BigDecimal execute() {
        return operand1.execute().subtract(operand2.execute());
    }
}
