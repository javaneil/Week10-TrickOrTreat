package org.neil.trickOrTreat;

/**
 * Created by Neil on 3/28/2017.
 */
public class TrickOrTreatMain {
    // define the maximum delay between trick-or-treat events
    final static Integer MAX_DELAY = 10 ; // seconds

    public static void main( String a[] ) {
        // instantiate the house that Riley lives in
        House house = new House() ;

        // instantiate & start the homeowner thread,
        // then kill some time to allow homeowner to settle-in to watching television...
        HomeOwner owner = new HomeOwner( "Riley", house ) ;
        Thread thOwner = new Thread( owner ) ;
        thOwner.start() ;
        try {
            Thread.sleep(5000 ) ;
        } catch ( InterruptedException ie ) {}

        // instantiate & start the trick-or-treat generator thread
        TricksterGenerator tgen = new TricksterGenerator( house, MAX_DELAY ) ;
        Thread thTGen  = new Thread( tgen ) ;
        thTGen.start() ;

        // Simulation run-time...
        try {
            Thread.sleep(30000 ) ;
        } catch ( InterruptedException ie ) {}

        // shut-down running threads - cleanly?
        tgen.terminateThread() ;

        // allow time for trickster generator to wind-down
        try {
            Thread.sleep(MAX_DELAY*1000 ) ;
        } catch ( InterruptedException ie ) {}

        owner.terminateThread() ;
    }
}
