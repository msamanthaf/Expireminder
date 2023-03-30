package ui.gui;

import model.Account;
import model.Categories;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCategory extends JFrame implements ActionListener, ScreenAdjustment {
    private Categories currentCategories;
    private JFrame addCategoryPage;
    private JPanel panel;
    private JTextField nameBox = new JTextField(1);
    private JLabel invalidName = new JLabel();
    private JButton okButton;
    private Account currentAccount;

    public AddCategory(Categories currentCategories, Account currentAccount) {
        this.currentAccount = currentAccount;
        this.currentCategories = currentCategories;
        addCategoryPage = new JFrame();
        panel = new JPanel();
        Container pane = this.getContentPane();
        pane.setLayout(new GridLayout(7, 1));
        pane.setBackground(Color.DARK_GRAY);
        printComponents(panel, pane);
    }

    private void printComponents(JPanel panel, Container pane) {
        JLabel categoryName = new JLabel("<html> Category name:<br>   ");
        categoryName.setFont(new Font("Adobe Clean ExtraBold", Font.BOLD, 15));
        textAdjustments(categoryName);
        pane.add(categoryName);
        nameBox.setPreferredSize(new Dimension(200, 20));
        nameBox.addActionListener(this);
        pane.add(nameBox);
        pane.add(invalidName);

        okButton = new JButton("Ok");
        okButton.addActionListener(this);
        pane.add(okButton);
        panel.add(pane);
        panel.setBackground(Color.DARK_GRAY);
        screenAdjustment(panel, addCategoryPage);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == okButton) {
            String categoryName = nameBox.getText();
            checkValid(categoryName);
            if (!categoryName.isEmpty() && !currentCategories.getCategoryName().contains(categoryName)) {
                addCategoryPage.dispose();
                currentCategories.add(categoryName);
                new Homepage(currentAccount, currentCategories, "else");
            }
        }
    }

    private void checkValid(String name) {
        if (name.isEmpty() && currentCategories.getCategoryName().contains(name)) {
            invalidName.setText("A category name cannot be blank");
            invalidName.setFont(new Font("Adobe Clean ExtraBold", Font.BOLD, 10));
            invalidName.setForeground(Color.RED);
        } else if (name.isEmpty()) {
            invalidName.setText("A category name cannot be blank");
            invalidName.setFont(new Font("Adobe Clean ExtraBold", Font.BOLD, 10));
            invalidName.setForeground(Color.RED);
        } else if (currentCategories.getCategoryName().contains(name)) {
            invalidName.setText("Category name cannot be the same as existing ones");
            invalidName.setFont(new Font("Adobe Clean ExtraBold", Font.BOLD, 10));
            invalidName.setForeground(Color.RED);
        }
    }
}