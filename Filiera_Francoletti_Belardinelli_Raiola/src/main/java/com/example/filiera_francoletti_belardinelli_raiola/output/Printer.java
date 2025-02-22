package com.example.filiera_francoletti_belardinelli_raiola.output;

public final class Printer {

    private static Printer instance;

    private Printer() { }

    public static synchronized Printer getInstance() {
        if(instance == null) {
            instance = new Printer();
        }
        return instance;
    }

    public static void print(String message) {
        System.out.println(message);
    }

    //metodo istanza singleton
    public void printMessage(String message) {
        System.out.println(message);
    }
}
