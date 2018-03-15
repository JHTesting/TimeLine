package Main;

import UserInterface.UserInterface;
import logic.EventHandler;
import logic.Lecture;
import logic.WeekDays;

import javax.swing.*;

public class Main {
    public static void main(String[] args) throws Exception {
        EventHandler handler = new EventHandler();
        handler.add(new Lecture("Programming", 10, 12, "MON", "Kaiser", "W12"));
        UserInterface ui = new UserInterface(handler);
        SwingUtilities.invokeLater(ui);
    }
}
