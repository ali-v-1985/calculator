package me.valizadeh.challenges.airwallex.operator;

import me.valizadeh.challenges.airwallex.exception.UnknownOperator;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class OperatorSignMapper {

    public static final String ADDITION_SIGN = "+";
    public static final String SUBTRACTION_SIGN = "-";
    public static final String MULTIPLICATION_SIGN = "*";
    public static final String DIVISION_SIGN = "/";
    public static final String SQRT_SIGN = "sqrt";

    private final Map<String, Class<? extends Operator>> operatorMap;

    public OperatorSignMapper() {
        operatorMap = new HashMap<>();
        operatorMap.put(ADDITION_SIGN, Addition.class);
        operatorMap.put(SUBTRACTION_SIGN, Subtraction.class);
        operatorMap.put(MULTIPLICATION_SIGN, Multiplication.class);
        operatorMap.put(DIVISION_SIGN, Division.class);
        operatorMap.put(SQRT_SIGN, SquareRoot.class);
    }

    public Class<? extends Operator> get(String sign) {
        Optional<Class<? extends Operator>> clazz = Optional.ofNullable(operatorMap.get(sign));
        return clazz.orElseThrow(() -> new UnknownOperator(sign));
    }
}
