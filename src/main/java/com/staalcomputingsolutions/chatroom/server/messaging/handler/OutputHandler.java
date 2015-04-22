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
package com.staalcomputingsolutions.chatroom.server.messaging.handler;

import com.staalcomputingsolutions.chatroom.server.impl.Communicator;
import com.staalcomputingsolutions.chatroom.server.messaging.messages.ChatMessage;

/**
 *
 * @author Charles Joseph Staal
 */
public class OutputHandler implements Handler {

    private Communicator communicator;

    public OutputHandler(Communicator communicator) {
        this.communicator = communicator;
    }

    @Override
    public void handleMessage(Object message) {
        if (message instanceof ChatMessage) {
            ChatMessage cm = (ChatMessage) message;
            String from = cm.getSenderPrivateUUID();
            String info[] = cm.getMessage().split("|");
            StringBuilder sb = new StringBuilder();
            if(info[0].equals("CHAT")){
                sb.append("CHAT|");
                
            } else if(info[0].equals("SYSTEM")){
                sb.append("SYSTEM|");
            }
            String info2[] = info[1].split(":");
            if(info2[0].equals("PUBLIC")){
            } else if()
        }

    }

}
