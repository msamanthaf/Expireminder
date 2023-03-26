package ui.gui;

import model.Account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Homepage extends JFrame implements ActionListener, ScreenAdjustment {
    private JFrame home;
    private JPanel panel;
    private JButton editProfileButton;
    private JLabel itemStatus;
    private JLabel greetings;
    private JList list1;
    private JList list2;
    private Account account;

    public Homepage(Account currentAccount, String inputButton) {
        this.account = currentAccount;
        home = new JFrame();
        panel = new JPanel();
        Container pane = this.getContentPane();
        pane.setLayout(new GridLayout(7, 1));
        pane.setBackground(Color.DARK_GRAY);
        printComponents(panel, pane);
    }

    private void printComponents(JPanel panel, Container pane) {
        greetings = new JLabel("Hello " + account.getName() + "!");
        greetings.setFont(new Font("Adobe Clean ExtraBold", Font.BOLD, 20));
        textAdjustments(greetings);
        pane.add(greetings);
        panel.add(pane);
        panel.setBackground(Color.DARK_GRAY);
        screenAdjustment(panel, home);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
