package me.valizadeh.challenges.airwallex.memory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

public class MemoryManager {

    private final ThreadLocal<Deque<BigDecimal>> numbers;
    private final ThreadLocal<Deque<String>> history;

    public MemoryManager() {
        this.numbers = new ThreadLocal<>();
        this.numbers.set(new ConcurrentLinkedDeque<>());
        this.history = new ThreadLocal<>();
        this.history.set(new ConcurrentLinkedDeque<>());
    }

    public Deque<BigDecimal> getNumbers() {
        return numbers.get();
    }

    public Deque<String> getHistory() {
        return history.get();
    }

    public String getMemory() {
        StringBuilder message = new StringBuilder();
        message.append("stack:");
        numbers.get().descendingIterator().forEachRemaining(n -> {
            String pattern = "#.##########";
            DecimalFormat decimalFormat = new DecimalFormat(pattern);
            decimalFormat.setRoundingMode(RoundingMode.FLOOR);
            message.append(" ").append(decimalFormat.format(n));
        });

        return message.toString();
    }

    public String getHistoryStr() {
        StringBuilder historyStr = new StringBuilder();
        historyStr.append("history:");
        history.get().forEach(n -> historyStr.append(" ").append(n));
        return historyStr.toString();
    }
}
