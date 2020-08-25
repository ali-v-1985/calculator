package me.valizadeh.challenges.airwallex.operation;

import java.util.Deque;

import static me.valizadeh.challenges.airwallex.operation.OperationSigns.*;

public class OperationFactory {

    private OperationFactory() {
    }

    public static Operation get(String sign, Deque<Operation> operations) {
        switch (sign) {
            case ADDITION_SIGN:
                return Addition.get(operations);
            case SUBTRACTION_SIGN:
                return Subtraction.get(operations);
            case MULTIPLICATION_SIGN:
                return Multiplication.get(operations);
            case DIVISION_SIGN:
                return Division.get(operations);
            case SQRT_SIGN:
                return SquareRoot.get(operations);
            default:
                return null;
        }

    }
}
