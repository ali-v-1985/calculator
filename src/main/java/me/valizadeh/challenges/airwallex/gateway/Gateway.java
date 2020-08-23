package me.valizadeh.challenges.airwallex.gateway;

public interface Gateway {

    String read();

    default void write(String output){}
}
