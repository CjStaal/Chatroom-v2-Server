/*
 * Copyright [2015] [Charles Joseph Staal]
 */
package com.staalcomputingsolutions.chatroom.server.messaging.queues.system;

import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author Charles Joseph Staal
 */
public class SystemQueue {

    private final BlockingQueue<SystemMessage> systemQueue;

    private final SystemQueueExecutor systemQueueExecutor;

    /**
     * <strong>DO NOT USE DIRECTLY</strong>
     * This is the constructor for the SystemQueue.
     */
    public SystemQueue() {
        this.systemQueue = new LinkedBlockingQueue();
        this.systemQueueExecutor = new SystemQueueExecutor();
    }

    /**
     * This is the method used to add messages to the queue.
     *
     * @param message
     * @return
     */
    public synchronized boolean add(SystemMessage message) {
        if (this.systemQueue.add(message)) {
            this.systemQueueExecutor.start();
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @
     */
    public synchronized void clear() {
        this.systemQueue.clear();
    }

    /**
     *
     * @return @
     */
    public synchronized SystemMessage element() {
        return this.systemQueue.element();
    }

    /**
     *
     * @return @
     */
    public synchronized boolean isEmpty() {
        return this.systemQueue.isEmpty();
    }

    /**
     *
     * @return @
     */
    public synchronized Iterator iterator() {
        return this.systemQueue.iterator();
    }

    /**
     *
     * @return @
     */
    public synchronized SystemMessage peek() {
        return this.systemQueue.peek();
    }

    /**
     *
     * @return @
     */
    public synchronized SystemMessage poll() {
        return this.systemQueue.poll();
    }

    /**
     *
     * @param message
     * @throws InterruptedException
     * @
     */
    public synchronized void put(SystemMessage message) throws InterruptedException {
        this.systemQueue.put(message);
    }

    /**
     *
     * @return @
     */
    public synchronized int remainingCapacity() {
        return this.systemQueue.remainingCapacity();
    }

    /**
     *
     * @return @
     */
    public synchronized int size() {
        return this.systemQueue.size();
    }

    /**
     *
     * @return @throws InterruptedException
     */
    public synchronized SystemMessage take() throws InterruptedException {
        return this.systemQueue.take();
    }
}
