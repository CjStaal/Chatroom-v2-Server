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

import com.staalcomputingsolutions.chatroom.server.messaging.executor.Consumer;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * The generic queue, it is used for input, output, and system mesages.
 * 
 * @author Charles Joseph Staal
 * @param <T> The type of message.
 */
public final class Queue<T> {

    private final BlockingQueue<T> queue;

    private final Consumer queueExecutor;

    public Queue(Consumer queueExecutor) {
        this.queue = new LinkedBlockingQueue();
        this.queueExecutor = queueExecutor;
    }

    public synchronized boolean add(T message) {
        if (this.queue.add(message)) {
            this.queueExecutor.start();
            return true;
        } else {
            return false;
        }
    }

    public synchronized void clear() {
        this.queue.clear();
    }

    public synchronized T element() {
        return this.queue.element();
    }

    public synchronized boolean isEmpty() {
        return this.queue.isEmpty();
    }

    public synchronized Iterator iterator() {
        return this.queue.iterator();
    }

    public synchronized T peek() {
        return this.queue.peek();
    }

    public synchronized T poll() {
        return this.queue.poll();
    }

    public synchronized void put(T message) throws InterruptedException {
        this.queue.put(message);
    }

    public synchronized int remainingCapacity() {
        return this.queue.remainingCapacity();
    }

    public synchronized int size() {
        return this.queue.size();
    }

    public synchronized T take() throws InterruptedException {
        return this.queue.take();
    }
}
