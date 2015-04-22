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
package com.staalcomputingsolutions.chatroom.server.users;

import com.staalcomputingsolutions.chatroom.server.messaging.messages.Message;
import com.staalcomputingsolutions.chatroom.server.messaging.queue.Queue;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Charles Joseph Staal
 */
public class UserConnection {

    private Socket socket;
    private String privateUUID, publicUUID, username;
    private InputStream inputStream;
    private OutputStream outputStream;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private Queue<Message> inputQueue;

    private static final Logger logger = LoggerFactory.getLogger(UserConnection.class);

    public UserConnection(Socket socket, String privateUUID, String publicUUID, String username, Queue<Message> inputQueue, UserManager um) {
        logger.debug("Creating UserConnection object with private UUID of: " + privateUUID + ".");
        this.socket = socket;
        this.privateUUID = privateUUID;
        this.inputQueue = inputQueue;
        this.publicUUID = publicUUID;
        this.username = username;

        try {
            this.inputStream = this.socket.getInputStream();
            this.dataInputStream = new DataInputStream(this.inputStream);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(UserConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            this.outputStream = this.socket.getOutputStream();
            this.dataOutputStream = new DataOutputStream(this.outputStream);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(UserConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

        logger.debug("UserConnection object created with private UUID of: " + this.privateUUID + ".");
    }

    public boolean sendMessage(String message) {
        logger.debug("Sending message: " + message + " to user with privateUUID: " + privateUUID + ".");
        try {
            this.getDataOutputStream().writeUTF(message);
            logger.debug("Sent message: " + message + " to user with privateUUID: " + privateUUID + ".");
            return true;
        } catch (IOException ex) {
            logger.debug(" message: " + message + " to user with privateUUID: " + privateUUID + "has failed to send.");
            this.close();
            return false;
        }
    }

    public boolean messageAvailable() throws IOException {
        return (this.getDataInputStream().available() > 0);
    }

    public String receiveMessage() throws IOException {
        return this.getDataInputStream().readUTF();
    }

    public Socket getSocket() {
        return this.socket;
    }

    public String getPrivateUUID() {
        return this.privateUUID;
    }

    public InputStream getInputStream() {
        return this.inputStream;
    }

    public OutputStream getOutputStream() {
        return this.outputStream;
    }

    public DataInputStream getDataInputStream() {
        return this.dataInputStream;
    }

    public DataOutputStream getDataOutputStream() {
        return this.dataOutputStream;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void setPrivateUUID(String privateUUID) {
        this.privateUUID = privateUUID;
    }

    public void setPublicUUID(String publicUUID) {
        this.publicUUID = publicUUID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void setDataInputStream(DataInputStream dataInputStream) {
        this.dataInputStream = dataInputStream;
    }

    public void setDataOutputStream(DataOutputStream dataOutputStream) {
        this.dataOutputStream = dataOutputStream;
    }

    public void setInputQueue(Queue<Message> inputQueue) {
        this.inputQueue = inputQueue;
    }

    public String getPublicUUID() {
        return publicUUID;
    }

    public String getUsername() {
        return username;
    }

    public Queue<Message> getInputQueue() {
        return inputQueue;
    }

    public void close() {
        try {
            this.getDataInputStream().close();
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(UserConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.getDataOutputStream().close();
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(UserConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.getInputStream().close();
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(UserConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.getOutputStream().close();
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(UserConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.getSocket().close();
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(UserConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
