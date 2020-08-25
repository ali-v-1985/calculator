package me.valizadeh.challenges.airwallex.calculator;

import me.valizadeh.challenges.airwallex.exception.InsufficientParametersException;
import me.valizadeh.challenges.airwallex.exception.UnknownOperator;
import me.valizadeh.challenges.airwallex.memory.Memory;
import me.valizadeh.challenges.airwallex.operand.BinaryOperandWrapper;
import me.valizadeh.challenges.airwallex.operand.OperandWrapper;
import me.valizadeh.challenges.airwallex.operand.UnaryOperandWrapper;
import me.valizadeh.challenges.airwallex.operator.*;
import me.valizadeh.challenges.airwallex.utils.NumberHelper;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.function.*;

/**
 * The {@literal Reverse Polish Notation} implementation of the {@link Calculator} API.
 */
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

    /**
     * Process input string based on {@literal RPN}
     *
     * @param input the input string of the {@link Calculator}
     * @return The execution of all {@link Statement}s in memory
     */
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

    /**
     * Ask the memory to clear the {@link Statement}s inside it.
     */
    public void clear() {
        memory.clear();
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
        Statement statement;
        if (!checkInternalOperation(input)) {
            if (NumberHelper.isNumeric(input)) {
                statement = new Value(new BigDecimal(input));
            } else {
                statement = operatorFactory.get(input, pos, this::operandWrapper);
            }
            memory.save(statement);
        }
    }

    private OperandWrapper operandWrapper(Class<? extends Statement> statementType) {
        if (UnaryStatement.class.isAssignableFrom(statementType)) {
            return getUnaryOperandWrapper();
        } else if (BinaryStatement.class.isAssignableFrom(statementType)) {
            return getBinaryOperandWrapper();
        } else {
            return null;
        }
    }

    private UnaryOperandWrapper getUnaryOperandWrapper() {
        Statement operand = memory.getOperations().pop();
        return new UnaryOperandWrapper(operand);
    }

    private BinaryOperandWrapper getBinaryOperandWrapper() {
        Statement operand2 = null;
        Statement operand1;
        try {
            operand2 = memory.getOperations().pop();
            operand1 = memory.getOperations().pop();
        } catch (NoSuchElementException e) {
            if (operand2 != null) {
                memory.getOperations().push(operand2);
            }
            throw e;
        }
        Statement finalOperand = operand2;
        return new BinaryOperandWrapper(operand1, finalOperand);
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

}
