/**
 * Copyright © 2016-2020 The Thingsboard Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.simple.actor.test;

import com.simple.server.common.msg.ActorMsg;
import com.simple.thingsboard.server.actor.Actor;
import com.simple.thingsboard.server.actor.ActorCreator;
import com.simple.thingsboard.server.actor.ActorCtx;
import com.simple.thingsboard.server.actor.core.AbstractActor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

/**
 * actor 执行单元
 */
@Slf4j
public class TestRootActor extends AbstractActor {

    @Getter
    private final UUID actorId;
    @Getter
    private final ActorTestCtx testCtx;

    private boolean initialized;
    private long sum;
    private int count;

    public TestRootActor(UUID actorId, ActorTestCtx testCtx) {
        this.actorId = actorId;
        this.testCtx = testCtx;
    }

    @Override
    public void init(ActorCtx ctx) throws Exception {
        super.init(ctx);
        initialized = true;
    }

    @Override
    public boolean process(ActorMsg msg) throws InterruptedException {
        if (initialized) {
            int value = ((IntTbActorMsg) msg).getValue();
            sum += value;
            count += 1;
            //通过记录次数 将sum置位0
            //将计算完毕的结果返回主线程
            Thread.sleep(10);
            if (count == testCtx.getExpectedInvocationCount()) {
                //返回结果
                testCtx.getActual().set(sum);
                //实际执行次数
                testCtx.getInvocationCount().addAndGet(count);
                //actor状态置位
                sum = 0;
                count = 0;
                //停止线程
                testCtx.getLatch().countDown();
            }
        }
        return true;
    }

    @Override
    public void destroy() {

    }

    public static class TestRootActorCreator implements ActorCreator {

        private final UUID actorId;
        private final ActorTestCtx testCtx;

        public TestRootActorCreator(UUID actorId, ActorTestCtx testCtx) {
            this.actorId = actorId;
            this.testCtx = testCtx;
        }

        @Override
        public UUID createActorId() {
            return actorId;
        }

        @Override
        public Actor createActor() {
            return new TestRootActor(actorId, testCtx);
        }
    }
}
