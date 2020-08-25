package me.valizadeh.challenges.airwallex.memory;

import me.valizadeh.challenges.airwallex.operator.Statement;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * The memory which saves the {@link Statement}s.
 */
public class Memory {

    private static final String PATTERN = "#.##########";

    private final ThreadLocal<Deque<Statement>> operations;
    private final ThreadLocal<Deque<Statement>> undone;
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

    /**
     *
     * @return a list of {@link Statement}s which have been saved on memory.
     */
    public Deque<Statement> getOperations() {
        if(operations.get() == null) {
            this.operations.remove();
            operations.set(new ConcurrentLinkedDeque<>());
        }
        return operations.get();
    }

    /**
     *
     * @return a string which represent the execution of the {@link Statement}s which have been saved on memory.
     */
    public String result() {
        StringBuilder memory = new StringBuilder();
        memory.append("stack:");
        operations.get().descendingIterator().forEachRemaining(n -> {
            BigDecimal value = n.execute();
            memory.append(" ").append(decimalFormat.format(value));
        });

        return memory.toString();
    }

    /**
     * Clears the {@link Statement}s history on the memory.
     */
    public void clear() {
        this.operations.get().clear();
        this.operations.remove();
        this.operations.set(new ConcurrentLinkedDeque<>());
        this.undone.get().clear();
        this.undone.remove();
        this.undone.set(new ConcurrentLinkedDeque<>());
    }

    /**
     * Undoes the latest pushed {@link Statement} on the memory.
     */
    public void undo() {
        if (!this.getOperations().isEmpty()) {
            Statement undoStatement = this.getOperations().pop();
            List<Statement> undoStatements = undoStatement.unExecute();
            undoStatements.forEach(o -> this.getOperations().push(o));
            this.getUndone().push(undoStatement);
        }
    }

    /**
     * Saves the {@link Statement} on the memory.
     * @param statement
     */
    public void save(Statement statement) {
        this.getOperations().push(statement);
    }

    private Deque<Statement> getUndone() {
        if(undone.get() == null) {
            this.undone.remove();
            undone.set(new ConcurrentLinkedDeque<>());
        }
        return undone.get();
    }
}
