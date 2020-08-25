package me.valizadeh.challenges.airwallex.operation;

import java.math.BigDecimal;
import java.util.Deque;

public class Subtraction extends BinaryOperation {

    public Subtraction(Operation operand1, Operation operand2) {
        super(operand1, operand2);
    }

    @Override
    public BigDecimal execute() {
        return operand1.execute().subtract(operand2.execute());
    }

    public static Subtraction get(Deque<Operation> operations) {
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
        return new Subtraction(operand1, operand2);
    }
}
