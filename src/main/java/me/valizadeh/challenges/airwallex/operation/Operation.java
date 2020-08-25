package me.valizadeh.challenges.airwallex.operation;

import java.math.BigDecimal;
import java.util.List;

public interface Operation {

    BigDecimal execute();

    List<Operation> unExecute();

}
