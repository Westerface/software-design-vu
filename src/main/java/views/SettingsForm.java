package views;

import classes.ColorTheme;
import classes.Settings;
import classes.State;
import globals.ColorThemes;
import globals.Globals;

import javax.swing.*;
import java.awt.*;

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
    public JComboBox<String> defaultColorThemeComboBox;
    public JLabel categoriesLabel;
    public JPanel categoriesTextBoxWrapper;
    public JTextArea categoriesTextArea;
    public JButton saveButton;
    public JPanel saveButtonWrapper;

    //added so we can keep up with the current color theme since it will change when we change it in settings
    ColorTheme colorTheme = ColorThemes.getCurrentSelectedColorTheme();

    public SettingsForm(){

        applicationName.setText(Globals.APPLICATION_NAME);
//        applicationName.setForeground(ColorThemes.TEXT_COLOR);
//
//        headerPanel.setBackground(ColorThemes.HEADER_COLOR);
//        headerPanel.setForeground(ColorThemes.TEXT_COLOR);

        backToDashboardButton.addActionListener(e -> {
            try {
                handleBackToDashboardButton();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        backToDashboardButton.setIcon(getScaledImageIcons(new ImageIcon("src/main/assets/menu_icon.png"),50, 50));
        backToDashboardButton.setText("");
        setupOptionsButton(backToDashboardButton);

        for(String language : Globals.getAllProgramingLanguages()) {
            defaultLanguageComboBox.addItem(language);
        }


        for(String colorThemes : Globals.colorThemesNames) {

            this.defaultColorThemeComboBox.addItem(colorThemes);
        }

        categoriesTextArea.setText(Globals.settingsParser.getSettings().getCategories());
        categoriesTextArea.setLineWrap(true);
        categoriesTextArea.setWrapStyleWord(true);

        this.saveButton.addActionListener(e -> handleSaveButtonClick());

        setCurrentElements();
    }

    private void handleSaveButtonClick(){

        Settings updatedSettings = new Settings();
        updatedSettings.setDefaultLanguage(String.valueOf(this.defaultLanguageComboBox.getSelectedItem()));

        String colorTheme = String.valueOf(this.defaultColorThemeComboBox.getSelectedItem()).toUpperCase().replace(" ", "_");
        updatedSettings.setColorTheme(colorTheme);
        updatedSettings.setCategories(this.categoriesTextArea.getText());

        Globals.settingsParser.setSettings(updatedSettings);
        Globals.currentColorTheme = colorTheme;
        ColorThemes.getCurrentSelectedColorTheme();
    }

    private void handleBackToDashboardButton() {

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
                optionButton.setBackground(Globals.colorTheme.getOptionsButtonHoverBackgroundColor());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                optionButton.setBackground(Globals.colorTheme.getOptionsButtonBackgroundColor());
            }
        });
    }

    private void setCurrentElements(){
        this.categoriesTextArea.setText(Globals.settingsParser.getSettings().getCategories());
        this.defaultLanguageComboBox.setSelectedItem(Globals.settingsParser.getSettings().getDefaultLanguage());
        switch (Globals.settingsParser.getSettings().getColorTheme()){
            case "THE_PINK":
                this.defaultColorThemeComboBox.setSelectedItem("The Pink");
                break;
            case "THE_ORANGE":
                this.defaultColorThemeComboBox.setSelectedItem("The Orange");
                break;
            case "THE_GREEN":
                this.defaultColorThemeComboBox.setSelectedItem("The Green");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + Globals.settingsParser.getSettings().getColorTheme());
        }
    }
}
