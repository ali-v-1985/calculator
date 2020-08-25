package me.valizadeh.challenges.airwallex.operation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Deque;

public class Division extends BinaryOperation {

    public Division(Operation operand1, Operation operand2) {
        super(operand1, operand2);
    }

    @Override
    public BigDecimal execute() {
        return operand1.execute().divide(operand2.execute(), 15, RoundingMode.FLOOR);
    }

    public static Division get(Deque<Operation> operations) {
        Operation operand2 = null;
        Operation operand1;
        try {
            operand2 = operations.pop();
            operand1 = operations.pop();
        } catch (Exception e) {
            if (operand2 != null) {
                operations.push(operand2);
            }
            throw e;
        }
        return new Division(operand1, operand2);
    }
}
