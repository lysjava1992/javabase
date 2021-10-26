package com.handbook.java.close;


import sun.misc.Cleaner;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName Room
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2021/5/9 15:52
 * @Version 1.0
 **/
public class Room implements AutoCloseable {
    private final Cleaner cleaner ;
    private final State state ;
    public Room(int unmJunkPiles) {
        this.state = new State(unmJunkPiles);
        this.cleaner=  Cleaner.create(this,this.state);
    }
    private static class A{

    }
    @Override
    public void close() throws Exception {
        System.out.println("自动关闭");
        cleaner.clean();
    }

    private static class State implements Runnable {
        int unmJunkPiles;

        State(int unmJunkPiles) {
            this.unmJunkPiles = unmJunkPiles;
        }

        // 被close方法或清除方法调用
        @Override
        public void run() {
             System.out.println("Cleaning room");
             unmJunkPiles=0;
        }
    }

    public static void main(String[] args) {
        String b=null;
        "b".equals(b);
//        try (Room room=new Room(7)){
//            System.out.println("Goodbye");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        Room room=new Room(7);
        System.out.println("Peace out");
        System.gc();
          A a=new A();
    }
}
