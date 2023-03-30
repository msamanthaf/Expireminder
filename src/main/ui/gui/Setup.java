package ui.gui;

import model.Account;
import model.Categories;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Setup page where user first make their accounts
public class Setup extends JFrame implements ActionListener, ScreenAdjustment {
    private JFrame setupPage;
    private JPanel panel;
    private JTextField nameBox = new JTextField(1);
    private JTextField emailBox = new JTextField(1);
    private JButton nextButton;
    private String inputName;
    private String inputEmail;
    private JLabel invalidName = new JLabel();
    private JLabel invalidEmail = new JLabel();
    private Categories currentCategories;
    private Account currentAccount;
    private String inputCase;
    private Account account;
    private Categories category;

    // REQUIRES: the previous start page
    // EFFECTS: creates new page to set up an account
    public Setup(String inputCase, Account account, Categories category) {
        this.account = account;
        this.category = category;
        this.inputCase = inputCase;
        setupPage = new JFrame();
        panel = new JPanel();
        Container pane = this.getContentPane();
        pane.setLayout(new GridLayout(7, 1));
        pane.setBackground(Color.DARK_GRAY);
        printComponents(panel, pane);
    }

    // EFFECTS: Obtain user input for new account
    private void printComponents(JPanel panel, Container pane) {
        JLabel enterName = new JLabel("<html> Enter your name:<br>   ");
        enterName.setFont(new Font("Adobe Clean ExtraBold", Font.BOLD, 15));
        textAdjustments(enterName);
        pane.add(enterName);
        nameBox.setPreferredSize(new Dimension(200, 20));
        nameBox.addActionListener(this);
        pane.add(nameBox);
        pane.add(invalidName);

        JLabel enterEmail = new JLabel("<html> Enter your email address: <br>    ");
        enterEmail.setFont(new Font("Adobe Clean ExtraBold", Font.BOLD, 15));
        textAdjustments(enterEmail);
        pane.add(enterEmail);
        emailBox.setPreferredSize(new Dimension(200, 20));
        emailBox.addActionListener(this);
        pane.add(emailBox);
        pane.add(invalidEmail);

        nextButton = new JButton("Done");
        nextButton.addActionListener(this);
        pane.add(nextButton);
        panel.add(pane);
        panel.setBackground(Color.DARK_GRAY);
        screenAdjustment(panel, setupPage);
    }

    // EFFECTS: Creates new account and moves to next home JFrame when button is pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nextButton) {
            inputName = nameBox.getText();
            inputEmail = emailBox.getText();
            checkValid(inputName, inputEmail);
            if (!inputName.isEmpty() && !inputEmail.isEmpty()) {
                if (inputCase == "n") {
                    currentAccount = new Account(inputName, inputEmail);
                    currentCategories = new Categories();
                } else {
                    account.setAccount(inputName, inputEmail);
                    currentAccount = account;
                    currentCategories = category;
                }
                setupPage.dispose();
                new Homepage(currentAccount, currentCategories, inputCase);
            }
        }
    }

    // EFFECTS: Displays a message when input name and email is invalid
    private void checkValid(String inputName, String inputEmail) {
        if (inputName.isEmpty() && inputEmail.isEmpty()) {
            invalidName.setText("Please enter a valid name");
            invalidName.setFont(new Font("Adobe Clean ExtraBold", Font.BOLD, 10));
            invalidName.setForeground(Color.RED);
            invalidEmail.setText("Please enter a valid email address");
            invalidEmail.setFont(new Font("Adobe Clean ExtraBold", Font.BOLD, 10));
            invalidEmail.setForeground(Color.RED);
        } else if (inputName.isEmpty()) {
            invalidName.setText("Please enter a valid name");
            invalidName.setFont(new Font("Adobe Clean ExtraBold", Font.BOLD, 10));
            invalidName.setForeground(Color.RED);
        } else if (inputEmail.isEmpty()) {
            invalidEmail.setText("Please enter a valid email address");
            invalidEmail.setFont(new Font("Adobe Clean ExtraBold", Font.BOLD, 10));
            invalidEmail.setForeground(Color.RED);
        }
    }
}