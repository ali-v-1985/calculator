package me.valizadeh.challenges.airwallex.calculator;

import me.valizadeh.challenges.airwallex.exception.InsufficientParametersException;
import me.valizadeh.challenges.airwallex.exception.UnknownOperator;
import me.valizadeh.challenges.airwallex.memory.Memory;
import me.valizadeh.challenges.airwallex.operator.Operator;
import me.valizadeh.challenges.airwallex.operator.OperatorFactory;
import me.valizadeh.challenges.airwallex.operator.Value;
import me.valizadeh.challenges.airwallex.utils.Utility;

import java.math.BigDecimal;
import java.util.StringTokenizer;
import java.util.function.ObjIntConsumer;

public class RpnCalculator implements Calculator {

    public static final String NEW_LINE = "line.separator";
    public static final String UNDO_COMMAND = "undo";
    public static final String CLEAR_COMMAND = "clear";
    private final Memory memory;
    private final OperatorFactory operatorFactory;

    public RpnCalculator(Memory memory,
                         OperatorFactory operatorFactory) {
        this.memory = memory;
        this.operatorFactory = operatorFactory;
    }

    public String execute(String input) {
        StringBuilder message = new StringBuilder();
        try {
            readInput(input, this::push);
        } catch (UnknownOperator | InsufficientParametersException e) {
            message.append(e.getMessage());
            message.append(System.getProperty(NEW_LINE));
        }
        message.append(memory.result());
        return message.toString();
    }

    private void readInput(String input, ObjIntConsumer<String> pushFunction) {
        int pos = 1;
        StringTokenizer tokenizer = new StringTokenizer(input);
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            pushFunction.accept(token, pos);
            pos += token.length() + 1;
        }
    }

    private void push(String input, int pos) {
        Operator operator;
        if (!checkInternalOperation(input)) {
            if (Utility.isNumeric(input)) {
                operator = new Value(new BigDecimal(input));
            } else {
                operator = operatorFactory.get(input, pos, memory.getOperations());
            }
            memory.save(operator);
        }
    }

    private boolean checkInternalOperation(String input) {
        if (!(input.equals(UNDO_COMMAND) || input.equals(CLEAR_COMMAND))) {
            return false;
        }
        if (input.equals(UNDO_COMMAND)) {
            this.undo();
        } else {
            this.clear();
        }
        return true;
    }

    private void undo() {
        memory.undo();
    }

    public void clear() {
        memory.clear();
    }

}
