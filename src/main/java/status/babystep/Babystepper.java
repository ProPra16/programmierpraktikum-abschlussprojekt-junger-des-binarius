package status.babystep;

import gui.StatusDisplay;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;

public class Babystepper implements BabystepControls{
    private StatusDisplay statusDisplay;
    private AnimationTimer animationTimer;
    private long timeRemaining;
    private long countdownLength;
    private long lastUpdateTime;
    private EventHandler<BabystepperEvent> eventHandler;
    public Babystepper(StatusDisplay statusDisplay, EventHandler<BabystepperEvent> eventHandler, long countdownLength){
        this.statusDisplay = statusDisplay;
        this.eventHandler = eventHandler;
        this.countdownLength = countdownLength*1000;
        this.animationTimer=new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };
    }

    public void start(){
        lastUpdateTime=System.currentTimeMillis();
        animationTimer.start();
    }

    public void restart(){
        stop();
        timeRemaining = countdownLength;
        start();
    }

    public void stop(){
        animationTimer.stop();
    }

    private void update(){
        long now = System.currentTimeMillis();
        timeRemaining-=now-lastUpdateTime;
        lastUpdateTime=now;
        if(timeRemaining<=0){
            stop();
            statusDisplay.displayRemainingTime(0);
            eventHandler.handle(new BabystepperEvent());
        }else{
            statusDisplay.displayRemainingTime(Math.floor((((double)(timeRemaining))/100))/10);
        }
    }

}