package com.simple.thingsboard.server.actor;

import com.simple.server.common.msg.ActorMsg;

import java.util.UUID;

/**
 * 定义Actor 邮箱基础功能 发送消息
 * tell（）
 * tellWithHighPriority（）
 * 优先级不同
 */
public interface ActorRef {
    /**
     * 获取ActorId
     * @return
     */
    UUID getActorId();

    /**
     * 发送消息
     */
   void tell(ActorMsg actorMsg);

    /**
     * 发送消息
     */
   void tellWithHighPriority(ActorMsg actorMsg);
}
