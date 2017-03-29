package org.neil.trickOrTreat;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Neil on 3/28/2017.
 */
public class TricksterGeneratorTest {

    @Test
    public void run() throws Exception {
        House home = new House() ;
        TricksterGenerator tgen = new TricksterGenerator( home, 10000 ) ;
        Thread thread = new Thread( tgen ) ;
        thread.start() ;

        Thread.sleep( 30000 ) ;
        tgen.terminateThread() ;
    }

}