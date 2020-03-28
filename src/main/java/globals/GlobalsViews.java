package globals;

import classes.ApplicationState;
import classes.ColorTheme;
import classes.SnippetState;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class GlobalsViews {

    private ClassLoader classloader;
    
    private InputStream addImage;
    private InputStream addSnippetImage;
    private InputStream copyImage;
    private InputStream deleteImage;
    private InputStream editImage;
    private InputStream menuImage;
    private InputStream saveImage;
    private InputStream settingsImage;
    private InputStream snippetsImage;
    
    private ImageIcon addIcon;
    private ImageIcon addSnippetIcon;
    private ImageIcon copyIcon;
    private ImageIcon deleteIcon;
    private ImageIcon editIcon;
    private ImageIcon menuIcon;
    private ImageIcon saveIcon;
    private ImageIcon settingsIcon;
    private ImageIcon snippetsIcon;

    public GlobalsViews() throws IOException {

        classloader = Thread.currentThread().getContextClassLoader();

        addImage = classloader.getResourceAsStream("assets/add_icon.png");
        addSnippetImage = classloader.getResourceAsStream("assets/add_snippet_icon.png");
        copyImage = classloader.getResourceAsStream("assets/copy_icon.png");
        deleteImage = classloader.getResourceAsStream("assets/delete_icon.png");
        editImage = classloader.getResourceAsStream("assets/edit_icon.png");
        menuImage = classloader.getResourceAsStream("assets/menu_icon.png");
        saveImage = classloader.getResourceAsStream("assets/save_icon.png");
        settingsImage = classloader.getResourceAsStream("assets/settings_icon.png");
        snippetsImage = classloader.getResourceAsStream("assets/snippets_icon.png");

        addIcon = new ImageIcon(ImageIO.read(addImage));
        addSnippetIcon = new ImageIcon(ImageIO.read(addSnippetImage));
        copyIcon = new ImageIcon(ImageIO.read(copyImage));
        deleteIcon = new ImageIcon(ImageIO.read(deleteImage));
        editIcon = new ImageIcon(ImageIO.read(editImage));
        menuIcon = new ImageIcon(ImageIO.read(menuImage));
        saveIcon = new ImageIcon(ImageIO.read(saveImage));
        settingsIcon = new ImageIcon(ImageIO.read(settingsImage));
        snippetsIcon = new ImageIcon(ImageIO.read(snippetsImage));
    }

    public ImageIcon getScaledImageIcons(ImageIcon imageIcon, int width, int height){

        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);  // transform it back_

        return imageIcon;
    }

    public void setupButtonVisual(JButton optionButton, int width, int height, ColorTheme colorTheme, boolean dashboardButton){

        optionButton.setBackground(colorTheme.getOptionsButtonBackgroundColor());
        optionButton.setFocusPainted(false);
        optionButton.setBorderPainted(true);
        optionButton.setMinimumSize(new Dimension(width,height));
        optionButton.setPreferredSize(new Dimension(width,height));
        optionButton.setHorizontalTextPosition(JLabel.CENTER);
        optionButton.setVerticalTextPosition(JLabel.BOTTOM);
        optionButton.setFont(new Font("Andale Mono", Font.PLAIN, 12));
        optionButton.setForeground(colorTheme.getDashboardButtonTextColor());

        optionButton.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseEntered(java.awt.event.MouseEvent evt) {

                optionButton.setBackground(colorTheme.getOptionsButtonHoverBackgroundColor());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {

                optionButton.setBackground(colorTheme.getOptionsButtonBackgroundColor());
            }
        });

        if(dashboardButton){

            optionButton.setFont(new Font("Andale Mono", Font.BOLD, 18));
        }
    }

    public void setupNavigationButtons(JButton allSnippetsButton, JButton addSnippetButton, JButton settingsButton, JButton dashboardButton, ColorTheme colorTheme) {

        dashboardButton.setIcon(getScaledImageIcons(menuIcon, 30, 30));
        setupButtonVisual(dashboardButton, 50, 50, colorTheme, false);

        allSnippetsButton.setIcon(getScaledImageIcons(snippetsIcon, 30, 30));
        setupButtonVisual(allSnippetsButton, 50, 50, colorTheme, false);

        addSnippetButton.setIcon(getScaledImageIcons(addSnippetIcon , 30, 30));
        setupButtonVisual(addSnippetButton, 50, 50, colorTheme, false);

        settingsButton.setIcon(getScaledImageIcons(settingsIcon,30, 30));
        setupButtonVisual(settingsButton, 50, 50, colorTheme, false);


        dashboardButton.addActionListener(e -> {
            try {
                handleDashboardButtonClicked();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        allSnippetsButton.addActionListener(e -> {
            try {
                handleAllSnippetsButton();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        addSnippetButton.addActionListener(e -> {
            try {
                handleAddSnippetsButton();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        settingsButton.addActionListener(e -> {
            try {
                handleSettingsButtonClicked();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    public void handleSettingsButtonClicked() throws IOException {

        Globals.currentState = ApplicationState.STATE_SETTINGS;
        Globals.currentSnippetState = SnippetState.SNIPPET_NORMAL;
        new ApplicationState().changeState( Globals.currentState);
    }

    public void handleAllSnippetsButton() throws IOException {

        Globals.currentState = ApplicationState.STATE_ADD_SNIPPET;
        Globals.currentSnippetState = SnippetState.SNIPPET_NORMAL;
        new ApplicationState().changeState( Globals.currentState);
    }

    public void handleAddSnippetsButton() throws IOException {

        Globals.currentState = ApplicationState.STATE_ADD_SNIPPET;
        Globals.currentSnippetState = SnippetState.SNIPPET_ADD;
        new ApplicationState().changeState( Globals.currentState);
    }

    public void handleDashboardButtonClicked() throws IOException {

        Globals.currentState = ApplicationState.STATE_DASHBOARD;
        Globals.currentSnippetState = SnippetState.SNIPPET_NORMAL;
        new ApplicationState().changeState( Globals.currentState);
    }

    public ImageIcon getAddIcon() {
        return addIcon;
    }

    public ImageIcon getAddSnippetIcon() {
        return addSnippetIcon;
    }

    public ImageIcon getCopyIcon() {
        return copyIcon;
    }

    public ImageIcon getDeleteIcon() {
        return deleteIcon;
    }

    public ImageIcon getEditIcon() {
        return editIcon;
    }

    public ImageIcon getMenuIcon() {
        return menuIcon;
    }

    public ImageIcon getSaveIcon() {
        return saveIcon;
    }

    public ImageIcon getSettingsIcon() {
        return settingsIcon;
    }

    public ImageIcon getSnippetsIcon() {
        return snippetsIcon;
    }
}
