package me.valizadeh.challenges.airwallex.gateway;

/**
 * The api which acts as input and output of the {@link me.valizadeh.challenges.airwallex.calculator.Calculator}
 */
public interface Gateway {

    /**
     * Reads the values from input based on the type of {@link Gateway}
     * @return the input string which has been pass through the {@link Gateway}
     */
    String read();

    /**
     * Writes on the {@link Gateway}
     * @param output the output which should be written.
     */
    default void write(String output){}
}
