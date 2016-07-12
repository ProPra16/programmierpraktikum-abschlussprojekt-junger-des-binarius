package status.tracking;

/**
 *  Hilfsklasse zur Bestimmung der vergangenen Zeit.
 */
public class TimeTracker {
    private long startTime;
    private long endTime;

    public TimeTracker(){
        startTime=0;
        endTime=0;
    }

    /**
     * Setzt die Startzeit.
     */
    public void start(){
        startTime=System.currentTimeMillis();
        endTime=startTime;
    }

    /**
     * Setzt die Endzeit.
     */
    public void end(){
        endTime=System.currentTimeMillis();
    }

    /**
     * @return die vergangene Zeit in Millisekunden.
     */
    public long getTimeMillis(){
        return endTime-startTime;
    }
}
