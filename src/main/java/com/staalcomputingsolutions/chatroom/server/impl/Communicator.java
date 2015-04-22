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

/**
 * This class is used to communicate to and from the clients.
 *
 * @author Charles Joseph Staal
 */
public class Communicator {

    private UserManager userManager;

    private boolean started = false;
    
    public Communicator(UserManager userManager) {
        this.userManager = userManager;
    }

    private void sendMessage(String message) {
        for (UserConnection uc : this.userManager.getConnectionMap().values()) {
            if (!uc.sendMessage(message)) {
                this.userManager.removeClient(uc.getPrivateUUID());
            }
        }
    }

    public void start(){
        this.started = true;
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
