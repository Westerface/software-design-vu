package views;

import classes.*;
import globals.ColorThemes;
import globals.Globals;
import globals.GlobalsViews;
import org.fife.ui.rsyntaxtextarea.*;
import views.listCellRenderers.AllSnippetsCellRenderer;
import views.listCellRenderers.FilterListCellRenderer;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
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
    private GlobalsViews globalsViews;
    ColorTheme colorTheme = ColorThemes.getCurrentSelectedColorTheme();

    public AllSnippetsForm() throws IOException {

        globalsViews = new GlobalsViews();
        globalsViews.setupNavigationButtons(allSnippetsButton, addSnippetButton,  settingsButton, dashboardButton,colorTheme);
        headerPanel.setBackground(colorTheme.getHeaderBackgroundColor());
        applicationName.setForeground(colorTheme.getHeaderTextColor());
        applicationName.setText(Globals.APPLICATION_NAME);
        createTextArea();
        addProgramingLanguages();
        createFilters();
        createAllSnippetsList();
        setupListeners();
        setupOrderByDropdown();
        setupAddCopyDeleteButtons();

        if(!newSnippet){

            allSnippets.setSelectedIndex(0);
        }
    }

    private void setupListeners() throws IOException {

        this.snippetLanguageDropdown.addActionListener(e -> changeProgramingLanguage(String.valueOf(this.snippetLanguageDropdown.getSelectedItem())));
        this.allSnippets.addListSelectionListener(e -> handleSelectSnippet());
        this.categoriesFilterList.addListSelectionListener(e -> handleCategoryFilterSelected());
        this.programingLanguagesFilterList.addListSelectionListener(e -> handleProgrammingLanguageFilterSelected());
        this.cancelButton.setEnabled(false);
        this.orderByDropdown.addActionListener(e -> handleOrderBySelected(this.orderByDropdown.getSelectedIndex()));
        this.deleteButton.addActionListener(e -> handleDeleteButtonClicked());
        this.copyButton.addActionListener(e -> handleCopyButtonClicked());

        this.addButton.addActionListener(e -> {
            try {
                handleAddButtonClicked();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        this.saveSnippetButton.addActionListener(e -> {
            try {
                handleSaveSnippet();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        this.cancelButton.addActionListener(e -> {
            try {
                handleCancelButtonClicked();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        checkCurrentSnippetState(Globals.currentSnippetState);

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

    private void setupAddCopyDeleteButtons() throws IOException {

        this.addButton.setIcon(globalsViews.getScaledImageIcons(globalsViews.getAddIcon(),30, 30));
        //setupActionButton(this.addButton, 50, 50);
        globalsViews.setupButtonVisual(this.addButton, 50, 50, colorTheme, false);
        this.addButton.setToolTipText("Add Snippet");

        this.copyButton.setIcon(globalsViews.getScaledImageIcons(globalsViews.getCopyIcon(),30, 30));
        //setupActionButton(this.addButton, 50, 50);
        globalsViews.setupButtonVisual(this.copyButton, 50, 50, colorTheme, false);
        this.copyButton.setToolTipText("Copy Snippet");

        this.deleteButton.setIcon(globalsViews.getScaledImageIcons(globalsViews.getDeleteIcon(),30, 30));
        //setupActionButton(this.addButton, 50, 50);
        globalsViews.setupButtonVisual(this.deleteButton, 50, 50, colorTheme, false);
        this.deleteButton.setToolTipText("Delete Snippet");

    }

    private void handleOrderBySelected(int selectedOrderType){
        //System.out.println(selectedOrderType);
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

    private void createTextArea() throws IOException {

        if(Globals.settingsParser.getSettings().getDefaultLanguage() != null && Globals.settingsParser.getSettings().getDefaultLanguage().length() > 0){

            changeProgramingLanguage(Globals.settingsParser.getSettings().getDefaultLanguage());
            addProgramingLanguages();
            snippetLanguageDropdown.setSelectedItem(Globals.settingsParser.getSettings().getDefaultLanguage());
        } else {

            changeProgramingLanguage(SyntaxConstants.SYNTAX_STYLE_LATEX);
        }
        textArea.setCodeFoldingEnabled(true);
        textArea.setAutoIndentEnabled(true);

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

    public void createFilters() throws IOException {

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

    private void handleSaveSnippet() throws IOException {

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

        if(Globals.currentSnippetState.equals(SnippetState.SNIPPET_ADD) && Globals.snippetHelper.checkNameExists(this.snippetNameTextField.getText())) {

            JOptionPane.showMessageDialog(null, "Please make sure the name of the new snippet does not exist already!");
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

    
    private void handleAddButtonClicked() throws IOException {

        Globals.currentSnippetState = SnippetState.SNIPPET_ADD;
        checkCurrentSnippetState(Globals.currentSnippetState);
    }

    private void checkCurrentSnippetState(String snippetState) throws IOException {

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

    public void addSnippetState() throws IOException {

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


        if(Globals.settingsParser.getSettings().getDefaultLanguage() != null || !Globals.settingsParser.getSettings().getDefaultLanguage().equals("")){

            this.snippetLanguageDropdown.setSelectedItem(Globals.settingsParser.getSettings().getDefaultLanguage());
            this.changeProgramingLanguage(Globals.settingsParser.getSettings().getDefaultLanguage());
        }else{
            this.snippetLanguageDropdown.setSelectedItem("Text");
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
        Globals.currentSnippetState = SnippetState.SNIPPET_NORMAL;
        newSnippet = false;
    }

    private void handleCancelButtonClicked() throws IOException {


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

        checkCurrentSnippetState(Globals.currentSnippetState);
        Globals.currentSnippetState = SnippetState.SNIPPET_NORMAL;
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

}
