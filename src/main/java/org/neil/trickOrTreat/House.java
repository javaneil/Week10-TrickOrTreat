package org.neil.trickOrTreat;

import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

import static java.lang.Thread.sleep;

/**
 * Created by Neil on 3/27/2017.
 */
public class House {
    private String homeowner = "Unknown" ;
    private List<Trickster> listTricksters ;
    private final Logger log = Logger.getLogger( this.getClass() ) ;


    // Constructor - assign who lives in this house
    public House() {
        listTricksters = new LinkedList<Trickster>() ;
    }


    /**
     * This method is called from the homeowner thread to handle
     * objects added to the Trickster List
     */
    public void answerDoor() {
        Trickster trickster ;
        log.info( "Waiting for listTricksters lock" ) ;
        synchronized ( listTricksters ) {
//            while ( 0 == listTricksters.size() ) {
            if ( 0 == listTricksters.size() ) {
                log.info( homeowner + " is watching television" ) ;
                try {
                    // wait here until synchronized List receives
                    // a .notice() from another thread
                    listTricksters.wait() ;
                } catch ( InterruptedException ie ) { log.error( ie ); }
            }
            else {
                log.info( homeowner + " is answering the doorbell" ) ;

                // handle all tricksters at door
                while ( 0 < listTricksters.size()  ) {
                    trickster = (Trickster) ((LinkedList<?>) listTricksters).poll();
                    try {
                        log.info( homeowner + " is handing out candy to " + trickster.getName() ) ;
                        sleep(3000);  // allocate 3-seconds to hand-out candy
                    } catch ( InterruptedException ie ) { log.error( ie ); }
                }
                log.info( homeowner + " is closing the door" ) ;
            }
        }
    }


    /**
     * This method is called by the TricksterGenerator thread to
     * add new objects to the Trickster List
     * @param trickster
     */
    public void add( Trickster trickster ) {
        Boolean offer ;
        log.info( "Adding new: " + trickster.getName() + " at time: " + trickster.getTime() ) ;
        synchronized ( listTricksters ) {
            if ( 10 <= listTricksters.size() ) {
                log.info( "Too many at door for " + trickster.getName() + " goes to next house" ) ;
                return ;
            }

            //TODO what is this?  need to look into .offer()...
            offer = ((LinkedList<Trickster>)listTricksters).offer( trickster ) ;
            if ( offer ) {
                log.info(trickster.getName() + " is ringing doorbell ");

                //TODO equal-to-one? or greater-than-zero???
                if ( 0 < listTricksters.size() ) {
                    listTricksters.notify() ;
                }
            }
            else {
                log.error( "Failed to add " + trickster.getName() + " to List" ) ;
            }
        }
    }


    public void unblock() {
        log.info( "Forcing notify() to unblock .wait() " ) ;
        synchronized ( listTricksters ) {
            listTricksters.notify() ;
        }
    }


    /** **********************************************************************
     * Setters and Getters
     * @return
     */
    public String getHomeowner() {
        return homeowner;
    }

    public void setHomeowner(String homeowner) {
        this.homeowner = homeowner;
    }

}
