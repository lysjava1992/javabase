package com.simple.actor.test.ticket;

import com.simple.server.common.msg.ActorMsg;
import com.simple.thingsboard.server.actor.Actor;
import com.simple.thingsboard.server.actor.ActorCreator;
import com.simple.thingsboard.server.actor.core.AbstractActor;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class TicketingActor extends AbstractActor {
    private AtomicInteger tickets=new AtomicInteger(10);
    @Override
    public boolean process(ActorMsg msg) throws Exception {
        if (tickets.get()>0){
            System.out.println(Thread.currentThread().getName()+" 出售第"+(10-tickets.get()+1) +"张");
            Thread.sleep(1);
            tickets.getAndDecrement();
            return true;
        }
        System.out.println("无票");
        return false;
    }
    public static class TicketingActorCreator implements ActorCreator{

        @Override
        public UUID createActorId() {
            return UUID.randomUUID();
        }

        @Override
        public Actor createActor() {
            return new TicketingActor();
        }
    }
}
