package gui;

import javafx.scene.paint.Color;
import java.util.List;

public interface StatusDisplay {
    String getCode();

    void displayCode(String code);

    void displayClassList(List<String> content);

    void displayStatus(String statusText, Color color);

    void displaySwitchStatusOptions(boolean red, boolean green, boolean refactor);

    void displayFeedback(String text);
}
