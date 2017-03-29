package org.neil.trickOrTreat;

import org.apache.log4j.Logger;
import java.util.Random;

/**
 * Trickster Generator thread
 * Created by Neil on 3/28/2017.
 */
public class TricksterGenerator implements Runnable {
    private volatile Boolean running ;
    private House home ;
    private Integer maxDelay ;
    private Random rand  ;
    private final Logger log = Logger.getLogger( this.getClass() ) ;

    /**
     * Constructor
     * @param home
     * @param delay - sets maximum time period between objects generation
     */
    public TricksterGenerator( House home, Integer delay ) {
        this.home = home ;
        this.maxDelay = delay ;
        this.rand = new Random() ;
        this.running = true ;
    }

    /**
     * The Trickster generation thread
     * Creates new trickster object every 1 to MAX_DELAY seconds
     */
    public void run() {
        Integer id = 1 ;
        Long start = System.currentTimeMillis() ;    // this run's epoch
        Integer delay = 0 ;

        log.info ( "Trickster generation started..." ) ;
        while ( running ) {
            Trickster trickster = new Trickster( home ) ;
            trickster.setName( "Trickster-" + id++ ) ;
            trickster.setTime( System.currentTimeMillis() - start ) ;  // calculate elapse time (ms)
            Thread thread = new Thread( trickster ) ;
            log.info( thread.getName() ) ;
            thread.start() ;

            // calculate delay until next trickster generation
            delay = rand.nextInt( maxDelay - 1 ) + 1 ;

            try {
                // sleep() is a millisecond timer, multiply seconds x 1000
                Thread.sleep( delay * 1000 ) ;
            } catch ( InterruptedException ie ) { log.error( ie ); }
        }
        log.info( "Trickster generation terminated..." ) ;
    }


    /**
     * Breaks out of while() loop and terminates thread
     */
    public void terminateThread()
    {
        this.running = false ;
    }

}
