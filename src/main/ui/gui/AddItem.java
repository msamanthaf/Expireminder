package ui.gui;

import model.Account;
import model.Categories;
import model.Items;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

// Pop up window that allows user interaction to add new items
public class AddItem extends JFrame implements ActionListener, ScreenAdjustment {
    private JFrame home;
    private Categories currentCategories;
    private Account currentAccount;
    private int index;
    private int categoryIndex;
    private JFrame addItemPage;
    private JPanel panel;
    private JTextField nameBox = new JTextField(1);
    private JTextField dateBox;
    private JSpinner quantity;
    private JLabel invalidName = new JLabel();
    private JLabel invalidDate = new JLabel();
    private JButton okButton;
    private JButton renameButton;
    private JButton deleteButton;
    private SpinnerNumberModel spinnerModel;

    // REQUIRES: previous home page, non-null Account and Categories, valid category and item index
    // EFFECTS: initialize a new area to add new items
    public AddItem(int index, Categories currentCategories, Account currentAccount, JFrame home,
                   String input, int categoryIndex) {
        this.currentAccount = currentAccount;
        this.currentCategories = currentCategories;
        this.index = index;
        this.home = home;
        this.categoryIndex = categoryIndex;
        addItemPage = new JFrame();
        panel = new JPanel();
        panel.setBackground(Color.DARK_GRAY);
        Container pane = this.getContentPane();
        pane.setLayout(new GridLayout(11, 1));
        pane.setBackground(Color.DARK_GRAY);

        if (input == "edit") {
            printComponents(pane, "edit");
        } else {
            printComponents(pane, input);
        }

        popUp(panel, addItemPage, 320, 340);
    }

    // MODIFIES: this
    // EFFECTS: Displays window for user input
    private void printComponents(Container pane, String input) {
        JLabel enterName = new JLabel("Item name: ");
        enterName.setFont(new Font("Adobe Clean ExtraBold", Font.BOLD, 10));
        textAdjustments(enterName);
        pane.add(enterName);

        nameBox = new JTextField(1);
        pane.add(nameBox);
        pane.add(invalidName);

        JLabel enterQuantity = new JLabel("Item Quantity: ");
        enterQuantity.setFont(new Font("Adobe Clean ExtraBold", Font.BOLD, 10));
        textAdjustments(enterQuantity);
        pane.add(enterQuantity);

        spinnerModel = new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1);
        quantity = new JSpinner(spinnerModel);
        pane.add(quantity);
        pane.add(new JLabel());

        enterDate(pane);

        if (input == "edit") {
            editItem(pane);
        } else {
            okButton = new JButton("Ok");
            okButton.addActionListener(this);
            pane.add(okButton);
        }
    }

    // MODIFIES: this
    // EFFECTS: Displays window when user wants to edit items
    private void editItem(Container pane) {
        ArrayList<Items> arrayOfItems = currentCategories.getCategoryItems().get(categoryIndex);
        Items selected = arrayOfItems.get(index - 1);

        nameBox.setText(selected.getName());
        spinnerModel = new SpinnerNumberModel(selected.getQuantity(), 1, Integer.MAX_VALUE, 1);
        quantity.setModel(spinnerModel);
        dateBox.setText(selected.getDate());

        renameButton = new JButton("Change");
        renameButton.addActionListener(this);
        pane.add(renameButton);

        deleteButton = new JButton("Delete item");
        deleteButton.addActionListener(this);
        pane.add(deleteButton);
    }

    // MODIFIES: this
    // EFFECTS: Allows user to input expiry date
    private void enterDate(Container pane) {
        JLabel enterDate = new JLabel("Expiry Date: ");
        enterDate.setFont(new Font("Adobe Clean ExtraBold", Font.BOLD, 10));
        textAdjustments(enterDate);
        pane.add(enterDate);

        dateBox = new JTextField("MM/dd/yyyy");
        pane.add(dateBox);
        panel.add(pane);
        pane.add(invalidDate);
    }

    // EFFECTS: Runs new JFrame windows based on button pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == okButton) {
            addNew();
        }

        if (e.getSource() == renameButton) {
            modifyItem();
        }

        if (e.getSource() == deleteButton) {
            currentCategories.deleteItem(categoryIndex, index - 1);
            new Homepage(currentAccount, currentCategories, "else");
            addItemPage.dispose();
            home.dispose();
        }
    }

    // MODIFIES: this
    // EFFECTS: Creates new item
    private void addNew() {
        String itemName = nameBox.getText();
        int itemQuantity = (int) quantity.getValue();
        String itemDate = dateBox.getText();
        checkValid(itemName, itemDate);
        if (!itemName.isEmpty() && !itemDate.isEmpty() && isValidDate(itemDate)) {
            Items item = new Items(itemName, itemQuantity, itemDate);
            currentCategories.addItem(item, index);
            new Homepage(currentAccount, currentCategories, "else");
            addItemPage.dispose();
            home.dispose();
        }
    }


    // MODIFIES: this
    // EFFECTS: Edits specified item
    private void modifyItem() {
        String itemName = nameBox.getText();
        int itemQuantity = (int) quantity.getValue();
        String itemDate = dateBox.getText();
        checkValid(itemName, itemDate);
        if (!itemName.isEmpty() && !itemDate.isEmpty() && isValidDate(itemDate)) {
            ArrayList<Items> arrayOfItems = currentCategories.getCategoryItems().get(categoryIndex);
            Items selected = arrayOfItems.get(index - 1);
            selected.modifyItem(itemName, itemQuantity, itemDate);
            new Homepage(currentAccount, currentCategories, "else");
            addItemPage.dispose();
            home.dispose();
        }
    }

    // EFFECTS: returns true if the input date is a valid date
    private boolean isValidDate(String date) {
        String dateFormat = "MM/dd/yyyy";
        DateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);
        try {
            sdf.parse(date);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    // MODIFIES: this
    // EFFECTS: checks whether input name and date is valid
    private void checkValid(String name, String date) {
        if (name.isEmpty()) {
            invalidName.setText("An item name cannot be blank");
            invalidName.setFont(new Font("Adobe Clean ExtraBold", Font.BOLD, 10));
            invalidName.setForeground(Color.RED);
        }
        if (date.isEmpty() || !isValidDate(date)) {
            invalidDate.setText("Please enter a valid date");
            invalidDate.setFont(new Font("Adobe Clean ExtraBold", Font.BOLD, 10));
            invalidDate.setForeground(Color.RED);
        }
    }
}