package ui.gui;

import model.Account;
import model.Categories;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditCategory extends JFrame implements ActionListener, ScreenAdjustment {
    private Categories currentCategories;
    private JFrame editCategoryPage;
    private JFrame home;
    private JPanel panel;
    private JTextField nameBox = new JTextField(1);
    private JLabel invalidName = new JLabel();
    private JButton renameButton;
    private JButton deleteButton;
    private JButton backButton;
    private Account currentAccount;
    private int index;

    public EditCategory(int index, Categories currentCategories, Account currentAccount, JFrame home) {
        this.currentAccount = currentAccount;
        this.currentCategories = currentCategories;
        this.index = index;
        this.home = home;
        editCategoryPage = new JFrame();
        panel = new JPanel();
        panel.setBackground(Color.DARK_GRAY);
        Container pane = this.getContentPane();
        pane.setLayout(new GridLayout(3, 1));
        pane.setBackground(Color.DARK_GRAY);

        JPanel renamePane = new JPanel();
        renamePane.setLayout(new GridLayout(1, 2));
        renamePane.setBackground(Color.DARK_GRAY);
        printRename(renamePane);

        JPanel paneButtons = new JPanel();
        paneButtons.setLayout(new GridLayout(1, 2));
        paneButtons.setBackground(Color.DARK_GRAY);
        printButtons(paneButtons);
        panel.add(renamePane);
        panel.add(invalidName);
        panel.add(paneButtons);

        popUp(panel, editCategoryPage, 360, 120);
    }

    private void printRename(Container pane) {
        JLabel enterName = new JLabel("New category name:");
        enterName.setFont(new Font("Adobe Clean ExtraBold", Font.BOLD, 10));
        textAdjustments(enterName);
        pane.add(enterName);

        nameBox = new JTextField(1);
        nameBox.setPreferredSize(new Dimension(200, 20));
        pane.add(nameBox);

        renameButton = new JButton("Ok");
        renameButton.addActionListener(this);
        pane.add(renameButton);
    }

    private void printButtons(Container pane) {
        deleteButton = new JButton("Delete Category");
        deleteButton.addActionListener(this);
        pane.add(deleteButton);

        backButton = new JButton("Back");
        backButton.addActionListener(this);
        pane.add(backButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == renameButton) {
            String newName = nameBox.getText();
            checkValid(newName);
            if (!newName.isEmpty() && !currentCategories.getCategoryName().contains(newName)) {
                currentCategories.rename(index, newName);
                new Homepage(currentAccount, currentCategories, "else");
                editCategoryPage.dispose();
                home.dispose();
            }
        }
        if (e.getSource() == deleteButton) {
            currentCategories.delete(index - 1);
            new Homepage(currentAccount, currentCategories, "else");
            editCategoryPage.dispose();
            home.dispose();
        }
        if (e.getSource() == backButton) {
            editCategoryPage.dispose();
        }
    }

    private void checkValid(String name) {
        if (name.isEmpty() && currentCategories.getCategoryName().contains(name)) {
            invalidName.setText("A category name cannot be blank");
            invalidName.setFont(new Font("Adobe Clean ExtraBold", Font.BOLD, 8));
            invalidName.setForeground(Color.RED);
        } else if (name.isEmpty()) {
            invalidName.setText("A category name cannot be blank");
            invalidName.setFont(new Font("Adobe Clean ExtraBold", Font.BOLD, 8));
            invalidName.setForeground(Color.RED);
        } else if (currentCategories.getCategoryName().contains(name)) {
            invalidName.setText("Category name cannot be the same as existing ones");
            invalidName.setFont(new Font("Adobe Clean ExtraBold", Font.BOLD, 8));
            invalidName.setForeground(Color.RED);
        }
    }
}