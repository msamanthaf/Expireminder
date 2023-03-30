package ui.gui;

import javax.swing.*;
import java.awt.*;

// Standard JFrame size and background for the application
public interface ScreenAdjustment {
    // EFFECTS: Makes a standard JFrame for a regular window
    default void screenAdjustment(JPanel screen, JFrame load) {
        load.setSize(360, 640);
        load.setResizable(false);
        load.setTitle("EXPIREMINDER");
        load.setBackground(Color.DARK_GRAY);
        load.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
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