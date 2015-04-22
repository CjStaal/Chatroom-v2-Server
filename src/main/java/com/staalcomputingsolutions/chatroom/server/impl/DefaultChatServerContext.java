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
package com.staalcomputingsolutions.chatroom.server.impl;

import com.staalcomputingsolutions.chatroom.server.messaging.executor.Executor;
import com.staalcomputingsolutions.chatroom.server.messaging.handler.Handler;
import com.staalcomputingsolutions.chatroom.server.messaging.messages.ChatMessage;
import com.staalcomputingsolutions.chatroom.server.messaging.messages.Message;
import com.staalcomputingsolutions.chatroom.server.messaging.messages.SystemMessage;
import com.staalcomputingsolutions.chatroom.server.messaging.queue.Queue;

/**
 *
 * @author Charles Joseph Staal
 */
public class DefaultChatServerContext implements ChatServerContext{

    private Queue<Message> inputQueue;
    private Queue<ChatMessage> outputQueue;
    private Queue<SystemMessage> systemQueue;

    private Executor inputExecutor;
    private Executor outputExecutor;
    private Executor systemExecutor;
    
    private Handler inputHandler;
    private Handler outputHandler;
    private Handler systemHandler;

    public DefaultChatServerContext() {

        inputExecutor = new Executor();
        outputExecutor = new Executor();
        systemExecutor = new Executor();

        inputQueue = new Queue(inputExecutor);
        outputQueue = new Queue(outputExecutor);
        systemQueue = new Queue(systemExecutor);
        
        inputExecutor.setQueue(inputQueue);
        outputExecutor.setQueue(outputQueue);
        systemExecutor.setQueue(systemQueue);
    }

    @Override
    public Executor getInputExecutor() {
        return inputExecutor;
    }

    @Override
    public Executor getOutputExecutor() {
        return outputExecutor;
    }

    @Override
    public Executor getSystemExecutor() {
        return systemExecutor;
    }

    @Override
    public Queue<Message> getInputQueue() {
        return this.inputQueue;
    }

    @Override
    public Queue<ChatMessage> getOutputQueue() {
        return this.outputQueue;
    }

    @Override
    public Queue<SystemMessage> getSystemQueue() {
        return this.systemQueue;
    }

    public void setInputExecutor(Executor inputExecutor) {
        this.inputExecutor = inputExecutor;
    }

    public void setOutputExecutor(Executor outputExecutor) {
        this.outputExecutor = outputExecutor;
    }

    public void setSystemExecutor(Executor systemExecutor) {
        this.systemExecutor = systemExecutor;
    }

    public void setInputQueue(Queue<Message> inputQueue) {
        this.inputQueue = inputQueue;
    }

    public void setOutputQueue(Queue<ChatMessage> outputQueue) {
        this.outputQueue = outputQueue;
    }

    public void setSystemQueue(Queue<SystemMessage> systemQueue) {
        this.systemQueue = systemQueue;
    }
}
