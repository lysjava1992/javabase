package com.simple.thingsboard.server.actor.core;

import lombok.Data;

import java.util.concurrent.ExecutorService;

/**
 * 调度员
 *  实质是一个线程池
 *   不同等级的actor,用不同的线程池来控制创建
 *   来控制不同等级actor的线程数量
 */
@Data
public class Dispatcher {
   private final String dispatcherId;
   private final ExecutorService executor;
}
