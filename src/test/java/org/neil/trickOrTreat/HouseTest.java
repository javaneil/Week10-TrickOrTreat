package org.neil.trickOrTreat;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Neil on 3/28/2017.
 */
public class HouseTest {
    @Test
    public void answerDoor() throws Exception {

    }

    @Test
    public void add() throws Exception {
        House home = new House() ;

        Trickster trickster1 = new Trickster( home ) ;
        trickster1.setName( "Trickster-01" ) ;
        trickster1.setTime( (long)1234 ) ;
        home.add( trickster1 ) ;

        Trickster trickster2 = new Trickster( home ) ;
        trickster2.setName( "Trickster-02" ) ;
        trickster2.setTime( (long)2345 ) ;
        home.add( trickster2 ) ;

        home.answerDoor() ;
    }

}