package com.simple.actor.test.thread;

import com.simple.server.common.msg.ActorMsg;

import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class SimpleMailbox {

    private final ExecutorService dispatcher;
    private final int throughput;

    ConcurrentLinkedQueue<String> normQueue = new ConcurrentLinkedQueue<>();
    // 状态 :是否销毁
    private final AtomicBoolean destroy = new AtomicBoolean(false);
    // 状态 :是否工作状态
    private final AtomicBoolean ready = new AtomicBoolean(false);
    // 状态 : 工作状态
    private final AtomicBoolean busy = new AtomicBoolean(false);

    public SimpleMailbox(ExecutorService dispatcher, int throughput) {
        this.dispatcher = dispatcher;
        this.throughput = throughput;
    }

    public void initActor() {
        dispatcher.execute(() -> init());
    }

    private void init() {
        tryProcessQueue();
    }

    private void enqueue(String msg) {
        normQueue.add(msg);
        tryProcessQueue();
    }

    // 处理阶段异步，不妨碍消息接收
    private void tryProcessQueue() {
            if (!normQueue.isEmpty()) {
               if(busy.compareAndSet(false,true))
                     dispatcher.execute(this::processMailbox);
            }
    }

    private void processMailbox() {
        boolean empty = false;
        for (int i = 0; i < throughput; i++) {
            String msg = normQueue.poll();
            if (msg != null) {
                work(msg);
            } else {
                empty = true;
                break;
            }
        }
        if (empty) {
            busy.set(false);
            dispatcher.execute(this::tryProcessQueue);
        } else {
            dispatcher.execute(this::processMailbox);
        }
    }

    public void tell(String msg) {
        enqueue(msg);
    }

    private void work(String msg) {
        try {
            System.out.println(Thread.currentThread().getName() + " <");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + "    >");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        SimpleMailbox mailbox = new SimpleMailbox(Executors.newWorkStealingPool(10), 3);
        mailbox.initActor();
        new Thread(
                () -> {
                    for (int i = 0; i < 10; i++) {
                        mailbox.tell("");
                    }
                }
        ).start();
        new Thread(
                () -> {
                    for (int i = 0; i < 10; i++) {
                        mailbox.tell("");
                    }
                }
        ).start();
        Scanner input = new Scanner(System.in);
        String cmd = null;
        do {
          //  System.out.print("......");
            cmd = input.next();
        } while (!cmd.equals("stop"));
        input.close();
    }

}
