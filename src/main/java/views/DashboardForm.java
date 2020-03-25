package views;

import classes.*;
import globals.ColorThemes;
import globals.Globals;
import views.listCellRenderers.RecentlyAddedCellRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

public class DashboardForm {
    public JPanel mainPanel;
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

    public DefaultListModel<Snippet> recentlyAddedListModel;
    ColorTheme colorTheme = ColorThemes.getCurrentSelectedColorTheme();

    public DashboardForm(){

        headerPannel.setBackground(colorTheme.getHeaderBackgroundColor());
        dashboardPannel.setBackground(colorTheme.getBackgroundColor());

        recentlyAddedListView.setBackground(colorTheme.getPanelBackgroundColor());
        applicationName.setText(Globals.APPLICATION_NAME);
        applicationName.setForeground(colorTheme.getHeaderTextColor());

        recentlyAddedListModel = new DefaultListModel<>();

        setupDashboard();
    }

    public void setupDashboard(){

        dashboardPannel.setBackground(colorTheme.getPanelBackgroundColor());

        allSnippetsButton.setIcon(getScaledImageIcons(new ImageIcon("src/main/assets/snippets_icon.png"),120, 120));
        setupDashboadButton(allSnippetsButton);
        allSnippetsButton.addActionListener(e -> handleAllSnippetsButton());

        addSnippetButton.setIcon(getScaledImageIcons(new ImageIcon("src/main/assets/add_snippet_icon.png"),120, 120));
        setupDashboadButton(addSnippetButton);
        addSnippetButton.addActionListener(e -> handleAddSnippetsButton());

        settingsButton.setIcon(getScaledImageIcons(new ImageIcon("src/main/assets/settings_icon.png"),120, 120));
        setupDashboadButton(settingsButton);
        settingsButton.addActionListener(e -> handleSettingsButtonClicked());

        for(Snippet snippet : Globals.snippetHelper.getSnippetsOrderByDateDescending(Globals.snippetHelper.getAllSnippets())){
            recentlyAddedListModel.addElement(snippet);
        }

        //Setting list view and selection mode
        recentlyAddedListView.setCellRenderer(new RecentlyAddedCellRenderer());
        recentlyAddedListView.setModel(recentlyAddedListModel);
        recentlyAddedListView.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        recentlyAddedListView.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList)evt.getSource();
                if (evt.getClickCount() == 2) {

                    int index = list.locationToIndex(evt.getPoint());

                    JPanel panel = new JPanel();
                    JDialog optionsDialog = new JDialog((JFrame)null, "Select an option");

                    panel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
                    panel.setLayout(new GridLayout(1,3, 20, 20));
                    JButton editButton = new JButton("Edit");
                    JButton copyButton = new JButton("Copy");
                    JButton deleteButton = new JButton("Delete");

                    editButton.setIcon(getScaledImageIcons(new ImageIcon("src/main/assets/edit_icon.png"),50, 50));
                    copyButton.setIcon(getScaledImageIcons(new ImageIcon("src/main/assets/copy_icon.png"),50, 50));
                    deleteButton.setIcon(getScaledImageIcons(new ImageIcon("src/main/assets/delete_icon.png"),50, 50));

                    setupOptionsButton(editButton);
                    setupOptionsButton(copyButton);
                    setupOptionsButton(deleteButton);

                    //Add eventListeners
                    deleteButton.addActionListener(e -> handleDeleteButtonPressed(optionsDialog, index));
                    copyButton.addActionListener(e -> handleCopyButtonPressed(optionsDialog, index));

                    panel.add(editButton);
                    panel.add(copyButton);
                    panel.add(deleteButton);


                    optionsDialog.getContentPane().add(panel);
                    setOptionsDialogSettings(optionsDialog);



                    optionsDialog.setVisible(true);

                }
            }
        });
    }


    private ImageIcon getScaledImageIcons(ImageIcon imageIcon, int width, int height){

        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);  // transform it back_
        return imageIcon;
    }

    private void setupDashboadButton(JButton dashboardButton){

        dashboardButton.setBackground(colorTheme.getDashboardButtonBackgroundColor());
        dashboardButton.setHorizontalTextPosition(JLabel.CENTER);
        dashboardButton.setVerticalTextPosition(JLabel.BOTTOM);
        dashboardButton.setForeground(colorTheme.getDashboardButtonTextColor());
        dashboardButton.setFocusPainted(false);
        dashboardButton.setBorderPainted(true);
        dashboardButton.setBorder(BorderFactory.createLineBorder(colorTheme.getDashboardButtonBorderColor(), 4));

        dashboardButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dashboardButton.setBackground(colorTheme.getDashboardButtonHoverBackgroundColor());
                dashboardButton.setBorder(BorderFactory.createLineBorder(colorTheme.getDashboardButtonHoverBorderColor(), 4));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                dashboardButton.setBackground(colorTheme.getDashboardButtonBackgroundColor());
                dashboardButton.setBorder(BorderFactory.createLineBorder(colorTheme.getDashboardButtonBorderColor(), 4));
            }
        });
    }

    private void setupOptionsButton(JButton optionButton){

        optionButton.setBackground(colorTheme.getOptionsButtonBackgroundColor());
        optionButton.setHorizontalTextPosition(JLabel.CENTER);
        optionButton.setVerticalTextPosition(JLabel.BOTTOM);
        optionButton.setForeground(colorTheme.getOptionsButtonTextColor());
        optionButton.setFocusPainted(false);
        optionButton.setBorderPainted(true);
        optionButton.setFont(new Font("Andale Mono", Font.BOLD, 14));
        optionButton.setBorder(BorderFactory.createLineBorder(colorTheme.getOptionsButtonBorderColor(), 2));
        optionButton.setMinimumSize(new Dimension(80,80));
        optionButton.setPreferredSize(new Dimension(80,80));

        optionButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                optionButton.setBackground(colorTheme.getOptionsButtonHoverBackgroundColor());
                optionButton.setBorder(BorderFactory.createLineBorder(colorTheme.getOptionsButtonHoverBorderColor(), 2));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                optionButton.setBackground(colorTheme.getOptionsButtonBackgroundColor());
                optionButton.setBorder(BorderFactory.createLineBorder(colorTheme.getOptionsButtonBorderColor(), 2));
            }
        });
    }



    private void setOptionsDialogSettings(JDialog optionsDialog){
        optionsDialog.pack();

        final Toolkit toolkit = Toolkit.getDefaultToolkit();
        final Dimension screenSize = toolkit.getScreenSize();
        final int x = (screenSize.width - optionsDialog.getWidth()) / 2;
        final int y = (screenSize.height - optionsDialog.getHeight()) / 2;
        optionsDialog.setLocation(x, y);

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addVetoableChangeListener("focusedWindow",
            new VetoableChangeListener() {
                private boolean gained = false;

                @Override
                public void vetoableChange(PropertyChangeEvent evt)
                        throws PropertyVetoException {
                    if (evt.getNewValue() == optionsDialog) {
                        gained = true;
                    }
                    if (gained && evt.getNewValue() != optionsDialog) {
                        optionsDialog.dispose();
                    }
                }
            });
    }

    private void handleDeleteButtonPressed(JDialog optionsDialog, int index){

        System.out.println("DeletePresses");

        Globals.snippetHelper.deleteSnippet(this.recentlyAddedListModel.elementAt(index));
        this.update();
        System.out.println("ListUpdated");
        optionsDialog.dispose();
    }

    private void handleCopyButtonPressed(JDialog optionsDialog, int index){

        System.out.println("CopyPressed");
        SingleClickCopy copyHelper = new SingleClickCopy();
        copyHelper.copy(this.recentlyAddedListModel.elementAt(index).getContent());
        System.out.println("ContentCopied");
        optionsDialog.dispose();
    }

    private void handleSettingsButtonClicked() {

        Globals.currentState = ApplicationState.STATE_SETTINGS;
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

    private void update(){

        recentlyAddedListModel = new DefaultListModel<>();

        for(Snippet snippet : Globals.snippetHelper.getSnippetsOrderByDateDescending(Globals.snippetHelper.getAllSnippets())){
            recentlyAddedListModel.addElement(snippet);
        }

        this.recentlyAddedListView.setModel(recentlyAddedListModel);
    }


}


