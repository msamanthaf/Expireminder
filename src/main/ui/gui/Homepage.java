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
    private JButton editCategoryButton;
    private JLabel itemStatus;
    private JLabel greetings;
    private Container pane;
    private Account currentAccount;
    private Categories currentCategories;

    public Homepage(Account currentAccount, Categories currentCategories, String inputButton) {
        this.currentAccount = currentAccount;
        this.currentCategories = currentCategories;
        if (inputButton.equals("y")) {
            JsonReader reader = new JsonReader("./data/categoriesData.json");
            this.currentCategories = reader.readCategories();
        } else if (inputButton.equals("n")) {
            this.currentCategories = new Categories();
        }

        home = new JFrame();
        panel = new JPanel(new GridBagLayout());
        panel.setLayout(new GridLayout(0, 1));
        pane = this.getContentPane();
        pane.setLayout(new GridLayout(0, 1));
        pane.setBackground(Color.DARK_GRAY);
        printComponents(panel, pane);
    }

    private void printComponents(JPanel panel, Container pane) {
        greetings = new JLabel("Hello " + currentAccount.getName() + "!");
        greetings.setFont(new Font("Adobe Clean ExtraBold", Font.BOLD, 20));
        textAdjustments(greetings);
        pane.add(greetings);

        editProfileButton = new JButton("Edit Profile");
        editProfileButton.addActionListener(this);
        pane.add(editProfileButton);

        itemStatus = new JLabel();
        itemStatus.setText("<html> You have: <br>" + currentCategories.getGoodItems() + " items in good condition"
                + "<html><br>" + currentCategories.getExpiringItems() + " items expiring soon" + "<html><br>"
                + currentCategories.getExpiredItems() + " items expired");
        textAdjustments(itemStatus);
        pane.add(itemStatus);

        addCategoryButton = new JButton("Add new category");
        addCategoryButton.addActionListener(this);
        pane.add(addCategoryButton);

        showAllCategories();

        Container saveData = new JPanel(new GridLayout(1, 2));
        panel.add(pane);
        panel.setSize(360, 640);
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollBar(scrollPane, home);
    }

    private void showAllCategories() {
        for (int i = 0; i < currentCategories.getCategoryName().size(); i++) {
            Container allCategories = new JPanel(new GridLayout(2, 1));
            header(i, allCategories);
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

    private void header(int i, Container allCategories) {
        JPanel header = new JPanel(new GridLayout(1, 3));
        JLabel categoryLabel = new JLabel();
        categoryLabel.setText(currentCategories.getCategoryIndex().get(i) + ")" + " "
                + currentCategories.getCategoryName().get(i));
        editCategoryButton = new JButton("Edit Category");
        editCategoryButton.addActionListener(e -> {
            new EditCategory(i + 1, currentCategories, currentAccount, home);
        });
        JButton addItemButton = new JButton("Add new item");
        header.add(categoryLabel);
        header.add(editCategoryButton);
        header.add(addItemButton);
        allCategories.add(header);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == editProfileButton) {
            home.dispose();
            new Setup("else", currentAccount, currentCategories);
        }
        if (e.getSource() == addCategoryButton) {
            home.dispose();
            new AddCategory(currentCategories, currentAccount);
        }
    }
}