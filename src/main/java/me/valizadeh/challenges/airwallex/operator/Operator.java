package me.valizadeh.challenges.airwallex.operator;

import java.math.BigDecimal;
import java.util.List;

public interface Operator {

    BigDecimal execute();

    List<Operator> unExecute();

}
