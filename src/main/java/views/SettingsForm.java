package views;

import classes.ColorTheme;
import classes.Settings;
import classes.ApplicationState;
import classes.SnippetState;
import globals.ColorThemes;
import globals.Globals;
import globals.GlobalsViews;

import javax.swing.*;
import java.awt.*;

public class SettingsForm {
    public JPanel mainPanel;
    public JPanel headerPanel;
    public JLabel applicationName;
    public JPanel settingsWrapper;
    public JPanel settingsInfoWrapper;
    public JLabel settingsInfoLabel;
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
    public JPanel navigationWrapper;
    public JButton dashboardButton;
    public JButton addSnippetButton;
    public JButton allSnippetsButton;
    public JButton settingsButton;

    //added so we can keep up with the current color theme since it will change when we change it in settings
    ColorTheme colorTheme = ColorThemes.getCurrentSelectedColorTheme();

    public SettingsForm(){

        applicationName.setText(Globals.APPLICATION_NAME);

        headerPanel.setBackground(colorTheme.getHeaderBackgroundColor());
        applicationName.setForeground(colorTheme.getHeaderTextColor());
//        applicationName.setForeground(ColorThemes.TEXT_COLOR);
//
//        headerPanel.setBackground(ColorThemes.HEADER_COLOR);
//        headerPanel.setForeground(ColorThemes.TEXT_COLOR);


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
        setupNavigationButtons();
    }

    private void setupNavigationButtons(){

        allSnippetsButton.setIcon(GlobalsViews.getScaledImageIcons(new ImageIcon("src/main/assets/snippets_icon.png"),30, 30));
        GlobalsViews.setupOptionsButton(allSnippetsButton, 50, 50, colorTheme);

        addSnippetButton.setIcon(GlobalsViews.getScaledImageIcons(new ImageIcon("src/main/assets/add_snippet_icon.png"),30, 30));
        GlobalsViews.setupOptionsButton(addSnippetButton, 50, 50, colorTheme);

        settingsButton.setIcon(GlobalsViews.getScaledImageIcons(new ImageIcon("src/main/assets/settings_icon.png"),30, 30));
        GlobalsViews.setupOptionsButton(settingsButton, 50, 50, colorTheme);

        dashboardButton.setIcon(GlobalsViews.getScaledImageIcons(new ImageIcon("src/main/assets/menu_icon.png"),30, 30));
        GlobalsViews.setupOptionsButton(dashboardButton, 50, 50, colorTheme);

        allSnippetsButton.addActionListener(e -> handleAllSnippetsButton());
        addSnippetButton.addActionListener(e -> handleAddSnippetsButton());
        settingsButton.addActionListener(e -> handleSettingsButtonClicked());
        dashboardButton.addActionListener(e -> handleDashboardButtonClicked());
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

    private void handleSettingsButtonClicked() {

        Globals.currentState = ApplicationState.STATE_SETTINGS;
        Globals.currentSnippetState = SnippetState.SNIPPET_NORMAL;
        new ApplicationState().changeState( Globals.currentState);
    }

    private void handleAllSnippetsButton() {

        Globals.currentState = ApplicationState.STATE_ADD_SNIPPET;
        Globals.currentSnippetState = SnippetState.SNIPPET_NORMAL;
        new ApplicationState().changeState( Globals.currentState);
    }

    private void handleAddSnippetsButton() {

        Globals.currentState = ApplicationState.STATE_ADD_SNIPPET;
        Globals.currentSnippetState = SnippetState.SNIPPET_ADD;
        new ApplicationState().changeState( Globals.currentState);
    }

    private void handleDashboardButtonClicked() {

        Globals.currentState = ApplicationState.STATE_DASHBOARD;
        Globals.currentSnippetState = SnippetState.SNIPPET_NORMAL;
        new ApplicationState().changeState( Globals.currentState);
    }
}
