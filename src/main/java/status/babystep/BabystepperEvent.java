package status.babystep;

import javafx.event.Event;
import javafx.event.EventType;

public class BabystepperEvent extends Event {

    public static final EventType<BabystepperEvent> timeExpired = new EventType<>(ANY,"TIME_EXPIRED");

    public BabystepperEvent() {
        super(timeExpired);
    }
}
