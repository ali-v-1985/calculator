package me.valizadeh.challenges.airwallex.operator;

import java.util.ArrayList;
import java.util.List;

/**
 * The abstract {@link Statement} which contains one operand of type {@link Statement}.
 */
public abstract class UnaryStatement implements Statement {

    protected final Statement operand;

    public UnaryStatement(Statement operand) {
        this.operand = operand;
    }

    /**
     * @return the operand of any {@link UnaryStatement}.
     */
    @Override
    public List<Statement> unExecute() {
        List<Statement> statements = new ArrayList<>();
        statements.add(operand);
        return statements;
    }

}
