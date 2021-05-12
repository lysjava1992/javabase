package com.simple.thingsboard.server.actor;

import com.simple.server.common.msg.ActorMsg;

/**
 * Actor 接口
 */
public interface Actor {
    /**
     * 处理邮箱的消息
     * @param msg
     * @return
     */
    boolean process(ActorMsg msg) throws Exception;

    /**
     * 返回
     * @return  Actor的邮箱
     */
    ActorRef getActorRef();

    default void init(ActorCtx ctx) throws Exception {
    }

    default void destroy() throws Exception {
    }

    /**
     * process 异常时处理方案
     * @param t
     * @return
     */
    default boolean onProcessFailure(Throwable t) {
        if (t instanceof Error) {
            return true;
        } else {
            return false;
        }
    }
}
