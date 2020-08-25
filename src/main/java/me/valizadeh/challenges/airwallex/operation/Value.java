package me.valizadeh.challenges.airwallex.operation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Value implements Operation {

    private final BigDecimal numberValue;

    public Value(BigDecimal numberValue) {
        this.numberValue = numberValue;
    }

    @Override
    public BigDecimal execute() {
        return numberValue;
    }

    @Override
    public List<Operation> unExecute() {
        return new ArrayList<>();
    }
}
