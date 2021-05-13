package com.simple.thingsboard.server.actor.core;

import com.simple.server.common.msg.ActorMsg;
import com.simple.thingsboard.server.actor.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

@Slf4j
@Data
public class DefaultActorSystem implements ActorSystem {
    /**
     * 调度线程池
     */
    ConcurrentMap<String, Dispatcher> dispatchers = new ConcurrentHashMap();
    /**
     * ID
     * 邮箱
     */
    ConcurrentMap<UUID, ActorMailbox> actors = new ConcurrentHashMap<>();
    /**
     * 父级id
     * 子级id集合
     */
    ConcurrentMap<UUID, Set<UUID>> parentChildMap = new ConcurrentHashMap<>();
    private final ActorSystemSettings settings;

    public DefaultActorSystem(ActorSystemSettings settings) {
        this.settings = settings;
    }


    @Override
    public void createDispatcher(String dispatcherId, ExecutorService executor) {
        dispatchers.putIfAbsent(dispatcherId, new Dispatcher(dispatcherId, executor));
    }

    /**
     * 关闭相应的线程池
     *
     * @param dispatcherId
     */
    @Override
    public void destroyDispatcher(String dispatcherId) {
        Dispatcher dispatcher = dispatchers.remove(dispatcherId);
        if (dispatcher != null) {
            dispatcher.getExecutor().shutdownNow();
        }
    }

    @Override
    public ActorRef getActor(UUID actorId) {
        return actors.get(actorId);
    }

    @Override
    public ActorRef createRootActor(String dispatcherId, ActorCreator creator) {
        return createActor(dispatcherId, creator, null);
    }


    @Override
    public ActorRef createChildActor(String dispatcherId, ActorCreator creator, UUID parent) {
        return createActor(dispatcherId, creator, parent);
    }

    private ActorRef createActor(String dispatcherId, ActorCreator creator, UUID parent) {
        Dispatcher dispatcher = dispatchers.get(dispatcherId);
        if (dispatcher == null) {
            log.warn("[{}] 调度员不存在", dispatcherId);
            throw new RuntimeException("[" + dispatcherId + "] 调度员不存在,未注册");
        }
        UUID actorId = creator.createActorId();
        ActorMailbox actorMailbox = actors.get(actorId);
        if (actorMailbox != null) {
            log.debug("[{}]Actor已经注册", actorId);
        } else {
            Actor actor = creator.createActor();
            ActorRef parentRfe = null;
            if (parent != null) {
                parentRfe = getActor(parent);
                if (parentRfe == null) {
                    throw new RuntimeException("父级Actor[" + parent + "] 未注册");
                }
            }
            ActorMailbox mailbox = new ActorMailbox(settings, this, dispatcher, actorId,actor, parentRfe);
            actorMailbox=mailbox;
            actors.put(actorId, mailbox);
            mailbox.initActor();
            if (parent != null) {
                parentChildMap.computeIfAbsent(parent, id -> ConcurrentHashMap.newKeySet()).add(actorId);
            }
        }
        return actorMailbox;
    }

    @Override
    public void tell(UUID target, ActorMsg actorMsg) {
        tell(target, actorMsg, false);
    }

    @Override
    public void tellWithHighPriority(UUID target, ActorMsg actorMsg) {
        tell(target, actorMsg, true);
    }

    public void tell(UUID target, ActorMsg actorMsg, boolean priority) {
        ActorMailbox mailbox = actors.get(target);
        if (mailbox == null) {
            throw new RuntimeException("目标Actor[" + target + "]未注册");
        }
        if (priority) {
            mailbox.tellWithHighPriority(actorMsg);
        } else {
            mailbox.tell(actorMsg);
        }
    }

    @Override
    public void stop(ActorRef actorRef) {
        stop(actorRef.getActorId());
    }

    @Override
    public void stop(UUID actorId) {
        Set<UUID> childes = parentChildMap.get(actorId);
        if (childes != null) {
            for (UUID child : childes) {
                stop(child);
            }
        }
        ActorMailbox actorMailbox = actors.remove(actorId);
        if (actorMailbox != null) {
            actorMailbox.destroy();
        }
    }

    @Override
    public void stop() {
        dispatchers.values().forEach(dispatcher ->
        {
            dispatcher.getExecutor().shutdownNow();
            try {
                dispatcher.getExecutor().awaitTermination(3, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                log.warn("[{}]dispatcher关闭失败", dispatcher.getDispatcherId());
            }
        });
    }

    @Override
    public void broadcastToChildren(UUID parent, ActorMsg msg) {
        broadcastToChildren(parent, uuid -> true, msg);
    }

    @Override
    public void broadcastToChildren(UUID parent, Predicate<UUID> childFilter, ActorMsg msg) {
        Set<UUID> childes = parentChildMap.get(parent);
        if (childes != null) {
            childes.stream().filter(childFilter).forEach(uuid -> tell(uuid, msg));
        }
    }

    @Override
    public List<UUID> filterChildren(UUID parent, Predicate<UUID> childFilter) {
        return null;
    }
}
