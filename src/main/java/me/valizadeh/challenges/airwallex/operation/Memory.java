package me.valizadeh.challenges.airwallex.operation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Memory {

    private static final String PATTERN = "#.##########";

    private final ThreadLocal<Deque<Operation>> operations;
    private final ThreadLocal<Deque<Operation>> undone;
    private final DecimalFormat decimalFormat;


    public Memory() {
        this.operations = new ThreadLocal<>();
        this.operations.remove();
        this.operations.set(new ConcurrentLinkedDeque<>());
        this.undone = new ThreadLocal<>();
        this.undone.remove();
        this.undone.set(new ConcurrentLinkedDeque<>());
        this.decimalFormat = new DecimalFormat(PATTERN);
        this.decimalFormat.setRoundingMode(RoundingMode.FLOOR);
    }

    public Deque<Operation> getOperations() {
        if(operations.get() == null) {
            this.operations.remove();
            operations.set(new ConcurrentLinkedDeque<>());
        }
        return operations.get();
    }

    public Deque<Operation> getUndone() {
        if(undone.get() == null) {
            this.undone.remove();
            undone.set(new ConcurrentLinkedDeque<>());
        }
        return undone.get();
    }

    public String getMemory() {
        StringBuilder memory = new StringBuilder();
        memory.append("stack:");
        operations.get().descendingIterator().forEachRemaining(n -> {
            BigDecimal value = n.execute();
            memory.append(" ").append(decimalFormat.format(value));
        });

        return memory.toString();
    }
}
