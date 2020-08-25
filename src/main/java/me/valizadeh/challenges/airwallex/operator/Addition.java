package me.valizadeh.challenges.airwallex.operator;

import java.math.BigDecimal;

public class Addition extends BinaryOperator {

    public Addition(Operator operand1, Operator operand2) {
        super(operand1, operand2);
    }

    @Override
    public BigDecimal execute() {
        return operand1.execute().add(operand2.execute());
    }

}
