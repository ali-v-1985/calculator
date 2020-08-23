package me.valizadeh.challenges.airwallex.calculator;

import me.valizadeh.challenges.airwallex.exception.InsufficientParametersException;
import me.valizadeh.challenges.airwallex.exception.UnknownOperator;
import me.valizadeh.challenges.airwallex.memory.MemoryManager;
import me.valizadeh.challenges.airwallex.operator.Operator;
import me.valizadeh.challenges.airwallex.operator.OperatorFactory;
import me.valizadeh.challenges.airwallex.utils.Utility;

import java.math.BigDecimal;
import java.util.StringTokenizer;

public class RpnCalculator implements Calculator {

    public static final String NEW_LINE = "line.separator";
    private final OperatorFactory operatorFactory;
    private final MemoryManager memoryManager;

    public RpnCalculator(OperatorFactory operatorFactory, MemoryManager memoryManager) {
        this.operatorFactory = operatorFactory;
        this.memoryManager = memoryManager;
    }

    public String calculate(String input) {
        StringBuilder message = new StringBuilder();
        StringTokenizer tokenizer = new StringTokenizer(input);
        int pos = 1;
        try {
            while (tokenizer.hasMoreTokens()) {
                String token = tokenizer.nextToken();
                if (Utility.isNumeric(token)) {
                    memoryManager.getNumbers().push(new BigDecimal(token));
                } else {
                    Operator operator = operatorFactory.get(token);
                    operator.calculate(memoryManager.getNumbers(), pos);
                }
                pos += token.length() + 1;
                memoryManager.getHistory().push(token);
            }
        } catch (UnknownOperator | InsufficientParametersException e) {
            message.append(e.getMessage());
            message.append(System.getProperty(NEW_LINE));
        }
        message.append(memoryManager.getMemory());
        System.out.println(memoryManager.getHistoryStr());
        return message.toString();
    }

    @Override
    public void reset() {
        memoryManager.getHistory().clear();
        memoryManager.getNumbers().clear();
    }
}
