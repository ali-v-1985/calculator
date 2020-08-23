package me.valizadeh.challenges.airwallex.calculator;

import me.valizadeh.challenges.airwallex.exception.InsufficientParametersException;
import me.valizadeh.challenges.airwallex.exception.UnknownOperator;

public interface Calculator {

    String calculate(String input) throws UnknownOperator, InsufficientParametersException;
}
