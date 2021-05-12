package com.simple.thingsboard.server.actor;

import com.simple.server.common.msg.ActorMsg;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.function.Predicate;

/**
 * Actor 系统 启动
 */
public interface ActorSystem {
    /**
     * 创建调度员
     * @param dispatcherId
     * @param executor
     */
    void createDispatcher(String dispatcherId, ExecutorService executor);

    /**
     *  销毁方法
     * @param dispatcherId
     */
    void destroyDispatcher(String dispatcherId);

    /**
     *  获取系统中指定的Actor的地址
     * @param actorId
     * @return
     */
    ActorRef getActor(UUID actorId);

    /**
     * 创建 Root Actor
     * @param dispatcherId 调度ID
     * @param creator 创建者
     * @return
     */
    ActorRef createRootActor(String dispatcherId, ActorCreator creator);

    /**
     *  创建 子 Actor
     * @param dispatcherId
     * @param creator
     * @param parent 父级Actor地址
     * @return
     */
    ActorRef createChildActor(String dispatcherId, ActorCreator creator, UUID parent);

    void tell(UUID target, ActorMsg actorMsg);

    void tellWithHighPriority(UUID target, ActorMsg actorMsg);

    void stop(ActorRef actorRef);

    /**
     * 停止
     *  使用ActorSystem的停止函数
     *   会递归判断其子Actor停止
     * @param actorId
     */
    void stop(UUID actorId);

    void stop();

    /**
     * 广播
     * @param parent
     * @param msg
     */
    void broadcastToChildren(UUID parent, ActorMsg msg);
    /**
     * 广播
     * @param parent
     * @param msg
     */
    void broadcastToChildren(UUID parent, Predicate<UUID> childFilter, ActorMsg msg);

    List<UUID> filterChildren(UUID parent, Predicate<UUID> childFilter);
}
