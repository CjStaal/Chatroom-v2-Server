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

import com.staalcomputingsolutions.chatroom.server.messaging.messages.InputMessage;
import com.staalcomputingsolutions.chatroom.server.messaging.messages.Message;
import com.staalcomputingsolutions.chatroom.server.messaging.messages.OutputMessage;
import com.staalcomputingsolutions.chatroom.server.messaging.messages.SystemMessage;

/**
 * Builds messages.
 * 
 * @author Charles Joseph Staal
 */
public class MessageBuilder {

    public static OutputMessage outputMessage(Message message) {
        OutputMessage om = new OutputMessage();
        
        om.setPrivateUUIDOfSender(message.getPrivateUUIDOfSender());

        String[] info0 = message.getMessage().split("|");

        StringBuilder sb = new StringBuilder();

        switch (info0[0]) {
            case "CHAT":
                om.setChatMessage(true);
                sb.append("CHAT|");
                break;
            case "SYSTEM":
                om.setChatMessage(false);
                sb.append("SYSTEM|");
                break;
        }

        info0 = info0[1].split(":");
        String[] info1 = info0[1].split(",");
        
        switch (info0[1]) {
            case "PUBLIC":
                om.setPublicMessage(true);
                sb.append("PUBLIC:");
                
                break;
            case "PRIVATE":
                om.setPublicMessage(false);
                sb.append("PRIVATE:");
                break;
        }

        return om;
    }

    public static SystemMessage systemMessage(Message message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static Message buildMessage(String privateUUID, String receiveMessage) {
        return new InputMessage(privateUUID, receiveMessage);
    }

}
