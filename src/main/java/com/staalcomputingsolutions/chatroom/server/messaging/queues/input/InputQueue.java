/* 
 * Copyright (C) 2015 Charles Joseph Staal
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.staalcomputingsolutions.chatroom.server.messaging.queues.input;

import com.staalcomputingsolutions.chatroom.server.messaging.queues.output.ChatMessage;
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
public final class InputQueue {

    private BlockingQueue<Message> inputQueue;

    private InputQueueExecutor inputQueueExecutor;

    /**
     * <strong>DO NOT USE DIRECTLY</strong>
     * This is the constructor for the 
     */
    public InputQueue() {
        this.inputQueue = new LinkedBlockingQueue();
        this.inputQueueExecutor = new InputQueueExecutor();
    }

    /**
     * This is the method used to add messages to the queue.
     *
     * @param message
     * @return
     */
    public synchronized boolean add(Message message) {
        if (this.inputQueue.add(message)) {
            this.inputQueueExecutor.start();
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
        this.inputQueue.clear();
    }

    /**
     *
     * @return @
     */
    public synchronized Message element() {
        return this.inputQueue.element();
    }

    /**
     *
     * @return @
     */
    public synchronized boolean isEmpty() {
        return this.inputQueue.isEmpty();
    }

    /**
     *
     * @return @
     */
    public synchronized Iterator iterator() {
        return this.inputQueue.iterator();
    }

    /**
     *
     * @return @
     */
    public synchronized Message peek() {
        return this.inputQueue.peek();
    }

    /**
     *
     * @return @
     */
    public synchronized Message poll() {
        return this.inputQueue.poll();
    }

    /**
     *
     * @param message
     * @throws InterruptedException
     * @
     */
    public synchronized void put(Message message) throws InterruptedException {
        this.inputQueue.put(message);
    }

    /**
     *
     * @return @
     */
    public synchronized int remainingCapacity() {
        return this.inputQueue.remainingCapacity();
    }

    /**
     *
     * @return @
     */
    public synchronized int size() {
        return this.inputQueue.size();
    }

    /**
     *
     * @return @throws InterruptedException
     */
    public synchronized Message take() throws InterruptedException {
        return this.inputQueue.take();
    }
}