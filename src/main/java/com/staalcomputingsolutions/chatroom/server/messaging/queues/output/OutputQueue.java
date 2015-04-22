/*
 * Copyright [2015] [Charles Joseph Staal]
 */
package com.staalcomputingsolutions.chatroom.server.messaging.queues.output;

import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * This is the object used to queue the messages that are outgoing. This is a
 * class, initialize in the creation of the server and then only use the methods
 * there on out.
 *
 * This object uses the singleton pattern to ensure there is only one of these
 * objects ever created.
 *
 * This class wraps the LinkedBlockinQueue class.
 *
 * Call {@link #getInstance()} to obtain the class object.
 *
 * @author Charles Joseph Staal
 */
public final class OutputQueue {

    private final BlockingQueue<ChatMessage> outputQueue;

    private final OutputQueueExecutor outputQueueExecutor;

    /**
     * <strong>DO NOT USE DIRECTLY</strong>
     * This is the constructor for the OutputQueue.
     */
    public OutputQueue() {
        this.outputQueue = new LinkedBlockingQueue();
        this.outputQueueExecutor = new OutputQueueExecutor();
    }

    /**
     * This is the method used to add messages to the queue.
     *
     * @param message
     * @return
     */
    public synchronized boolean add(ChatMessage message) {
        if (this.outputQueue.add(message)) {
            this.outputQueueExecutor.start();
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
        this.outputQueue.clear();
    }

    /**
     *
     * @return @
     */
    public synchronized ChatMessage element() {
        return this.outputQueue.element();
    }

    /**
     *
     * @return @
     */
    public synchronized boolean isEmpty() {
        return this.outputQueue.isEmpty();
    }

    /**
     *
     * @return @
     */
    public synchronized Iterator iterator() {
        return this.outputQueue.iterator();
    }

    /**
     *
     * @return @
     */
    public synchronized ChatMessage peek() {
        return this.outputQueue.peek();
    }

    /**
     *
     * @return @
     */
    public synchronized ChatMessage poll() {
        return this.outputQueue.poll();
    }

    /**
     *
     * @param message
     * @throws InterruptedException
     * @
     */
    public synchronized void put(ChatMessage message) throws InterruptedException {
        this.outputQueue.put(message);
    }

    /**
     *
     * @return @
     */
    public synchronized int remainingCapacity() {
        return this.outputQueue.remainingCapacity();
    }

    /**
     *
     * @return @
     */
    public synchronized int size() {
        return this.outputQueue.size();
    }

    /**
     *
     * @return @throws InterruptedException
     */
    public synchronized ChatMessage take() throws InterruptedException {
        return this.outputQueue.take();
    }
}
