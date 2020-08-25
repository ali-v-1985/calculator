package me.valizadeh.challenges.airwallex.exception;

import me.valizadeh.challenges.airwallex.operator.BinaryStatement;
import me.valizadeh.challenges.airwallex.operator.Statement;
import me.valizadeh.challenges.airwallex.operator.UnaryStatement;

/**
 * Will be thrown when a {@link Statement} won't find
 * enough {@literal Operands} inside memory.
 * It is based on {@link UnaryStatement}
 * and {@link BinaryStatement}
 */
public class InsufficientParametersException extends RuntimeException {
    public InsufficientParametersException(String message) {
        super(message);
    }
}
