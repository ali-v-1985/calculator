package me.valizadeh.challenges.airwallex.operator;

import java.util.ArrayList;
import java.util.List;

public abstract class UnaryOperator implements Operator {

    protected final Operator operand;

    public UnaryOperator(Operator operand) {
        this.operand = operand;
    }

    @Override
    public List<Operator> unExecute() {
        List<Operator> operators = new ArrayList<>();
        operators.add(operand);
        return operators;
    }

}
