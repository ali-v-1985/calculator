package me.valizadeh.challenges.airwallex.operation;

import java.util.ArrayList;
import java.util.List;

public abstract class BinaryOperation implements Operation {

    protected final Operation operand1;
    protected final Operation operand2;

    public BinaryOperation(Operation operand1, Operation operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    @Override
    public List<Operation> unExecute() {
        List<Operation> operations = new ArrayList<>();
        operations.add(operand1);
        operations.add(operand2);
        return operations;
    }

}
