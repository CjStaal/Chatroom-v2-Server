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
import com.staalcomputingsolutions.chatroom.server.messaging.handler.InputHandler;
import com.staalcomputingsolutions.chatroom.server.messaging.handler.OutputHandler;
import com.staalcomputingsolutions.chatroom.server.messaging.handler.SystemHandler;
import com.staalcomputingsolutions.chatroom.server.messaging.messages.ChatMessage;
import com.staalcomputingsolutions.chatroom.server.messaging.messages.Message;
import com.staalcomputingsolutions.chatroom.server.messaging.messages.SystemMessage;
import com.staalcomputingsolutions.chatroom.server.messaging.queue.Queue;
import com.staalcomputingsolutions.chatroom.server.users.UserManager;

/**
 *
 * @author Charles Joseph Staal
 */
public class DefaultChatServerContext implements ChatServerContext {

    private UserManager userManager;

    private Queue<Message> inputQueue;
    private Queue<ChatMessage> outputQueue;
    private Queue<SystemMessage> systemQueue;

    private Executor inputExecutor;
    private Executor outputExecutor;
    private Executor systemExecutor;

    private Handler<Message> inputHandler;
    private Handler<ChatMessage> outputHandler;
    private Handler<SystemMessage> systemHandler;

    private Communicator communicator;

    public DefaultChatServerContext() {

        inputExecutor = new Executor();
        outputExecutor = new Executor();
        systemExecutor = new Executor();

        inputQueue = new Queue(inputExecutor);
        outputQueue = new Queue(outputExecutor);
        systemQueue = new Queue(systemExecutor);

        inputHandler = new InputHandler(systemQueue, outputQueue);
        outputHandler = new OutputHandler(communicator);
        systemHandler = new SystemHandler();

        inputExecutor.setQueue(inputQueue);
        inputExecutor.setHandler(inputHandler);

        outputExecutor.setQueue(outputQueue);
        outputExecutor.setHandler(outputHandler);

        systemExecutor.setQueue(systemQueue);
        systemExecutor.setHandler(systemHandler);

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

    public UserManager getUserManager() {
        return userManager;
    }

    public Handler<Message> getInputHandler() {
        return inputHandler;
    }

    public Handler<ChatMessage> getOutputHandler() {
        return outputHandler;
    }

    public Handler<SystemMessage> getSystemHandler() {
        return systemHandler;
    }

    public Communicator getCommunicator() {
        return communicator;
    }

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    public void setInputHandler(Handler<Message> inputHandler) {
        this.inputHandler = inputHandler;
    }

    public void setOutputHandler(Handler<ChatMessage> outputHandler) {
        this.outputHandler = outputHandler;
    }

    public void setSystemHandler(Handler<SystemMessage> systemHandler) {
        this.systemHandler = systemHandler;
    }

    public void setCommunicator(Communicator communicator) {
        this.communicator = communicator;
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
