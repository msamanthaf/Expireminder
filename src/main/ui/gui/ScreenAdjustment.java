package ui.gui;

import model.Event;
import model.EventLog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

// Standard JFrame size and background for the application
public interface ScreenAdjustment {
    // EFFECTS: Makes a standard JFrame for a regular window
    default void screenAdjustment(JPanel screen, JFrame load) {
        load.setSize(360, 640);
        load.setResizable(false);
        load.setTitle("EXPIREMINDER");
        load.setBackground(Color.DARK_GRAY);
        load.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        load.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                for (Event event : EventLog.getInstance()) {
                    String log = event.getDescription();
                    System.out.println(log);
                }
                EventLog.getInstance().clear();
                System.exit(0);
            }
        });
        ImageIcon logo = new ImageIcon("data/Expireminder.png");
        load.setIconImage(logo.getImage());
        load.add(screen);
        load.setVisible(true);
    }

    // EFFECTS: Makes the given panel scrollable
    default void scrollBar(JScrollPane panel, JFrame load) {
        load.setSize(360, 640);
        load.setResizable(false);
        load.setTitle("EXPIREMINDER");
        load.setBackground(Color.DARK_GRAY);
        load.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        ImageIcon logo = new ImageIcon("data/Expireminder.png");
        load.setIconImage(logo.getImage());
        load.add(panel);
        load.setVisible(true);
    }

    // EFFECTS: Makes a standard JFrame for a pop-up window
    default void popUp(JPanel panel, JFrame load, int width, int height) {
        load.setSize(width, height);
        load.setResizable(false);
        load.setTitle("EXPIREMINDER");
        load.setBackground(Color.DARK_GRAY);
        ImageIcon logo = new ImageIcon("data/Expireminder.png");
        load.setIconImage(logo.getImage());
        load.add(panel);
        load.setVisible(true);
    }

    // EFFECTS: Standardizes input text with given font and color
    default void textAdjustments(JLabel text) {
        text.setForeground(Color.white);
        text.setHorizontalTextPosition(JLabel.CENTER);
        text.setVerticalTextPosition(JLabel.BOTTOM);
    }
}