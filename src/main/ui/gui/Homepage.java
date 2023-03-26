package ui.gui;

import model.Account;
import model.Categories;
import persistence.JsonReader;

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
    private Categories currentCategories;

    public Homepage(Account currentAccount, String inputButton) {
        this.account = currentAccount;
        if (inputButton.equals("y")) {
            JsonReader reader = new JsonReader("./data/categoriesData.json");
            currentCategories = reader.readCategories();
        } else if (inputButton.equals("n")) {
            currentCategories = new Categories();
        }

        home = new JFrame();
        panel = new JPanel(new GridBagLayout());
        panel.setLayout(null);
        Container pane = this.getContentPane();
        pane.setLayout(new GridLayout(7, 1));
        pane.setSize(360, 640);
        pane.setBackground(Color.DARK_GRAY);
        printComponents(panel, pane);
    }

    private void printComponents(JPanel panel, Container pane) {
        greetings = new JLabel("Hello " + account.getName() + "!");
        greetings.setFont(new Font("Adobe Clean ExtraBold", Font.BOLD, 20));
        textAdjustments(greetings);
        pane.add(greetings);

        editProfileButton = new JButton("Edit Profile");
        pane.add(editProfileButton);

        itemStatus = new JLabel();
        itemStatus.setText("<html> You have: <br>" + currentCategories.getGoodItems()
                + " items in good condition" + "<html><br>" + currentCategories.getExpiringItems()
                + " items expiring soon" + "<html><br>" + currentCategories.getExpiredItems()
                + " items expired");
        textAdjustments(itemStatus);
        pane.add(itemStatus);

        panel.add(pane);
        panel.setSize(360, 640);
        screenAdjustment(panel, home);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
