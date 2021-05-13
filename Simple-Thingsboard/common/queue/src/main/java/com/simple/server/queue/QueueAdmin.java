package com.simple.server.queue;

public interface QueueAdmin {
    void createTopicIfNotExists(String topic);
    void destroy();
}
