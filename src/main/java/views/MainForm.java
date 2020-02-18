package views;

import classes.Snippet;
import globals.Colors;
import globals.Globals;
import views.listCellRenderers.RecentlyAddedCellRenderer;

import javax.swing.*;
import java.awt.*;

public class MainForm {
    public JPanel mainPannel;
    public JPanel headerPannel;
    public JPanel dashboardPannel;
    public JPanel buttonWrapper;
    public JButton addSnippetButton;
    public JButton settingsButton;
    public JButton allSnippetsButton;
    public JLabel applicationName;
    public JPanel listWrapper;
    public JList<Snippet> recentlyAddedListView;
    public JPanel recentlyAddedLabelHolder;
    public JLabel recentlyAddedLabel;

    public DefaultListModel<Snippet> listModel;

    public MainForm(){

        headerPannel.setBackground(Colors.HEADER_COLOR);

        applicationName.setText(Globals.APPLICATION_NAME);
        applicationName.setForeground(Colors.TEXT_COLOR);

        listModel = new DefaultListModel<>();

        setupDashboard();
    }

    public void setupDashboard(){

        dashboardPannel.setBackground(Colors.BACKGROUND_COLOR);

        allSnippetsButton.setIcon(getScaledImageIcons(new ImageIcon("src/main/assets/snippets_icon.png")));
        setupDashboadButton(allSnippetsButton);

        addSnippetButton.setIcon(getScaledImageIcons(new ImageIcon("src/main/assets/add_snippet_icon.png")));
        setupDashboadButton(addSnippetButton);

        settingsButton.setIcon(getScaledImageIcons(new ImageIcon("src/main/assets/settings_icon.png")));
        setupDashboadButton(settingsButton);

        for(Snippet snippet : Globals.snippetHelper.getAllSnippets()){
            listModel.addElement(snippet);
        }

        //Setting list view and selection mode
        recentlyAddedListView.setCellRenderer(new RecentlyAddedCellRenderer());
        recentlyAddedListView.setModel(listModel);
        recentlyAddedListView.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


    }


    private ImageIcon getScaledImageIcons(ImageIcon imageIcon){

        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);  // transform it back_
        return imageIcon;
    }

    private void setupDashboadButton(JButton dashboardButton){

        dashboardButton.setBackground(Colors.BUTTON_COLOR);
        dashboardButton.setHorizontalTextPosition(JLabel.CENTER);
        dashboardButton.setVerticalTextPosition(JLabel.BOTTOM);
        dashboardButton.setForeground(Colors.TEXT_COLOR);
        dashboardButton.setFocusPainted(false);
        dashboardButton.setBorderPainted(true);
        dashboardButton.setBorder(BorderFactory.createLineBorder(Colors.PANELS_COLOR, 4));

        dashboardButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dashboardButton.setBackground(Colors.PANELS_COLOR);
                dashboardButton.setBorder(BorderFactory.createLineBorder(Colors.BUTTON_COLOR, 4));;
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                dashboardButton.setBackground(Colors.BUTTON_COLOR);
                dashboardButton.setBorder(BorderFactory.createLineBorder(Colors.PANELS_COLOR, 4));
            }
        });

    }
}


