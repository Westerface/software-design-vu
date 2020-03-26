package views;

import classes.*;
import globals.ColorThemes;
import globals.Globals;
import org.fife.ui.rsyntaxtextarea.*;
import views.listCellRenderers.AllSnippetsCellRenderer;
import views.listCellRenderers.FilterListCellRenderer;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;

public class AllSnippetsForm {
    public JPanel mainPanel;
    public JPanel headerPanel;
    public JLabel applicationName;
    public JPanel contentWrapper;
    public JPanel navigationWrapper;
    public JPanel snippetsWrapper;
    public JPanel allSnippetsWrapper;
    public JPanel addSnippetWrapper;
    public JPanel filtersWrapper;
    public JPanel allSnippetsPanel;
    public JButton dashboardButton;
    public JButton addSnippetButton;
    public JButton allSnippetsButton;
    public JButton settingsButton;
    public JPanel searchPanel;
    public JPanel orderByWrapper;
    public JComboBox<String> orderByDropdown;
    public JLabel orderByLabel;
    public JLabel searchLabel;
    public JTextField searchTextField;
    public JList<Snippet> allSnippets;
    public JPanel snippetOptionsWrapper;
    public JPanel snippetOptions;
    public JPanel snippetWrapper;
    public JTextField snippetNameTextField;
    public JComboBox<String> snippetLanguageDropdown;
    public JLabel snippetNameLabel;
    public JLabel snippetLanguageLabel;
    public JPanel snippetContent;
    public JButton saveSnippetButton;
    public JLabel categoryLabel;
    public JTextField categoryTextField;
    public JPanel filters;
    public JList<String> programingLanguagesFilterList;
    public JList<String> categoriesFilterList;
    public JLabel programingLanguagesFilerLabel;
    public JLabel categoriesFilerLabel;
    public JScrollPane allSnippetsScrollPane;
    public JScrollPane programingLanguageFilterScrollPane;
    public JScrollPane categoriesFilterScrollPane;
    public JPanel addEditDeleteWrapper;
    public JPanel addDeleteButtonWrapper;
    public JButton addButton;
    public JButton deleteButton;
    public JButton copyButton;
    public JPanel buttonsPanel;
    public JPanel saveCancelButtonWrapper;
    public JButton cancelButton;

    private RSyntaxTextArea textArea = new RSyntaxTextArea(20, 60);
    private DefaultListModel<Snippet> allSnippetsModel = new DefaultListModel<>();

    private ArrayList<String> selectedCategories = new ArrayList<>();
    private ArrayList<String> selectedLanguages = new ArrayList<>();
    boolean newSnippet = false;

    ColorTheme colorTheme = ColorThemes.getCurrentSelectedColorTheme();

    public AllSnippetsForm() {

        headerPanel.setBackground(colorTheme.getHeaderBackgroundColor());
        applicationName.setForeground(colorTheme.getHeaderTextColor());
        applicationName.setText(Globals.APPLICATION_NAME);
        createTextArea();
        addProgramingLanguages();
        createFilters();
        createAllSnippetsList();
        setupListeners();
        setupNavigationButtons();
        setupOrderByDropdown();


        if(!newSnippet){

            allSnippets.setSelectedIndex(0);
        }
    }

    private void setupListeners(){

        this.snippetLanguageDropdown.addActionListener(e -> changeProgramingLanguage(String.valueOf(this.snippetLanguageDropdown.getSelectedItem())));
        this.saveSnippetButton.addActionListener(e -> handleSaveSnippet());
        this.allSnippets.addListSelectionListener(e -> handleSelectSnippet());
        this.addButton.addActionListener(e -> handleAddButtonClicked());
        this.cancelButton.addActionListener(e -> handleCancelButtonClicked());
        this.categoriesFilterList.addListSelectionListener(e -> handleCategoryFilterSelected());
        this.programingLanguagesFilterList.addListSelectionListener(e -> handleProgrammingLanguageFilterSelected());
        this.cancelButton.setEnabled(false);
        this.orderByDropdown.addActionListener(e -> handleOrderBySelected(this.orderByDropdown.getSelectedIndex()));
        checkCurrentSnippetState(Globals.currentSnippetState);
        this.deleteButton.addActionListener(e -> handleDeleteButtonClicked());
        this.copyButton.addActionListener(e -> handleCopyButtonClicked());

        searchTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                search();
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                search();
            }

