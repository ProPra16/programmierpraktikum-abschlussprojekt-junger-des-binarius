package status.babystep;

import gui.StatusDisplay;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;

/**
 * Repraesentiert die Babystep-Erweiterung. Speichert eine AnimationTimer-Instanz, ein StatusDisplay-Interface, die verbleibende Zeit, die Countdown-Laenge, einen BabystepperEvent-EventHandler, und ob der Timer gerade laeuft.
 */
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

    /**
     * @return ob der Babystep-Timer gerade laeuft.
     */
    public boolean running(){
        return running;
    }

    /**
     * Laesst den Babystep-Timer weiterlaufen.
     */
    public void continueTimer(){
        lastUpdateTime=System.currentTimeMillis();
        running = true;
        animationTimer.start();
    }

    /**
     * Resettet den Babystep-Timer.
     */
    public void resetTimer(){
        pauseTimer();
        timeRemaining = countdownLength;
        statusDisplay.displayRemainingTime(Math.floor((((double)(timeRemaining))/100))/10);
        continueTimer();
    }

    /**
     * Pausiert den Babystep-Timer.
     */
    public void pauseTimer(){
        animationTimer.stop();
        running=false;
    }

    /**
     * Aktualisiert das Babystep-Time-Label und startet bei Ablauf der Babystep-Zeit das entsprechende Event.
     */
    private void update(){
        long now = System.currentTimeMillis();
        timeRemaining-=now-lastUpdateTime;
        lastUpdateTime=now;
        if(timeRemaining<=0){
            pauseTimer();
            statusDisplay.displayRemainingTime(0);
            if(statusSwitchActivated)
                eventHandler.handle(new BabystepperEvent());
        }else{
            statusDisplay.displayRemainingTime(Math.floor((((double)(timeRemaining))/100))/10);
        }
    }
}
