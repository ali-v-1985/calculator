package me.valizadeh.challenges.airwallex.operation;

import me.valizadeh.challenges.airwallex.memory.MemoryManager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OperationExecutor {

    private final List<Operation> operationList
            = new ArrayList<>();



    public BigDecimal executeOperation(Operation operation) {
        operationList.add(operation);
        return operation.execute();
    }
}
