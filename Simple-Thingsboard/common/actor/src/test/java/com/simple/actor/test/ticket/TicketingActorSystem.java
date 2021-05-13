package com.simple.actor.test.ticket;


import com.simple.actor.test.IntTbActorMsg;
import com.simple.thingsboard.server.actor.ActorRef;
import com.simple.thingsboard.server.actor.ActorSystemSettings;
import com.simple.thingsboard.server.actor.core.DefaultActorSystem;
import java.util.concurrent.Executors;

public class TicketingActorSystem {
    public static void main(String[] args) throws InterruptedException {
        ActorSystemSettings settings=new ActorSystemSettings(3);
        DefaultActorSystem system=new DefaultActorSystem(settings);
        system.createDispatcher("ticket", Executors.newWorkStealingPool(4));
        ActorRef ref= system.createRootActor("ticket",new TicketingActor.TicketingActorCreator());
        System.out.println(ref.getActorId());
        Thread thread=new Thread(()->{
            for (int i = 0; i < 10; i++) {
                ref.tell(new IntTbActorMsg(1));
            }

        },"窗口1");
        Thread thread2=new Thread(()->{
            for (int i = 0; i < 10; i++) {
              ref.tell(new IntTbActorMsg(1));
            }
        },"窗口2");
        thread.start();
         thread2.start();
        Thread.sleep(10000);
    }
}
