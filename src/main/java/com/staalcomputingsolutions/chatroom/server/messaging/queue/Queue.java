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
package com.staalcomputingsolutions.chatroom.server.messaging.queue;

import com.staalcomputingsolutions.chatroom.server.messaging.executors.Executor;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Charles Joseph Staal
 * @param <T> The type of message.
 */
public final class Queue<T> {

    private final BlockingQueue<T> queue;

    private final Executor queueExecutor;

    /**
     * @param queueExecutor
     */
    public Queue(Executor queueExecutor) {
        this.queue = new LinkedBlockingQueue();
        this.queueExecutor = queueExecutor;
    }

    /**
     * This is the method used to add messages to the queue.
     *
     * @param message
     * @return
     */
    public synchronized boolean add(T message) {
        if (this.queue.add(message)) {
            this.queueExecutor.start();
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
        this.queue.clear();
    }

    /**
     *
     * @return @
     */
    public synchronized T element() {
        return this.queue.element();
    }

    /**
     *
     * @return @
     */
    public synchronized boolean isEmpty() {
        return this.queue.isEmpty();
    }

    /**
     *
     * @return @
     */
    public synchronized Iterator iterator() {
        return this.queue.iterator();
    }

    /**
     *
     * @return @
     */
    public synchronized T peek() {
        return this.queue.peek();
    }

    /**
     *
     * @return @
     */
    public synchronized T poll() {
        return this.queue.poll();
    }

    /**
     *
     * @param message
     * @throws InterruptedException
     * @
     */
    public synchronized void put(T message) throws InterruptedException {
        this.queue.put(message);
    }

    /**
     *
     * @return @
     */
    public synchronized int remainingCapacity() {
        return this.queue.remainingCapacity();
    }

    /**
     *
     * @return @
     */
    public synchronized int size() {
        return this.queue.size();
    }

    /**
     *
     * @return @throws InterruptedException
     */
    public synchronized T take() throws InterruptedException {
        return this.queue.take();
    }
}