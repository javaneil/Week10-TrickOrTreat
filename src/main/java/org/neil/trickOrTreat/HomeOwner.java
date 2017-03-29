package org.neil.trickOrTreat;

import org.apache.log4j.Logger;

/**
 * Home Owner Thread
 * Created by Neil on 3/28/2017.
 */
public class HomeOwner implements Runnable {
    private volatile Boolean running ;
    private House home ;
    private String name ;
    private final Logger log = Logger.getLogger( this.getClass() ) ;


    /**
     * Constructor
     * @param name - homeowner's name
     * @param home - the house object where the homeowner lives
     */
    public HomeOwner( String name, House home ) {
        this.name = name ;
        this.home = home ;
        this.home.setHomeowner( this.name ) ;
        this.running = true ;
    }


    /**
     * The homeowner thread handles trickster objects created
     */
    public void run() {
        log.info( "HomeOwner Started..." ) ;
        while( running ) {
            home.answerDoor() ;
        }
        log.info( "HomeOwner Terminated..." ) ;
    }


    /**
     * Breaks out of while() loop and terminates this thread
     */
    public void terminateThread() {
        this.running = false ;
        // the thread may be blocked in the answerDoor() method
        home.unblock() ;
    }
}
