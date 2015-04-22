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

import com.staalcomputingsolutions.chatroom.server.users.UserConnection;
import com.staalcomputingsolutions.chatroom.server.users.UserManager;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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

    private boolean started = false;

    private static final Logger logger = LoggerFactory.getLogger(Communicator.class);

    public Communicator(UserManager userManager) {
        logger.debug("Creating communicator object.");
        this.userManager = userManager;
        receivingThread = Executors.newSingleThreadExecutor();
        logger.debug("Created communicator object.");
    }

    private void sendMessage(String message) {
        for (UserConnection uc : this.userManager.getConnectionMap().values()) {
            if (!uc.sendMessage(message)) {
                this.userManager.removeClient(uc.getPrivateUUID());
            }
        }
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
                for (UserConnection uc : userManager.getConnectionMap().values()) {
                    if (!uc.receiveMessage()) {
                        userManager.removeClient(uc.getPrivateUUID());
                    }
                }
            }
        }

    }
}
