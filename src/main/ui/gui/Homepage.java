package ui.gui;

import model.Account;
import model.Categories;
import model.Items;
import persistence.JsonReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Homepage extends JFrame implements ActionListener, ScreenAdjustment {
    private JFrame home;
    private JPanel panel;
    private JButton editProfileButton;
    private JButton addCategoryButton;
    private JLabel itemStatus;
    private JLabel greetings;
    private Container pane;
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
        pane = this.getContentPane();
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
        itemStatus.setText("<html> You have: <br>" + currentCategories.getGoodItems() + " items in good condition"
                + "<html><br>" + currentCategories.getExpiringItems() + " items expiring soon" + "<html><br>"
                + currentCategories.getExpiredItems() + " items expired");
        textAdjustments(itemStatus);
        pane.add(itemStatus);

        addCategoryButton = new JButton("Add new category");
        pane.add(addCategoryButton);

        showAllCategories();

        panel.add(pane);
        panel.setSize(360, 640);
        screenAdjustment(panel, home);
    }

    private void showAllCategories() {
        for (int i = 0; i < currentCategories.getCategoryName().size(); i++) {
            Container allCategories = new JPanel(new GridLayout(2, 1));
            if (i % 2 == 0) {
                allCategories.setBackground(Color.GRAY);
            } else {
                allCategories.setBackground(Color.LIGHT_GRAY);
            }
            JPanel header = new JPanel(new GridLayout(1, 3));
            JLabel categoryLabel = new JLabel();
            categoryLabel.setText(currentCategories.getCategoryIndex().get(i) + ")" + " "
                    + currentCategories.getCategoryName().get(i));
            JButton categoryButton = new JButton("Edit Category");
            JButton addItemButton = new JButton("Add new item");
            header.add(categoryLabel);
            header.add(categoryButton);
            header.add(addItemButton);
            allCategories.add(header);
            ArrayList<Items> listOfItems = currentCategories.getCategoryItems().get(i);
            Container displayItems = new JPanel();
            displayItems.setLayout(new GridLayout(listOfItems.size(), 1));
            for (Items item : listOfItems) {
                int indexPosition = listOfItems.indexOf(item);
                JLabel itemNumber = new JLabel(String.valueOf(indexPosition + 1) + ".");
                JLabel itemName = new JLabel(item.getName());
                JLabel itemDescription = new JLabel("x" + item.getQuantity() + " ~ "
                        + item.getDate());
                displayItems.add(itemNumber);
                displayItems.add(itemName);
                displayItems.add(itemDescription);
            }
            allCategories.add(displayItems);
            pane.add(allCategories);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addCategoryButton) {
            //
        }
    }
}
