package com.simple.thingsboard.server.actor;

import com.simple.server.common.msg.ActorMsg;

import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Actor 上下文的维护
 */
public interface ActorCtx extends ActorRef{
     UUID getSelf();

    /**
     *  返回父级Actor的地址
     * @return
     */
     ActorRef getParentRef();

    /**
     *
     * @param target 目标Actor邮箱 Id
     * @param msg 消息
     */
     void tell(UUID target, ActorMsg msg);

    /**
     * 停止
     * @param target
     */
     void stop(UUID target);

    /**
     *
     * @param actorId Id
     * @param dispatcher 调度
     * @param creator 创建者
     * @return Actor地址
     */
     ActorRef getOrCreateChildActor(UUID actorId, Supplier<String> dispatcher,Supplier<ActorCreator>creator);

    /**
     * 向子Actor 广播消息
     * @param msg
     */
    void broadcastToChildren(ActorMsg msg);

    /**
     * 向子Actor 广播消息
     *  Predicate<UUID> 过滤规则
     * @param msg
     * @param childFilter
     */
    void broadcastToChildren(ActorMsg msg, Predicate<UUID> childFilter);

    /**
     *  获取指定的子Actor 邮箱
     * @param childFilter 过滤规则
     * @return
     */
    List<UUID> filterChildren(Predicate<UUID> childFilter);
}
