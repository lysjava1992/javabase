package com.handbook.java.exception;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Human2 {
    public static void main(String[] args) {
        ExecutorService service= Executors.newSingleThreadExecutor();
         try {
             throw new Sneeze();
         } catch (Sneeze annoyance) {
             System.out.println("Caught Sneeze");
         }catch (Annoyance annoyance) {
             System.out.println("Caught Annoyance");
         } catch (Exception e) {
             System.out.println("Caught Exception");
         }
    }
}
