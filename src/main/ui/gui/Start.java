package ui.gui;

import model.Account;
import model.Categories;
import persistence.JsonReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Expiry date notifier application start page
public class Start extends JFrame implements ActionListener, ScreenAdjustment {
    private JFrame startPage;
    private ImageIcon logo;
    private Image splashLogo;
    private JLabel welcome;
    private JLabel question;
    private JLabel invalid;
    private JButton yesButton;
    private JButton noButton;
    JLabel space = new JLabel("                              ");
    private Account currentAccount;

    // MODIFIES: this
    // EFFECTS: runs the first window
    public Start() {
        printWelcome();
        displayButton();

        JPanel screen = new JPanel();
        screen.add(welcome);
        screen.add(question);
        screen.add(space);
        screen.add(yesButton);
        screen.add(noButton);
        screen.setBackground(Color.DARK_GRAY);
        screen.setAlignmentY(Component.CENTER_ALIGNMENT);

        startPage = new JFrame();
        screenAdjustment(screen, startPage);
    }

    // MODIFIES: this
    // EFFECTS: Displays the welcome page
    private void printWelcome() {
        logo = new ImageIcon("data/Expireminder.png");
        splashLogo = logo.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon homeLogo = new ImageIcon(splashLogo);
        welcome = new JLabel(homeLogo);
        welcome.setText("<html><br>Welcome! Let's get started");
        welcome.setFont(new Font("Adobe Clean ExtraBold", Font.BOLD, 20));
        textAdjustments(welcome);
        question = new JLabel("<html><br>Already have an account?");
        question.setFont(new Font("Adobe Clean ExtraBold", Font.BOLD, 15));
        textAdjustments(question);
    }

    // MODIFIES: this
    // EFFECTS: Display options as buttons to load saved data
    private void displayButton() {
        yesButton = new JButton("<html> Yes, load data");
        yesButton.setBounds(250, 320, 80, 50);
        yesButton.addActionListener(this);

        noButton = new JButton("<html> No, create new account");
        noButton.setBounds(250, 320, 80, 50);
        noButton.addActionListener(this);
    }

    // EFFECTS: Goes to next JFrame page when button is pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == yesButton) {
            JsonReader accountReader = new JsonReader("./data/accountData.json");
            currentAccount = accountReader.readAccount();
            while (currentAccount == null) {
                invalid = new JLabel("No saved accounts found");
            }
            startPage.dispose();
            new Homepage(currentAccount, new Categories(), "y");
        }

        if (e.getSource() == noButton) {
            startPage.dispose();
            new Setup("n", currentAccount, new Categories());
        }
    }
}