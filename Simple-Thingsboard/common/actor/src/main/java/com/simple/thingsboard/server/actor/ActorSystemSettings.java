package com.simple.thingsboard.server.actor;

import lombok.Data;

@Data
public class ActorSystemSettings {
    /**
     * 指 邮箱从队列中取消息时
     *    一条线程可以处理的最大消息数
     */
    private final int actorThroughput;
}
