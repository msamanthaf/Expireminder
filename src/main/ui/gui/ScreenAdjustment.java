package ui.gui;

import javax.swing.*;
import java.awt.*;

public interface ScreenAdjustment {
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

    default void textAdjustments(JLabel text) {
        text.setForeground(Color.white);
        text.setHorizontalTextPosition(JLabel.CENTER);
        text.setVerticalTextPosition(JLabel.BOTTOM);
    }
}
