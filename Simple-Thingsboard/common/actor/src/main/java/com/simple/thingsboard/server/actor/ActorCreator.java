package com.simple.thingsboard.server.actor;

import java.util.UUID;

/**
 * Actor 创建者接口
 */
public interface ActorCreator {
    UUID createActorId();
    Actor createActor();
}
