package status.babystep;

import gui.StatusDisplay;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;

public class Babystepper implements BabystepControls{
    private StatusDisplay statusDisplay;
    private AnimationTimer animationTimer;
    private boolean statusSwitchActivated;
    private long timeRemaining;
    private long countdownLength;
    private long lastUpdateTime;
    private boolean running;
    private EventHandler<BabystepperEvent> eventHandler;
    public Babystepper(StatusDisplay statusDisplay, EventHandler<BabystepperEvent> eventHandler, long countdownLength,boolean statusSwitchActivated){
        this.statusDisplay = statusDisplay;
        this.eventHandler = eventHandler;
        this.countdownLength = countdownLength*1000;
        this.statusSwitchActivated=statusSwitchActivated;
        running = false;
        this.animationTimer=new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };
    }

    public boolean running(){
        return running;
    }

    public void start(){
        lastUpdateTime=System.currentTimeMillis();
        running = true;
        animationTimer.start();
    }

    public void restart(){
        stop();
        timeRemaining = countdownLength;
        statusDisplay.displayRemainingTime(Math.floor((((double)(timeRemaining))/100))/10);
        start();
    }

    public void stop(){
        animationTimer.stop();
        running=false;
    }

    private void update(){
        long now = System.currentTimeMillis();
        timeRemaining-=now-lastUpdateTime;
        lastUpdateTime=now;
        if(timeRemaining<=0){
            stop();
            statusDisplay.displayRemainingTime(0);
            if(statusSwitchActivated)
                eventHandler.handle(new BabystepperEvent());
        }else{
            statusDisplay.displayRemainingTime(Math.floor((((double)(timeRemaining))/100))/10);
        }
    }

}
