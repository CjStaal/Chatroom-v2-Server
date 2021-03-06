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
package com.staalcomputingsolutions.chatroom.server;

import com.staalcomputingsolutions.chatroom.server.exceptions.ChatServerException;

/**
 *
 * @author Charles Joseph Staal
 */
public interface ChatServer {
    
    /**
     * Start the server. Open a new listener thread.
     * @throws ChatServerException 
     */
    void start() throws ChatServerException;
    
    /**
     * Stop the server. Stop the listener thread.
     */
    void stop();
    
    /**
     * Get the server status.
     * @return true if the server is stopped
     */
    boolean isStopped();
    
    /**
     * Suspend further requests
     */
    void suspend();
    
    /**
     * Resume the server handler
     */
    void resume();
    
    /**
     * Is the server suspended
     * @return true if the server is suspended
     */
    boolean isSuspended();
    
}
