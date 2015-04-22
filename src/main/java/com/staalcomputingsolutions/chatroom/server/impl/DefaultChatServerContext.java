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

import com.staalcomputingsolutions.chatroom.server.messaging.executors.InputExecutor;
import com.staalcomputingsolutions.chatroom.server.messaging.executors.OutputExecutor;
import com.staalcomputingsolutions.chatroom.server.messaging.executors.SystemExecutor;
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

    private InputExecutor inputExecutor;
    private OutputExecutor outputExecutor;
    private SystemExecutor systemExecutor;

    public DefaultChatServerContext() {

        inputExecutor = new InputExecutor();
        outputExecutor = new OutputExecutor();
        systemExecutor = new SystemExecutor();

        inputQueue = new Queue(inputExecutor);
        outputQueue = new Queue(outputExecutor);
        systemQueue = new Queue(systemExecutor);
    }

    @Override
    public InputExecutor getInputExecutor() {
        return inputExecutor;
    }

    @Override
    public OutputExecutor getOutputExecutor() {
        return outputExecutor;
    }

    @Override
    public SystemExecutor getSystemExecutor() {
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

    public void setInputExecutor(InputExecutor inputExecutor) {
        this.inputExecutor = inputExecutor;
    }

    public void setOutputExecutor(OutputExecutor outputExecutor) {
        this.outputExecutor = outputExecutor;
    }

    public void setSystemExecutor(SystemExecutor systemExecutor) {
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
