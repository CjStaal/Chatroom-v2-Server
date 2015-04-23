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
import com.staalcomputingsolutions.chatroom.server.messaging.messages.OutputMessage;

/**
 * Handles the output of outgoing messages to Communicator.
 * 
 * @author Charles Joseph Staal
 */
public class OutputHandler implements Handler {

    private final Communicator communicator;

    public OutputHandler(Communicator communicator) {
        this.communicator = communicator;
    }

    @Override
    public void handleMessage(Object message) {
        OutputMessage om = (OutputMessage) message;
        
    }

}
