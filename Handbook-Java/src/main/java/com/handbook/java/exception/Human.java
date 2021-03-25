package com.handbook.java.exception;

public class Human {
    public static void main(String[] args) throws Exception {
        try {
            try {
                throw new Sneeze();
            } catch (Annoyance e) {
                System.out.println("Caught Annoyance");
                throw e;
            }
        } catch (Sneeze s) {
            System.out.println("Caught Sneeze");
            return;
        } finally {
            System.out.println("Hello World");
        }


    }
}
