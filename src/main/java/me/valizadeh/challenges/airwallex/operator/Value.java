package me.valizadeh.challenges.airwallex.operator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Value implements Operator {

    private final BigDecimal numberValue;

    public Value(BigDecimal numberValue) {
        this.numberValue = numberValue;
    }

    @Override
    public BigDecimal execute() {
        return numberValue;
    }

    @Override
    public List<Operator> unExecute() {
        return new ArrayList<>();
    }
}
