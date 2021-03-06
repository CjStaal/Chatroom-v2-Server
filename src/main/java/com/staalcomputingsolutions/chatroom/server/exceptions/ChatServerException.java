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
