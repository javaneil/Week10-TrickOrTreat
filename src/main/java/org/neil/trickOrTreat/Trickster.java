package org.neil.trickOrTreat;

import org.apache.log4j.Logger;


/**
 * Trick-Or-Treater (Trickster) Thread
 * Created by Neil on 3/27/2017.
 */
public class Trickster implements Runnable {
    private String name ;
    private Long time ;
    private House home ;
    private final Logger log = Logger.getLogger( this.getClass() ) ;

    public Trickster( House home ) {
        this.home = home ;
    }

    public void run() {
        goRingDoorbell() ;
    }

    private synchronized void goRingDoorbell() {
        home.add( this ) ;
    }


    /** **********************************************************************
     * Setters and Getters
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

}
