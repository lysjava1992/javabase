package com.simple.thingsboard.server.actor.core;

import com.simple.server.common.msg.ActorMsg;
import com.simple.thingsboard.server.actor.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 邮箱
 *  一个邮箱对应一个Actor
 */
@Slf4j
@Data
public class ActorMailbox implements ActorCtx {
    private final ActorSystemSettings settings;
    /**
     * 系统
     */
   private final ActorSystem system;
    /**
     * 调度线程池
     */
   private final Dispatcher dispatcher;
    /**
     * 自己地址的ID
     */
   private final UUID selfId;
    /**
     * 自己的Actor
     */
   private final Actor actor;
    /**
     * 父Actor地址
     */
   private final ActorRef parentRef;

   // 邮箱队列
    ConcurrentLinkedQueue<ActorMsg> normQueue=new ConcurrentLinkedQueue<>();
    ConcurrentLinkedQueue<ActorMsg> priorityQueue=new ConcurrentLinkedQueue<>();

    // 状态 :是否销毁
    private final AtomicBoolean destroy=new AtomicBoolean(false);
   // 状态 :是否工作状态
    private final AtomicBoolean ready=new AtomicBoolean(false);
    /**
     * 状态 : 工作状态
     *  邮箱接收消息 Actor  actor.process(msg)处理
     *  消息接收与处理为异步
     *   actor.process(msg)是同步
     *
     */
    private final AtomicBoolean busy=new AtomicBoolean(false);
    /**
     * 邮箱初始化
     *  即初始化相应的Actor
     *   邮箱与Actor是异步关系
     *    邮箱开始接收消息，而对应的Actor可能还为准备好
     *    邮箱负责接收消息，处理由对应Actor完成
     *
     */
    public void initActor(){
        dispatcher.getExecutor().execute(()->init());
    }

    private void init() {
        try {
            if(!destroy.get()){
                actor.init(this);
                ready.set(true);
                tryProcessQueue();
            }
        }catch (Exception e){
            log.debug("[{}] 初始化Actor时失败:{}",selfId,e);
            system.stop(selfId);
        }

    }

    private void enqueue(ActorMsg msg,boolean priority){
        if(priority){
            priorityQueue.add(msg);
        }else {
            normQueue.add(msg);
        }
        tryProcessQueue();
    }
    // 处理阶段异步，不妨碍消息接收
    private void tryProcessQueue() {
        if(ready.get()){
            if(!priorityQueue.isEmpty()||!normQueue.isEmpty()){
                if(busy.compareAndSet(false,true))
                    // 繁忙时不再执行
                   dispatcher.getExecutor().execute(this::processMailbox);
            }
        }
    }

    private void processMailbox() {
        boolean empty=false;
        for (int i = 0; i < settings.getActorThroughput(); i++) {
            ActorMsg msg=priorityQueue.poll();
            if(msg==null){
                msg=normQueue.poll();
            }
            if(msg!=null){
                try {
                    actor.process(msg);
                } catch (Exception e) {
                   log.debug("[{}]Actor 处理消息失败:{}",selfId,msg,e);
                   if(actor.onProcessFailure(e)){
                       system.stop(selfId);
                   }
                }
            }else {
                empty=true;
                break;
            }
        }
        if(empty){
            busy.set(false);
            dispatcher.getExecutor().execute(this::tryProcessQueue);
        }else {
            dispatcher.getExecutor().execute(this::processMailbox);
        }
    }

    @Override
    public UUID getSelf() {
        return selfId;
    }

    @Override
    public ActorRef getParentRef() {
        return parentRef;
    }

    @Override
    public void tell(UUID target, ActorMsg msg) {
           system.tell(target,msg);
    }

    @Override
    public void stop(UUID target) {
         system.stop(target);
    }


    @Override
    public ActorRef getOrCreateChildActor(UUID actorId, Supplier<String> dispatcher, Supplier<ActorCreator> creator) {
        ActorRef actorRef= system.getActor(actorId);
        if(actorRef==null){
          return   system.createChildActor(dispatcher.get(),creator.get(),selfId);
        }
        return actorRef;
    }

    @Override
    public void broadcastToChildren(ActorMsg msg) {
       system.broadcastToChildren(selfId,msg);
    }

    @Override
    public void broadcastToChildren(ActorMsg msg, Predicate<UUID> childFilter) {
     system.broadcastToChildren(selfId,childFilter,msg);
    }

    @Override
    public List<UUID> filterChildren(Predicate<UUID> childFilter) {
        return system.filterChildren(selfId,childFilter);
    }

    @Override
    public UUID getActorId() {
        return selfId;
    }

    @Override
    public void tell(ActorMsg actorMsg) {
            enqueue(actorMsg,false);
    }

    @Override
    public void tellWithHighPriority(ActorMsg actorMsg) {
        enqueue(actorMsg,true);
    }
    public void destroy(){
        destroy.set(true);
        try {
            ready.set(false);
            actor.destroy();
        } catch (Exception e) {
            log.warn("[{}] Failed to destroy actor: {}", selfId, e);
        }
    }
}
