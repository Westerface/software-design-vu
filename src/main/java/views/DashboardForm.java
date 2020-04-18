package views;

import classes.*;
import globals.ColorThemes;
import globals.Globals;
import globals.GlobalsViews;
import views.listCellRenderers.RecentlyAddedCellRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.io.IOException;

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

    private GlobalsViews globalsViews;

    public DashboardForm() throws IOException {

        headerPannel.setBackground(colorTheme.getHeaderBackgroundColor());
        dashboardPannel.setBackground(colorTheme.getBackgroundColor());

        recentlyAddedListView.setBackground(colorTheme.getPanelBackgroundColor());
        applicationName.setText(Globals.APPLICATION_NAME);
        applicationName.setForeground(colorTheme.getHeaderTextColor());

        recentlyAddedListModel = new DefaultListModel<>();
        globalsViews = new GlobalsViews();
        setupDashboard();
    }

    public void setupDashboard(){

        dashboardPannel.setBackground(colorTheme.getPanelBackgroundColor());

        allSnippetsButton.setIcon(globalsViews.getScaledImageIcons(globalsViews.getSnippetsIcon(),120, 120));
        globalsViews.setupButtonVisual(allSnippetsButton, 150, 150, colorTheme, true);
        allSnippetsButton.addActionListener(e -> {
            try {
                handleAllSnippetsButton();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        addSnippetButton.setIcon(globalsViews.getScaledImageIcons(globalsViews.getAddSnippetIcon(),120, 120));
        globalsViews.setupButtonVisual(addSnippetButton, 150, 150, colorTheme, true);
        addSnippetButton.addActionListener(e -> {
            try {
                handleAddSnippetsButton();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        settingsButton.setIcon(globalsViews.getScaledImageIcons(globalsViews.getSettingsIcon(),120, 120));
        globalsViews.setupButtonVisual(settingsButton, 150, 150, colorTheme, true);
        settingsButton.addActionListener(e -> {
            try {
                handleSettingsButtonClicked();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

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

                    editButton.setIcon(globalsViews.getScaledImageIcons(globalsViews.getEditIcon(),50, 50));
                    copyButton.setIcon(globalsViews.getScaledImageIcons(globalsViews.getCopyIcon(),50, 50));
                    deleteButton.setIcon(globalsViews.getScaledImageIcons(globalsViews.getDeleteIcon(),50, 50));

                    globalsViews.setupButtonVisual(editButton, 80, 80, colorTheme, false);
                    globalsViews.setupButtonVisual(copyButton, 80, 80, colorTheme, false);
                    globalsViews.setupButtonVisual(deleteButton, 80, 80, colorTheme, false);

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

        //System.out.println("DeletePresses");

        Globals.snippetHelper.deleteSnippet(this.recentlyAddedListModel.elementAt(index));
        this.update();
        //System.out.println("ListUpdated");
        optionsDialog.dispose();
    }

    private void handleCopyButtonPressed(JDialog optionsDialog, int index){

        //System.out.println("CopyPressed");
        SingleClickCopy copyHelper = new SingleClickCopy();
        copyHelper.copy(this.recentlyAddedListModel.elementAt(index).getContent());
        //System.out.println("ContentCopied");
        optionsDialog.dispose();
    }

    private void handleSettingsButtonClicked() throws IOException {

        Globals.currentState = ApplicationState.STATE_SETTINGS;
        new ApplicationState().changeState( Globals.currentState);
    }

    private void handleAllSnippetsButton() throws IOException {

        Globals.currentState = ApplicationState.STATE_ADD_SNIPPET;
        Globals.currentSnippetState = SnippetState.SNIPPET_NORMAL;
        new ApplicationState().changeState( Globals.currentState);
    }

    private void handleAddSnippetsButton() throws IOException {

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


