package me.valizadeh.challenges.airwallex.operator;

import java.util.ArrayList;
import java.util.List;

public abstract class BinaryOperator implements Operator {

    protected final Operator operand1;
    protected final Operator operand2;

    public BinaryOperator(Operator operand1, Operator operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    @Override
    public List<Operator> unExecute() {
        List<Operator> operators = new ArrayList<>();
        operators.add(operand1);
        operators.add(operand2);
        return operators;
    }

}
