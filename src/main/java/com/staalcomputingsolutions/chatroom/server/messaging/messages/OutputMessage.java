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
package com.staalcomputingsolutions.chatroom.server.messaging.messages;

/**
 * The object for messages to be output.
 * 
 * @author Charles Joseph Staal
 */
public class OutputMessage extends DefaultMessage {

    private boolean publicMessage = false, chatMessage = false;

    private String privateUUIDOfRecipiant;

    public OutputMessage() {
        super();
    }

    public void setChatMessage(boolean chatMessage) {
        this.chatMessage = chatMessage;
    }

    public void setPublicMessage(boolean publicMessage) {
        this.publicMessage = publicMessage;
    }

    public void setPrivateUUIDOfRecipiant(String privateUUIDOfRecipiant) {
        this.privateUUIDOfRecipiant = privateUUIDOfRecipiant;
    }

    public String getPrivateUUIDOfRecipiant() {
        return privateUUIDOfRecipiant;
    }

    public boolean isChatMessage() {
        return chatMessage;
    }

    public boolean isPublicMessage() {
        return publicMessage;
    }
}
