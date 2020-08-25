package me.valizadeh.challenges.airwallex.operation;

import java.util.Deque;

public class SquareRoot extends Root {

    public SquareRoot(Operation operand) {
        super(operand, 2);
    }

    public static SquareRoot get(Deque<Operation> operations) {
        Operation operand = operations.pop();
        return new SquareRoot(operand);
    }
}
