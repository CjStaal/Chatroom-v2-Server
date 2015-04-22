/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
