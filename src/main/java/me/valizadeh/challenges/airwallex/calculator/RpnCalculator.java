package me.valizadeh.challenges.airwallex.calculator;

import me.valizadeh.challenges.airwallex.exception.InsufficientParametersException;
import me.valizadeh.challenges.airwallex.exception.UnknownOperator;
import me.valizadeh.challenges.airwallex.memory.Memory;
import me.valizadeh.challenges.airwallex.operator.Operator;
import me.valizadeh.challenges.airwallex.operator.OperatorFactory;
import me.valizadeh.challenges.airwallex.operator.Value;
import me.valizadeh.challenges.airwallex.utils.Utility;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.List;
import java.util.StringTokenizer;

import static me.valizadeh.challenges.airwallex.utils.MessageUtil.INSUFFICIENT_PARAMETERS_WARN;

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

    public void clear() {
        memory.clear();
    }


    public String execute(String input) {
        StringBuilder message = new StringBuilder();
        StringTokenizer tokenizer = new StringTokenizer(input);
        int pos = 1;
        try {
            while (tokenizer.hasMoreTokens()) {
                String token = tokenizer.nextToken();
                this.push(token, pos);
                pos += token.length() + 1;
            }
        } catch (UnknownOperator | InsufficientParametersException e) {
            message.append(e.getMessage());
            message.append(System.getProperty(NEW_LINE));
        }
        message.append(memory.getMemory());
        return message.toString();
    }

    private void push(String operationValue, int pos) throws InsufficientParametersException, UnknownOperator {
        Operator operator;
        if (operationValue.equals(UNDO_COMMAND)) {
            this.undo();
        } else if (operationValue.equals(CLEAR_COMMAND)) {
            this.clear();
        } else {
            if (Utility.isNumeric(operationValue)) {
                operator = new Value(new BigDecimal(operationValue));
            } else {
                try {
                    operator = operatorFactory.get(operationValue, memory.getOperations());
                } catch(UnknownOperator e) {
                    throw e;
                } catch (Exception e) {
                    throw new InsufficientParametersException(MessageFormat.format(INSUFFICIENT_PARAMETERS_WARN,
                            operationValue,
                            pos));
                }
            }
            memory.getOperations().push(operator);
        }
    }

    private void undo() {
        if (!memory.getOperations().isEmpty()) {
            Operator undoOperator = memory.getOperations().pop();
            List<Operator> undoOperators = undoOperator.unExecute();
            undoOperators.forEach(o -> memory.getOperations().push(o));
            memory.getUndone().push(undoOperator);
        }
    }
}
