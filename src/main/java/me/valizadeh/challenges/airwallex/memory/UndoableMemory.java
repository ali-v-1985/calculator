package me.valizadeh.challenges.airwallex.memory;

import me.valizadeh.challenges.airwallex.operator.Statement;

import java.util.Deque;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * The memory which saves the {@link Statement}s.
 */
public class UndoableMemory extends Memory {

    private final ThreadLocal<Deque<Statement>> undone;

    public UndoableMemory() {
        super();
        this.undone = new ThreadLocal<>();
        this.undone.remove();
        this.undone.set(new ConcurrentLinkedDeque<>());

    }

    @Override
    public void clear() {
        super.clear();
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

    protected Deque<Statement> getUndone() {
        if(undone.get() == null) {
            this.undone.remove();
            undone.set(new ConcurrentLinkedDeque<>());
        }
        return undone.get();
    }
}
