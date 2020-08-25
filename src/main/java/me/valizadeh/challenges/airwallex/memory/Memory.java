package me.valizadeh.challenges.airwallex.memory;

import me.valizadeh.challenges.airwallex.operator.Operator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Memory {

    private static final String PATTERN = "#.##########";

    private final ThreadLocal<Deque<Operator>> operations;
    private final ThreadLocal<Deque<Operator>> undone;
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

    public Deque<Operator> getOperations() {
        if(operations.get() == null) {
            this.operations.remove();
            operations.set(new ConcurrentLinkedDeque<>());
        }
        return operations.get();
    }

    public Deque<Operator> getUndone() {
        if(undone.get() == null) {
            this.undone.remove();
            undone.set(new ConcurrentLinkedDeque<>());
        }
        return undone.get();
    }

    public String result() {
        StringBuilder memory = new StringBuilder();
        memory.append("stack:");
        operations.get().descendingIterator().forEachRemaining(n -> {
            BigDecimal value = n.execute();
            memory.append(" ").append(decimalFormat.format(value));
        });

        return memory.toString();
    }

    public void clear() {
        this.operations.get().clear();
        this.operations.remove();
        this.operations.set(new ConcurrentLinkedDeque<>());
        this.undone.get().clear();
        this.undone.remove();
        this.undone.set(new ConcurrentLinkedDeque<>());
    }

    public void undo() {
        if (!this.getOperations().isEmpty()) {
            Operator undoOperator = this.getOperations().pop();
            List<Operator> undoOperators = undoOperator.unExecute();
            undoOperators.forEach(o -> this.getOperations().push(o));
            this.getUndone().push(undoOperator);
        }
    }

    public void save(Operator operator) {
        this.getOperations().push(operator);
    }
}
