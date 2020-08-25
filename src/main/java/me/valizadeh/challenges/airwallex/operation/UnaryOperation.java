package me.valizadeh.challenges.airwallex.operation;

import java.util.ArrayList;
import java.util.List;

public abstract class UnaryOperation implements Operation {

    protected final Operation operand;

    public UnaryOperation(Operation operand) {
        this.operand = operand;
    }

    @Override
    public List<Operation> unExecute() {
        List<Operation> operations = new ArrayList<>();
        operations.add(operand);
        return operations;
    }

}
