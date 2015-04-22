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

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class handles the management of users in the entire chat-room, not just
 * in a sub-room of the chat-room. This is NOT a singleton class, since the
 * server will be able to run multiple chat-rooms concurrently.
 *
 * @author Charles
 */
public class UserManager {

    private final ArrayList<UserConnection> userList;

    private static final Logger logger = LoggerFactory.getLogger(UserManager.class);

    /**
     * This is the default constructor for the UserManager class. All it does is
     * initiate the ConcurrentHashMaps.
     */
    public UserManager() {
        logger.debug("Constructing UserManager class. class name= " + this.getClass().getName());

        this.userList = new ArrayList();

        logger.debug("UserManager class created. class name= " + this.getClass().getName());
    }

    /**
     * @param userConnection The clients userConnection object.
     */
    public synchronized void addClient(UserConnection userConnection) {

        logger.debug("Adding client.");

        this.userList.add(userConnection);

        logger.debug("Client added.");
    }

    /**
     * This method is used to remove the client from the relevant
     * ConcurrentHashMaps by using the private UUID of the client.
     *
     * @param privateUUID The private UUID of the client to be removed.
     */
    public synchronized void removeClient(String privateUUID) {
        removeClient(getUserConnectionFromPrivateUUID(privateUUID));
    }
    
    public synchronized void removeClient(UserConnection uc){
        if(!uc.getSocket().isClosed()){
            uc.close();
        }
        this.userList.remove(uc);
    }

    /**
     * This method is used to fetch the ConcurrentHashMap that has the public
     * UUIDs mapped to the Usernames.
     *
     * @return {@link ConcurrentHashMap} The relevant ConcurrentHashMap.
     */
    public synchronized ArrayList<UserConnection> getUserList() {
        return this.userList;
    }

    /**
     *
     * @param publicUUID
     * @return
     */
    public String getPrivateUUIDFromPublicUUID(String publicUUID) {
        for (UserConnection uc : userList) {
            if (uc.getPublicUUID().equals(publicUUID)) {
                return uc.getPrivateUUID();
            }
        }
        return null;
    }

    /**
     *
     * @param privateUUID
     * @return
     */
    public String getPublicUUIDFromPrivateUUID(String privateUUID) {
        for (UserConnection uc : userList) {
            if (uc.getPrivateUUID().equals(privateUUID)) {
                return uc.getPublicUUID();
            }
        }
        return null;
    }

    public UserConnection getUserConnectionFromPrivateUUID(String privateUUID) {
        for (UserConnection uc : userList) {
            if (uc.getPrivateUUID().equals(privateUUID)) {
                return uc;
            }
        }
        return null;

    }

}