            @Override
            public void changedUpdate(DocumentEvent documentEvent) {
                search();
            }
        });
    }

    private void setupOrderByDropdown(){

        // TODO: The order added will be playing an ID role later;
        this.orderByDropdown.addItem("Order by name ascending"); // 0
        this.orderByDropdown.addItem("Order by name descending"); // 1
        this.orderByDropdown.addItem("Order by date added ascending"); // 2
        this.orderByDropdown.addItem("Order by date added descending"); // 3
        this.orderByDropdown.addItem("Order by date modified"); // 4

        this.orderByDropdown.setBackground(Color.WHITE);
    }

    private void handleOrderBySelected(int selectedOrderType){
        System.out.println(selectedOrderType);
        switch (selectedOrderType) {
            case 0:
                Globals.currentSnippetOrder = SnippetState.SNIPPET_ORDER_NAME_ASCENDING;
                orderList();
                break;
            case 1:
                Globals.currentSnippetOrder = SnippetState.SNIPPET_ORDER_NAME_DESCENDING;
                orderList();
                break;
            case 2:
                Globals.currentSnippetOrder = SnippetState.SNIPPET_ORDER_DATE_ASCENDING;
                orderList();
                break;
            case 3:
                Globals.currentSnippetOrder = SnippetState.SNIPPET_ORDER_DATE_DESCENDING;
                orderList();
                break;
            case 4:
                Globals.currentSnippetOrder = SnippetState.SNIPPET_ORDER_DATE_MODIFIED;
                orderList();
                break;
        }
    }

    private void setupNavigationButtons(){

        allSnippetsButton.setIcon(getScaledImageIcons(new ImageIcon("src/main/assets/snippets_icon.png"),30, 30));
        setupOptionsButton(allSnippetsButton, 50, 50);

        addSnippetButton.setIcon(getScaledImageIcons(new ImageIcon("src/main/assets/add_snippet_icon.png"),30, 30));
        setupOptionsButton(addSnippetButton, 50, 50);

        settingsButton.setIcon(getScaledImageIcons(new ImageIcon("src/main/assets/settings_icon.png"),30, 30));
        setupOptionsButton(settingsButton, 50, 50);

        dashboardButton.setIcon(getScaledImageIcons(new ImageIcon("src/main/assets/menu_icon.png"),30, 30));
        setupOptionsButton(dashboardButton, 50, 50);

        allSnippetsButton.addActionListener(e -> handleAllSnippetsButton());
        addSnippetButton.addActionListener(e -> handleAddSnippetsButton());
        settingsButton.addActionListener(e -> handleSettingsButtonClicked());
        dashboardButton.addActionListener(e -> handleDashboardButtonClicked());
    }

    private void createTextArea(){

        if(Globals.settingsParser.getSettings().getDefaultLanguage() != null && Globals.settingsParser.getSettings().getDefaultLanguage().length() > 0){

            changeProgramingLanguage(Globals.settingsParser.getSettings().getDefaultLanguage());
            addProgramingLanguages();
            snippetLanguageDropdown.setSelectedItem(Globals.settingsParser.getSettings().getDefaultLanguage());
        } else {

            changeProgramingLanguage(SyntaxConstants.SYNTAX_STYLE_LATEX);
        }
        textArea.setCodeFoldingEnabled(true);
        textArea.setAutoIndentEnabled(true);

//        InputStream in = getClass().getResourceAsStream("src/main/assets/textAreaThemes/dark.xml");
//        try {
//            Theme theme = Theme.load(in);
//            theme.apply(textArea);
//        } catch (IOException ioe) { // Never happens
//            ioe.printStackTrace();
//        }

        snippetContent.add(textArea);

    }

    private void handleSelectSnippet(){

        if(allSnippets.getSelectedValue() != null){

            Snippet snippetToDisplay = allSnippets.getSelectedValue();
            snippetNameTextField.setText(snippetToDisplay.getName());
            categoryTextField.setText(snippetToDisplay.getCategories());
            textArea.setText(snippetToDisplay.getContent());
            snippetLanguageDropdown.setSelectedItem(snippetToDisplay.getProgramingLanguage());
            changeProgramingLanguage(snippetToDisplay.getProgramingLanguage());

            if(!cancelButton.isEnabled()){

                cancelButton.setEnabled(true);
            }
         }
    }

    private void createAllSnippetsList(){

        for(Snippet snippet : Globals.snippetHelper.getSnippetsOrderByNameAscending(Globals.snippetHelper.getAllSnippets())){

            allSnippetsModel.addElement(snippet);
        }

        //Setting list view and selection mode
        allSnippets.setCellRenderer(new AllSnippetsCellRenderer());
        allSnippets.setModel(allSnippetsModel);
        allSnippets.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void addProgramingLanguages(){

        for(String language : Globals.getAllProgramingLanguages()) {

            this.snippetLanguageDropdown.addItem(language);
        }

        this.snippetLanguageDropdown.setBackground(Color.WHITE);
    }

    public void createFilters(){

        DefaultListModel<String> programingLanguages = new DefaultListModel<>();

        for(String language : Globals.getAllProgramingLanguages()){

            programingLanguages.addElement(language);
        }
        programingLanguagesFilterList.setModel(programingLanguages);
        programingLanguagesFilterList.setCellRenderer(new FilterListCellRenderer());
        programingLanguagesFilterList.setSelectionModel(new DefaultListSelectionModel() {
            @Override
            public void setSelectionInterval(int index0, int index1) {

                if(super.isSelectedIndex(index0)) {

                    super.removeSelectionInterval(index0, index1);
                }else {

                    super.addSelectionInterval(index0, index1);
                }
            }
        });

        DefaultListModel<String> categories = new DefaultListModel<>();

        for(String category : Globals.getAllCategories()){

            categories.addElement(category);
        }
        categoriesFilterList.setModel(categories);
        categoriesFilterList.setCellRenderer(new FilterListCellRenderer());
        categoriesFilterList.setSelectionModel(new DefaultListSelectionModel() {
            @Override
            public void setSelectionInterval(int index0, int index1) {

                if(super.isSelectedIndex(index0)) {

                    super.removeSelectionInterval(index0, index1);
                }else {

                    super.addSelectionInterval(index0, index1);
                }
            }
        });
    }

    private void changeProgramingLanguage(String programingLanguage){

        switch (programingLanguage){

            case "Text":
                this.textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_NONE);
                break;
            case "Java":
                this.textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
                break;
            case "C++":
                this.textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CPLUSPLUS);
                break;
            case "Python":
                this.textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_PYTHON);
                break;
            case "C":
                this.textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_C);
                break;
            case "Scala":
                this.textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_SCALA);
                break;
            case "HTML":
                this.textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_HTML);
                break;
            case "CSS":
                this.textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CSS);
                break;
            case "JSON":
                this.textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JSON);
                break;
            case "JavaScript":
                this.textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVASCRIPT);
                break;
            case "SQL":
                this.textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_SQL);
                break;
            case "C#":
                this.textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CSHARP);
                break;
            default: break;
        }
    }

    private void handleSaveSnippet(){

        if(!this.validateData()){

            return;
        }

        Snippet snippet = new Snippet();

        if(newSnippet){

            snippet.setId(1001 + Globals.snippetHelper.getAllSnippets().size());
            snippet.setName(this.snippetNameTextField.getText());
            snippet.setDateCreated(new Date());
            snippet.setContent(this.textArea.getText());
            snippet.setCategories(this.categoryTextField.getText());
            snippet.setProgramingLanguage(String.valueOf(this.snippetLanguageDropdown.getSelectedItem()));
            Globals.snippetHelper.getAllSnippets().add(snippet);
            Globals.snippetHelper.updateSnippets(Globals.snippetHelper.getAllSnippets());
            this.newSnippet = false;
        } else {

            snippet.setId(this.allSnippets.getSelectedValue().getId());
            snippet.setName(this.snippetNameTextField.getText());
            snippet.setContent(this.textArea.getText());
            snippet.setCategories(this.categoryTextField.getText());
            snippet.setDateCreated(this.allSnippets.getSelectedValue().getDateCreated());
            snippet.setProgramingLanguage(String.valueOf(this.snippetLanguageDropdown.getSelectedItem()));
            Globals.snippetHelper.updateSnippet(snippet);
        }


        Globals.currentSnippetState = SnippetState.SNIPPET_NORMAL;
        checkCurrentSnippetState(Globals.currentSnippetState);

        this.allSnippets.setBackground(Color.WHITE);

        update();
        allSnippets.setSelectedIndex(allSnippetsModel.size()-1);

    }

    private boolean validateData(){

        if(this.snippetNameTextField.getText() == null || this.snippetNameTextField.getText().equals("")){

            JOptionPane.showMessageDialog(null, "Please make sure the name of the snippet is not empty!");
            return false;
        }

        if(this.textArea.getText() == null || this.textArea.getText().equals("")){

            JOptionPane.showMessageDialog(null, "Please make sure the content of the snippet is not empty!");
            return false;
        }

        return true;
    }

    private void handleDeleteButtonClicked(){

        if(allSnippets.getSelectedValue() != null){

            Globals.snippetHelper.deleteSnippet(allSnippets.getSelectedValue());
            update();
        } else {

            JOptionPane.showMessageDialog(null, "Please select a snippet before trying to delete!");
        }
    }

    private void handleCopyButtonClicked(){

        if(allSnippets.getSelectedValue() != null){

            SingleClickCopy copy = new SingleClickCopy();
            copy.copy(allSnippets.getSelectedValue().getContent());
            JOptionPane.showMessageDialog(null, "The " + allSnippets.getSelectedValue().getName() + " snippet content was copied to the CLIPBOARD. Use CTRL+V to paste it in convenient place for you.");
        } else {

            JOptionPane.showMessageDialog(null, "Please select a snippet before trying to delete!");
        }

    }
    private void update(){

        allSnippetsModel = new DefaultListModel<>();
        for(Snippet snippet : Globals.snippetHelper.getSnippetsOrderByNameAscending(Globals.snippetHelper.getAllSnippets())){

            allSnippetsModel.addElement(snippet);
        }

        this.allSnippets.setModel(allSnippetsModel);
        this.orderByDropdown.setSelectedIndex(0);
    }

    private void orderList(){
        ArrayList<Snippet> snippetsFromList = new ArrayList<>();
        DefaultListModel<Snippet> newModel = new DefaultListModel<>();
        //allSnippetsModel = new DefaultListModel<>();

        for(int i=0; i < allSnippets.getModel().getSize(); i++){

            snippetsFromList.add(allSnippets.getModel().getElementAt(i));
        }

        for(Snippet snippet : SnippetState.getCurrentSnippetList(snippetsFromList)){

            newModel.addElement(snippet);
            //System.out.println(snippet.getName());
        }

        this.allSnippets.setModel(newModel);
        //System.out.println("LIST SIZE:::" + allSnippets.getModel().getSize());
    }





    private void search() {

        DefaultListModel<Snippet> newModel = new DefaultListModel<>();

        if (searchTextField.getText() != null && !searchTextField.getText().equals("")){

            for (int i = 0; i < allSnippets.getModel().getSize(); i++) {

                if (allSnippets.getModel().getElementAt(i).getName().contains(searchTextField.getText())) {

                    newModel.addElement(allSnippets.getModel().getElementAt(i));
                }
            }

            this.allSnippets.setModel(newModel);
        }else {

            this.allSnippets.setModel(allSnippetsModel);
        }
    }

    
    private void handleAddButtonClicked(){

        Globals.currentSnippetState = SnippetState.SNIPPET_ADD;
        checkCurrentSnippetState(Globals.currentSnippetState);
    }

    private void checkCurrentSnippetState(String snippetState){

        switch (snippetState){
            case SnippetState.SNIPPET_ADD:
                addSnippetState();
                break;
            case SnippetState.SNIPPET_NORMAL:
                normalState();
                break;
            case SnippetState.SNIPPET_DELETE:
                break;
            case SnippetState.SNIPPET_EDIT:
                break;
            default:
                break;
        }
    }

    public void addSnippetState(){

        this.newSnippet = true;
        this.allSnippets.clearSelection();
        this.allSnippets.setBackground(Color.LIGHT_GRAY);
        this.allSnippets.setEnabled(false);
        this.snippetNameTextField.setText("");
        this.textArea.setText("");
        this.categoryTextField.setText("");
        this.cancelButton.setEnabled(true);

        this.programingLanguagesFilterList.setEnabled(false);
        this.programingLanguagesFilterList.setBackground(Color.LIGHT_GRAY);
        this.categoriesFilterList.setEnabled(false);
        this.categoriesFilterList.setBackground(Color.LIGHT_GRAY);

        this.searchTextField.setEnabled(false);
        this.orderByDropdown.setEnabled(false);


        if(Globals.settingsParser.getSettings().getDefaultLanguage() != null || Globals.settingsParser.getSettings().getDefaultLanguage().equals("")){

            changeProgramingLanguage(Globals.settingsParser.getSettings().getDefaultLanguage());
        }else{

            changeProgramingLanguage("Text");
        }
    }

    public void normalState(){

        cancelButton.setEnabled(false);
        allSnippets.setEnabled(true);

        this.programingLanguagesFilterList.setEnabled(true);
        this.programingLanguagesFilterList.setBackground(Color.WHITE);

        this.categoriesFilterList.setEnabled(true);
        this.categoriesFilterList.setBackground(Color.WHITE);

        this.searchTextField.setEnabled(true);
        this.orderByDropdown.setEnabled(true);

    }

    private void handleCancelButtonClicked(){

        Globals.currentSnippetState = SnippetState.SNIPPET_NORMAL;
        checkCurrentSnippetState(Globals.currentSnippetState);
        this.allSnippets.setBackground(Color.WHITE);
        this.allSnippets.setEnabled(true);
        this.allSnippets.clearSelection();


        this.snippetNameTextField.setText("");
        this.textArea.setText("");
        this.categoryTextField.setText("");

        if(Globals.settingsParser.getSettings().getDefaultLanguage() != null || Globals.settingsParser.getSettings().getDefaultLanguage().equals("")){

            changeProgramingLanguage(Globals.settingsParser.getSettings().getDefaultLanguage());
        }else{

            changeProgramingLanguage("Text");
        }

        Globals.currentSnippetState = SnippetState.SNIPPET_NORMAL;
        checkCurrentSnippetState(Globals.currentSnippetState);

        update();
    }

    private void filterList(){
        Globals.isFilterSelected = true;
        DefaultListModel<Snippet> newModel = new DefaultListModel<>();

        if(!selectedCategories.isEmpty()  || !selectedLanguages.isEmpty()){

            ArrayList<Snippet> matchedSnippets = new ArrayList<>();
            ArrayList<Snippet> snippetsToShow = new ArrayList<>();

            for(int i=0; i < allSnippetsModel.getSize(); i++) {

                if (allSnippetsModel.getElementAt(i).getCategories().length() > 0) {

                    //System.out.println(allSnippetsModel.getElementAt(i).getName());
                    for (String selectedCategory : selectedCategories) {

                        //System.out.println("Category selected: " + selectedCategory);
                        if (allSnippetsModel.getElementAt(i).getCategories().contains(selectedCategory)) {

                            matchedSnippets.add(allSnippetsModel.getElementAt(i));
                            //System.out.println(allSnippetsModel.getElementAt(i).getCategories() + "::::" + selectedCategory + ":::: IN");
                        }
                    }
                }
            }
            if(!selectedLanguages.isEmpty()) {

                if (matchedSnippets.isEmpty()) {

                    for (int k = 0; k < allSnippetsModel.getSize(); k++) {

                        matchedSnippets.add(allSnippetsModel.getElementAt(k));
                    }
                }
                for (Snippet snippet : matchedSnippets) {

                    for (String selectedLanguage : selectedLanguages) {

                        //System.out.println("Category selected: " + selectedLanguage);
                        if (snippet.getProgramingLanguage().equals(selectedLanguage)) {

                            snippetsToShow.add(snippet);
                        }
                    }

                }
                if(!snippetsToShow.isEmpty()) {

                    for (Snippet snippet : snippetsToShow) {
                        newModel.addElement(snippet);
                    }
                }
            }else {

                if(!matchedSnippets.isEmpty()) {

                    for (Snippet snippet : matchedSnippets) {
                        newModel.addElement(snippet);
                    }
                }
            }

            allSnippets.setModel(newModel);
        }else{
            Globals.isFilterSelected = false;
            update();
        }
    }


    private void handleCategoryFilterSelected(){

        if(this.categoriesFilterList.getSelectedValue() != null) {

            selectedCategories = (ArrayList<String>) this.categoriesFilterList.getSelectedValuesList();
        }
        else{

            selectedCategories = new ArrayList<>();
        }

        filterList();
    }

    private void handleProgrammingLanguageFilterSelected(){

        if(this.programingLanguagesFilterList.getSelectedValue() != null) {

            selectedLanguages = (ArrayList<String>) this.programingLanguagesFilterList.getSelectedValuesList();
        }else{

            selectedLanguages = new ArrayList<>();
        }

        filterList();
    }

    private ImageIcon getScaledImageIcons(ImageIcon imageIcon, int width, int height){

        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);  // transform it back_

        return imageIcon;
    }
    private void setupOptionsButton(JButton optionButton, int width, int height){

        optionButton.setBackground(colorTheme.getOptionsButtonBackgroundColor());
        optionButton.setFocusPainted(false);
        optionButton.setBorderPainted(true);
        optionButton.setMinimumSize(new Dimension(width,height));
        optionButton.setPreferredSize(new Dimension(width,height));

        optionButton.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseEntered(java.awt.event.MouseEvent evt) {

                optionButton.setBackground(colorTheme.getOptionsButtonHoverBackgroundColor());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {

                optionButton.setBackground(colorTheme.getOptionsButtonBackgroundColor());
            }
        });
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
