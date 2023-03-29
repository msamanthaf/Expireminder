package ui.gui;

import model.Account;
import model.Categories;
import model.Items;
import model.Notification;
import persistence.JsonReader;
import persistence.JsonWriter;

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
    private JButton addItemButton;
    private JButton saveButton;
    private JLabel itemStatus;
    private JLabel greetings;
    private JPanel saveData;
    private Container pane;
    private Account currentAccount;
    private Categories currentCategories;
    private ArrayList<Items> goodCondition = new ArrayList<>();
    private ArrayList<Items> expiringSoon = new ArrayList<>();
    private ArrayList<Items> expired = new ArrayList<>();

    public Homepage(Account currentAccount, Categories currentCategories, String inputButton) {
        this.currentAccount = currentAccount;
        if (inputButton.equals("y")) {
            JsonReader reader = new JsonReader("./data/categoriesData.json");
            this.currentCategories = reader.readCategories();
        } else if (inputButton.equals("n")) {
            this.currentCategories = new Categories();
        } else {
            this.currentCategories = currentCategories;
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
        checkStatus();
        itemStatus.setText("<html> You have: <br>" + goodCondition.size() + " items in good condition" + "<html><br>"
                + expiringSoon.size() + " items expiring soon" + "<html><br>" + expired.size() + " items expired");
        textAdjustments(itemStatus);
        pane.add(itemStatus);

        addCategoryButton = new JButton("Add new category");
        addCategoryButton.addActionListener(this);
        pane.add(addCategoryButton);

        showAllCategories();

        saveFunction();

        pane.add(saveData);
        panel.add(pane);
        panel.setSize(360, 640);
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollBar(scrollPane, home);
    }

    private void checkStatus() {
        for (ArrayList<Items> items : currentCategories.getCategoryItems()) {
            for (Items item : items) {
                addStatus(item);
            }
        }
    }

    // MODIFIES: expired, expiringSoon, goodCondition
    // EFFECTS: creates new notification for this item and sort it into a group
    public void addStatus(Items i) {
        Notification dummy = new Notification(i.getDate());
        if (dummy.getExpired()) {
            expired.add(i);
        } else if (dummy.getNotified()) {
            expiringSoon.add(i);
        } else {
            goodCondition.add(i);
        }
    }

    private void saveFunction() {
        saveData = new JPanel(new GridLayout(2, 1));
        saveData.setBackground(Color.DARK_GRAY);
        JLabel save = new JLabel("Save data before exit?");
        textAdjustments(save);
        saveData.add(save);

        saveButton = new JButton("Yes, save my progress");
        saveButton.addActionListener(this);
        saveData.add(saveButton);
    }

    private void showAllCategories() {
        for (int i = 0; i < currentCategories.getCategoryName().size(); i++) {
            int itemIndex = i;
            Container allCategories = new JPanel(new GridLayout(2, 1));
            header(i, allCategories);
            ArrayList<Items> listOfItems = currentCategories.getCategoryItems().get(i);
            Container displayItems = new JPanel();
            displayItems.setLayout(new GridLayout(listOfItems.size(), 1));
            for (Items item : listOfItems) {
                int indexPosition = listOfItems.indexOf(item);
                JButton itemNumber = new JButton(String.valueOf(indexPosition + 1) + ".");
                itemNumber.addActionListener(e -> {
                    new AddItem(indexPosition + 1, currentCategories, currentAccount, home,
                            "edit", itemIndex);
                });
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
        addItemButton = new JButton("Add new item");
        addItemButton.addActionListener(e -> {
            new AddItem(i + 1, currentCategories, currentAccount, home, "ignore", i);
        });
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
        if (e.getSource() == saveButton) {
            new JsonWriter("./data/accountData.json", currentAccount);
            new JsonWriter("./data/categoriesData.json", currentCategories);
        }
    }
}