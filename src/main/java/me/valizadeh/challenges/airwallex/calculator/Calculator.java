package me.valizadeh.challenges.airwallex.calculator;

/**
 * The {@link Calculator} which can have different implementation of {@link Calculator}.
 */
public interface Calculator {

    /**
     * Receive the input string based on the type of {@link Calculator} and process it and return the result.
     * @param input the input string of the {@link Calculator}
     * @return the result of the execution result of input based on the type of {@link Calculator}
     */
    String execute(String input);

    /**
     * Clear the memory of the {@link Calculator}
     */
    void clear();

    /**
     * Undo the latest operation of memory of the {@link Calculator}
     */
    void undo();
}
