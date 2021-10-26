package com.simple.actor.test;


import com.simple.thingsboard.server.actor.ActorCreator;
import com.simple.thingsboard.server.actor.ActorRef;
import com.simple.thingsboard.server.actor.ActorSystem;
import com.simple.thingsboard.server.actor.ActorSystemSettings;
import com.simple.thingsboard.server.actor.core.DefaultActorSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class ActorSystemDemo {
    public static final String ROOT_DISPATCHER = "root-dispatcher";
    private static volatile ActorSystem actorSystem;
    private static volatile ExecutorService submitPool;
    private static int parallelism=10;
    public static void  main(String[] args) throws InterruptedException {
        //Actor 配置
       ActorSystemSettings settings = new ActorSystemSettings(3);
        //初始化
        actorSystem = new DefaultActorSystem(settings);
        submitPool = Executors.newWorkStealingPool(parallelism);
        actorSystem.createDispatcher(ROOT_DISPATCHER, Executors.newWorkStealingPool(parallelism));
        testActorsAndMessages(1, 10, 10);
    }
    public static void testActorsAndMessages(int actorsCount, int msgNumber, int times) throws InterruptedException {
        //求和
        Random random=new Random();
        int[] randomIntegers = new int[msgNumber];
        long sumTmp = 0;
        for (int i = 0; i < msgNumber; i++) {
            int temp=random.nextInt(10)+1;
            randomIntegers[i] = temp;
            sumTmp += temp;
        }
        long expected = sumTmp;

        List<ActorTestCtx> testCtxes = new ArrayList<>();

        /**
         * actor 集合
         **/
        List<ActorRef> actorRefs = new ArrayList<>();

        // 创建Actor
        for (int actorIdx = 0; actorIdx < actorsCount; actorIdx++) {
            ActorTestCtx testCtx = getActorTestCtx(msgNumber);
            testCtxes.add(testCtx);

            //Actor 创建者 id
            UUID id=UUID.randomUUID();
            //Actor创建者，定义了actor的执行逻辑
            ActorCreator tbActorCreator=new TestRootActor.TestRootActorCreator(id , testCtx);
            ActorRef actorRef=actorSystem.createRootActor(ROOT_DISPATCHER, tbActorCreator);
            actorRefs.add(actorRef);
        }

        for (int t = 0; t < times; t++) {
            for (int i = 0; i < msgNumber; i++) {
                int tmp = randomIntegers[i];
                submitPool.execute(() -> actorRefs.forEach(actorId -> actorId.tell(new IntTbActorMsg(tmp))));
            }
            testCtxes.forEach(ctx -> {
                try {
                    boolean success = ctx.getLatch().await(1, TimeUnit.MINUTES);
                    if (!success) {

                    }
                    System.out.println( "期望值【"+expected+"】 实际值 【"+ctx.getActual().get()+"】"+"数据量【"+msgNumber+"】 调用次数 【"+ctx.getInvocationCount().get()+"】");
                    ctx.clear();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
    private static  ActorTestCtx getActorTestCtx(int i) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        AtomicLong actual = new AtomicLong();
        AtomicInteger invocations = new AtomicInteger();
        return new ActorTestCtx(countDownLatch, invocations, i, actual);
    }
}
