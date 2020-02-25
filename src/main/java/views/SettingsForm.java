package views;

import classes.State;
import globals.Colors;
import globals.Globals;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class SettingsForm {
    public JPanel mainPanel;
    public JPanel headerPanel;
    public JLabel applicationName;
    public JPanel settingsWrapper;
    public JPanel settingsInfoWrapper;
    public JLabel settingsInfoLabel;
    public JButton backToDashboardButton;
    public JPanel contentWrapper;
    public JPanel defaultLanguagePanel;
    public JPanel defaultColorPanel;
    public JPanel categoriesPanel;
    public JComboBox<String> defaultLanguageComboBox;
    public JPanel defaultLanguageComboBoxWrapper;
    public JLabel defaultLanguageLabel;
    public JLabel defaultColorLabel;
    public JPanel defaultThemeComboBoxWrapper;
    public JComboBox defaultColorThemeComboBox;
    public JLabel categoriesLabel;
    public JPanel categoriesTextBoxWrapper;
    public JTextArea categoriesTextArea;

    public SettingsForm(){

        applicationName.setText(Globals.APPLICATION_NAME);
        applicationName.setForeground(Colors.TEXT_COLOR);

        headerPanel.setBackground(Colors.HEADER_COLOR);
        headerPanel.setForeground(Colors.TEXT_COLOR);

        backToDashboardButton.addActionListener(e -> {
            try {
                handleBackToDashboardButton();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        backToDashboardButton.setIcon(getScaledImageIcons(new ImageIcon("src/main/assets/menu_icon.png"),50, 50));
        backToDashboardButton.setText("");
        setupOptionsButton(backToDashboardButton);

        for(String language : Globals.getAllProgramingLanguages()) {
            defaultLanguageComboBox.addItem(language);
        }
    }

    private void handleBackToDashboardButton() throws FileNotFoundException {

        Globals.currentState = State.STATE_DASHBOARD;
        new State().changeState(Globals.currentState);
    }

    private ImageIcon getScaledImageIcons(ImageIcon imageIcon, int width, int height){

        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);  // transform it back_
        return imageIcon;
    }
    private void setupOptionsButton(JButton optionButton){

        optionButton.setBackground(Color.LIGHT_GRAY);
        optionButton.setFocusPainted(false);
        optionButton.setBorderPainted(true);
        optionButton.setMinimumSize(new Dimension(80,80));
        optionButton.setPreferredSize(new Dimension(80,80));

        optionButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                optionButton.setBackground(Colors.OPTIONS_BUTTON_HOVER_COLOR);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                optionButton.setBackground(Colors.OPTIONS_BUTTON_COLOR);
            }
        });
    }
}
