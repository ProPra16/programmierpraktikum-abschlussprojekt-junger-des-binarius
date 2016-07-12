package gui;

import javafx.scene.paint.Color;

import java.util.List;

/**
 * Interface, welches Methoden verlangt um in einem Fenster einen Status anzeigen lassen zu koennen.
 * Hier: Kontrolle der Elemente des Hauptfensters.
 */
public interface StatusDisplay {
    String getCode();

    void displayCode(String code);

    void displayClassList(List<String> content);

    void displayStatus(String statusText, Color color);

    void displaySwitchStatusOptions(boolean red, boolean green, boolean refactor);

    void displayFeedback(String text);

    void displayRemainingTime(double timeRemaining);
}
