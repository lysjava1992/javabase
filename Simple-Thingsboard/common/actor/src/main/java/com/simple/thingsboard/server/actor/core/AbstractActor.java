package com.simple.thingsboard.server.actor.core;

import com.simple.server.common.msg.ActorMsg;
import com.simple.thingsboard.server.actor.Actor;
import com.simple.thingsboard.server.actor.ActorCtx;
import com.simple.thingsboard.server.actor.ActorRef;

public abstract class AbstractActor implements Actor {
    protected ActorCtx ctx;
    @Override
    public void init(ActorCtx ctx) throws Exception {
        this.ctx = ctx;
    }
    @Override
    public ActorRef getActorRef() {
        return ctx;
    }
}
