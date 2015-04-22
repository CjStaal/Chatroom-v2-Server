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

import com.staalcomputingsolutions.chatroom.server.messaging.handler.MessageBuilder;
import com.staalcomputingsolutions.chatroom.server.messaging.messages.Message;
import com.staalcomputingsolutions.chatroom.server.messaging.queue.Queue;
import com.staalcomputingsolutions.chatroom.server.users.UserConnection;
import com.staalcomputingsolutions.chatroom.server.users.UserManager;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class is used to communicate to and from the clients.
 *
 * @author Charles Joseph Staal
 */
public class Communicator {

    private final UserManager userManager;
    private final ExecutorService receivingThread;
    private final Queue<Message> inputQueue;
    private boolean started = false;

    private static final Logger logger = LoggerFactory.getLogger(Communicator.class);

    public Communicator(UserManager userManager, Queue<Message> inputQueue) {
        logger.debug("Creating communicator object.");
        this.userManager = userManager;
        this.inputQueue = inputQueue;
        receivingThread = Executors.newSingleThreadExecutor();
        logger.debug("Created communicator object.");
    }

    public void sendChatMessage(String message){
        sendMessage("CHAT{PUBLIC:MSG=" + message + "}");
                
    }
    
    public void sendSystemMessage(String message){
        sendMessage("SYSTEM{" + message + "}");
    }
    
    public void sendPrivateSystemMessage(String privateUUIDOfReceiver, String message){
        userManager.getUserConnectionFromPrivateUUID(privateUUIDOfReceiver).sendMessage(message);
    }
    
    private void sendMessage(String message) {
        for (UserConnection uc : this.userManager.getUserList()) {
            if (!uc.sendMessage(message)) {
                this.userManager.removeClient(uc.getPrivateUUID());
            }
        }
    }
    
    public void sendPrivateMessage(String publicUUIDOfReceiver, String message){
        String privateUUIDOfReceiver = userManager.getPrivateUUIDFromPublicUUID(publicUUIDOfReceiver);
        userManager.getUserConnectionFromPrivateUUID(privateUUIDOfReceiver).sendMessage(message);
    }

    public void start() {
        if (this.started) {
            logger.info("Communicator started already.");
        } else {
            logger.info("Starting communicator.");
            this.started = true;
            receivingThread.execute(new MessageReceiver());
        }
    }

    public void stop() {
        if (this.started) {
            logger.info("Stopping communicator.");
            this.started = false;
            receivingThread.shutdown();
            logger.info("Communicator stopped.");
        } else {
            logger.info("Communicator already stopped.");
        }
    }
    
    public void restart(){
        this.stop();
        this.start();
    }

    public class MessageReceiver implements Runnable {

        @Override
        public void run() {
            while (started) {
                for (UserConnection uc : userManager.getUserList()) {
                    try {
                        if(uc.messageAvailable()){
                            inputQueue.add(MessageBuilder.buildMessage(uc.getPrivateUUID(), uc.receiveMessage()));
                        }
                    } catch (IOException ex) {
                        java.util.logging.Logger.getLogger(Communicator.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }

    }
}
