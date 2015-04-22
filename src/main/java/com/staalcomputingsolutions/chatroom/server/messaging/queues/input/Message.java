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
package com.staalcomputingsolutions.chatroom.server.messaging.queues.input;

/**
 *
 * @author Charles Joseph Staal
 */
public class Message {
 
    private final String uuid;
    private String message;
    
    public Message(String uuid) {
        this.uuid = uuid;
    }
    
    public Message setMessage(String message){
        this.message = message;
        return this;
    }
    
    public String getUUID(){
        return this.uuid;
    }
    
    public String getMessage(){
        return this.message;
    }
    
}
