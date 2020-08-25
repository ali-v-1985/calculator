package me.valizadeh.challenges.airwallex.operator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Division extends BinaryOperator {

    public Division(Operator operand1, Operator operand2) {
        super(operand1, operand2);
    }

    @Override
    public BigDecimal execute() {
        return operand1.execute().divide(operand2.execute(), 15, RoundingMode.FLOOR);
    }
}
