package com.learn.spring.base.chapter11;

public class Machine {
    private Parts parts;
   private PartsManager partsManager;

    public Machine(PartsManager partsManager) {
        this.partsManager = partsManager;
    }

    public void setParts(Parts parts) {
        this.parts = parts;
    }

    public void doWork(){
        System.out.println("working");
        System.out.println(this.partsManager.createParts().getId()+"  is scrap");
    }
}
