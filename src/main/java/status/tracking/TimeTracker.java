package status.tracking;

public class TimeTracker {
    private long startTime;
    private long endTime;

    public TimeTracker(){
        startTime=0;
        endTime=0;
    }

    public void start(){
        startTime=System.currentTimeMillis();
        endTime=startTime;
    }

    public void end(){
        endTime=System.currentTimeMillis();
    }

    public long getTimeMillis(){
        return endTime-startTime;
    }



}
