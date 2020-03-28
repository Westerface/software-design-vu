package views;

import classes.ColorTheme;
import classes.Settings;
import classes.ApplicationState;
import classes.SnippetState;
import globals.ColorThemes;
import globals.Globals;
import globals.GlobalsViews;

import javax.swing.*;
import java.io.IOException;

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

    ColorTheme colorTheme = ColorThemes.getCurrentSelectedColorTheme();
    private GlobalsViews globalsViews;

    public SettingsForm() throws IOException {

        globalsViews = new GlobalsViews();
        globalsViews.setupNavigationButtons(allSnippetsButton, addSnippetButton,  settingsButton, dashboardButton,colorTheme);
        applicationName.setText(Globals.APPLICATION_NAME);

        headerPanel.setBackground(colorTheme.getHeaderBackgroundColor());
        applicationName.setForeground(colorTheme.getHeaderTextColor());

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
