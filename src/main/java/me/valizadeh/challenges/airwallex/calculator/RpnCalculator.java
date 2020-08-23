package me.valizadeh.challenges.airwallex.calculator;

import me.valizadeh.challenges.airwallex.exception.InsufficientParametersException;
import me.valizadeh.challenges.airwallex.exception.UnknownOperator;
import me.valizadeh.challenges.airwallex.operator.Operator;
import me.valizadeh.challenges.airwallex.operator.OperatorFactory;
import me.valizadeh.challenges.airwallex.utils.Utility;

import java.math.BigDecimal;
import java.util.Stack;
import java.util.StringTokenizer;

public class RpnCalculator implements Calculator {

    public static final String NEW_LINE = "line.separator";
    private final ThreadLocal<Stack<BigDecimal>> numbers;
    private final ThreadLocal<Stack<String>> history;

    private final OperatorFactory operatorFactory;

    public RpnCalculator(OperatorFactory operatorFactory) {
        this.numbers = new ThreadLocal<>();
        this.numbers.set(new Stack<>());
        this.history = new ThreadLocal<>();
        this.history.set(new Stack<>());
        this.operatorFactory = operatorFactory;
    }

    public String calculate(String input) {
        StringBuilder message = new StringBuilder();
        StringTokenizer tokenizer = new StringTokenizer(input);
        int pos = 1;
        try {
            while (tokenizer.hasMoreTokens()) {
                String token = tokenizer.nextToken();
                if (Utility.isNumeric(token)) {
                    numbers.get().push(new BigDecimal(token));
                } else {
                    Operator operator = operatorFactory.get(token);
                    numbers.set(operator.calculate(numbers.get(), pos));
                }
                pos += token.length() + 1;
                history.get().push(token);
            }
        } catch (UnknownOperator | InsufficientParametersException e) {
            message.append(e.getMessage());
        }
        message.append(System.getProperty(NEW_LINE));

        message.append("stack: ");
        numbers.get().forEach(n -> {
            message.append(n).append(" ");
        });
        message.append(System.getProperty(NEW_LINE));

        message.append("history: ");
        history.get().forEach(n -> {
            message.append(n).append(" ");
        });
        return message.toString();
    }
}
