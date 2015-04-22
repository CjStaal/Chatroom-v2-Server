/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.staalcomputingsolutions.chatroom.server.exceptions;

/**
 *
 * @author Charles Joseph Staal
 */
public class ChatServerException extends Exception {
    
    /**
     * Default constructor.
     */
    public ChatServerException() {
        super();
    }

    /**
     * Constructs a <code>FtpException</code> object with a message.
     * 
     * @param msg
     *            a description of the exception
     */
    public ChatServerException(String msg) {
        super(msg);
    }

    /**
     * Constructs a <code>ChatServerException</code> object with a
     * <code>Throwable</code> cause.
     * 
     * @param th
     *            the original cause
     */
    public ChatServerException(Throwable th) {
        super(th.getMessage());
    }

    /**
     * Constructs a <code>BaseException</code> object with a
     * <code>Throwable</code> cause.
     * @param msg A description of the exception
     * 
     * @param th
     *            The original cause
     */
    public ChatServerException(String msg, Throwable th) {
        super(msg);
    }

}
