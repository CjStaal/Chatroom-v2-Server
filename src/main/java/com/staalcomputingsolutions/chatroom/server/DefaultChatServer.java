/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.staalcomputingsolutions.chatroom.server;

/**
 *
 * @author Charles Joseph Staal
 */
public class DefaultChatServer {
    
    private boolean started = false, suspended = false;
    
    private ChatServerContext serverContext;
    public DefaultChatServer(ChatServerContext serverContext){
        this.serverContext = serverContext;
    }
}
