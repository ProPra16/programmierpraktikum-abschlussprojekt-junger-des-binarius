package status.babystep;

/**
 * Interface zur Kontrolle eines Babystep-Timers.
 */
public interface BabystepControls {
    void continueTimer();
    void pauseTimer();
    void resetTimer();
    boolean running();
}
