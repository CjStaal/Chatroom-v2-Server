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
package com.staalcomputingsolutions.chatroom.server.messaging.executor;

import com.staalcomputingsolutions.chatroom.server.messaging.handler.Handler;
import com.staalcomputingsolutions.chatroom.server.messaging.queue.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Charles Joseph Staal
 */
public class Executor implements Runnable{
    
    protected final ExecutorService executor;

    protected Queue queue;
    protected Handler handler;
    protected boolean started = false;

    public Executor(){
        executor = Executors.newSingleThreadExecutor();
        
    }
    
    public void setHandler(Handler handler){
        this.handler = handler;
    }
    
    public void setQueue(Queue queue){
        this.queue = queue;
    }
    
    public void start() {
        if (started) {

        } else {
            executor.execute(this);
        }
    }


    public void stop() {
        started = false;
    }

    @Override
    public void run() {
        started = true;
        while(!queue.isEmpty() && started){
            try {
                handler.handleMessage(queue.take());
            } catch (InterruptedException ex) {
                Logger.getLogger(Executor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        started = false;
    }
    
    

}
