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

    /**
     * This ConcurrentHashMap maps the private UUID to the Username of the
     * client.
     * key = private UUID, value=Username.
     */
    private final ConcurrentHashMap<String, String> publicUUIDToUsername;
    /**
     * This ConcurrentHashMap maps the private UUID to the public UUID of the
     * client.
     * key = private UUID, value=public UUID.
     */
    private final ConcurrentHashMap<String, String> privateUUIDToPublicUUID;
    /**
     * This ConcurrentHashMap maps the public UUID to the private UUID of the
     * client.
     * key = public UUID, value=private UUID.
     */
    private final ConcurrentHashMap<String, String> publicUUIDToPrivateUUID;
    /**
     * This ConcurrentHashMap maps the private UUID to the UserConnection object
     * that the specific client is currently using.
     * key = private UUID, value=Clients UserConnection.
     */
    private final ConcurrentHashMap<String, UserConnection> privateUUIDToUserConnection;

    private static final Logger logger = LoggerFactory.getLogger(UserManager.class);

    /**
     * This is the default constructor for the UserManager class. All it does is
     * initiate the ConcurrentHashMaps.
     */
    public UserManager() {
        logger.debug("Constructing UserManager class. class name= " + this.getClass().getName());

        this.publicUUIDToUsername = new ConcurrentHashMap();
        this.privateUUIDToPublicUUID = new ConcurrentHashMap();
        this.publicUUIDToPrivateUUID = new ConcurrentHashMap();
        this.privateUUIDToUserConnection = new ConcurrentHashMap();

        logger.debug("UserManager class created. class name= " + this.getClass().getName());
    }

    /**
     * This method is used to add the clients information to the relevant
     * ConcurrentHashMaps.
     *
     * @param privateUUID The clients private UUID.
     * @param publicUUID The clients public UUID.
     * @param username The clients username.
     * @param userConnection The clients userConnection object.
     */
    public synchronized void addClient(String privateUUID, String publicUUID,
            String username, UserConnection userConnection) {

        logger.debug("Adding client."
                + "\nUsername: " + username
                + "\nPrivate UUID: " + privateUUID
                + "\nPublic UUID: " + publicUUID + ".");

        this.publicUUIDToUsername.put(publicUUID, username);
        this.privateUUIDToPublicUUID.put(privateUUID, publicUUID);
        this.publicUUIDToPrivateUUID.put(publicUUID, privateUUID);
        this.privateUUIDToUserConnection.put(privateUUID, userConnection);

        logger.debug("Client added."
                + "\nUsername: " + username
                + "\nPrivate UUID: " + privateUUID
                + "\nPublic UUID: " + publicUUID + ".");
    }

    /**
     * This method is used to remove the client from the relevant
     * ConcurrentHashMaps by using the private UUID of the client.
     *
     * @param privateUUID The private UUID of the client to be removed.
     */
    public synchronized void removeClient(String privateUUID) {

        logger.debug("Removing client.\nPrivate UUID: " + privateUUID + ".");

        if (!this.privateUUIDToUserConnection.get(privateUUID).getSocket().isClosed()) {
            logger.debug("UserManager closing socket of client with private UUID of : " + privateUUID + ".");

            this.privateUUIDToUserConnection.get(privateUUID).close();

            logger.debug("UserManager closed socket of client with private UUID of : " + privateUUID + ".");
        } else {
            logger.debug("UserManager attempted to socket of client with private UUID of : " + privateUUID
                    + " but it is already closed.");
        }

        this.publicUUIDToPrivateUUID.remove(this.privateUUIDToPublicUUID.get(privateUUID));
        this.publicUUIDToUsername.remove(this.privateUUIDToPublicUUID.get(privateUUID));

        this.privateUUIDToPublicUUID.remove(privateUUID);
        this.privateUUIDToUserConnection.remove(privateUUID);

        logger.debug("Client removed.\nPrivate UUID: " + privateUUID + ".");
    }

    /**
     * This method is used to fetch the ConcurrentHashMap that has the public
     * UUIDs mapped to the Usernames.
     *
     * @return {@link ConcurrentHashMap} The relevant ConcurrentHashMap.
     */
    public synchronized ConcurrentHashMap<String, String> getUserMap() {
        logger.debug("ConcurrentHashMap publicUUIDToUsername being fetched.");
        return this.publicUUIDToUsername;
    }

    /**
     * This method is used to fetch the ConcurrentHashMap that has the private
     * UUIDs mapped to the Users connection object.
     *
     * @return {@link ConcurrentHashMap} The relevant ConcurrentHashMap.
     */
    public synchronized ConcurrentHashMap<String, UserConnection> getConnectionMap() {
        logger.debug("ConcurrentHashMap privateUUIDToUserConnection being fetched.");
        return this.privateUUIDToUserConnection;
    }

    /**
     *
     * @param publicUUID
     * @return
     */
    public String getPrivateUUIDFromPublicUUID(String publicUUID) {
        return this.publicUUIDToPrivateUUID.get(publicUUID);
    }

    /**
     *
     * @param privateUUID
     * @return
     */
    public String getPublicUUIDFromPrivateUUID(String privateUUID) {
        return this.privateUUIDToPublicUUID.get(privateUUID);
    }

}
