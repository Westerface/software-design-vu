package views;

import classes.Snippet;
import globals.Globals;
import org.fife.ui.rsyntaxtextarea.*;
import views.listCellRenderers.AllSnippetsCellRenderer;
import views.listCellRenderers.FilterListCellRenderer;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.io.IOException;
import java.io.InputStream;
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
    public JButton dshboardButton;
    public JButton addSnippetButton;
    public JButton allSnippetsButton;
    public JButton settingsButton;
    public JPanel searchPanel;
    public JPanel orderByWrapper;
    public JComboBox orderByDropdown;
    public JLabel orderByLabel;
    public JLabel searchLabel;
    public JTextField searchTextField;
    public JList<Snippet> allSnippets;
    public JPanel snippetOptionsWrapper;
    public JPanel snippetOptions;
    public JPanel snippetWrapper;
    public JTextField snippetNameTextField;
    public JComboBox<String> snippetLangaugeDropdown;
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
    public JScrollPane programinLanguageFilterScrollPane;
    public JScrollPane categoriesFilterScrollPane;

    private RSyntaxTextArea textArea = new RSyntaxTextArea(20, 60);
    private DefaultListModel<Snippet> allSnippetsModel = new DefaultListModel<>();

    public AllSnippetsForm() {

       createTextArea();
       addProgramingLanguages();
       createFilters();
       createAllSnippetsList();

       snippetLangaugeDropdown.addActionListener(e -> changeProgramingLanguage(String.valueOf(this.snippetLangaugeDropdown.getSelectedItem())));
       saveSnippetButton.addActionListener(e -> handleSaveSnippet());
       allSnippets.addListSelectionListener(e -> handleSelectSnippet());

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

    private void createTextArea(){

        if(Globals.settingsParser.getSettings().getDefaultLanguage() != null && Globals.settingsParser.getSettings().getDefaultLanguage().length() > 0){

            changeProgramingLanguage(Globals.settingsParser.getSettings().getDefaultLanguage());
            addProgramingLanguages();
            snippetLangaugeDropdown.setSelectedItem(Globals.settingsParser.getSettings().getDefaultLanguage());
        } else {
            changeProgramingLanguage(SyntaxConstants.SYNTAX_STYLE_LATEX);
        }
        textArea.setCodeFoldingEnabled(true);
        textArea.setAutoIndentEnabled(true);

        InputStream in = getClass().getResourceAsStream("src/main/assets/textAreaThemes/dark.xml");
        try {
            Theme theme = Theme.load(in);
            theme.apply(textArea);
        } catch (IOException ioe) { // Never happens
            ioe.printStackTrace();
        }

        snippetContent.add(textArea);

    }

    private void handleSelectSnippet(){

        Snippet snippetToDisplay = allSnippets.getSelectedValue();
        snippetNameTextField.setText(snippetToDisplay.getName());
        categoryTextField.setText(snippetToDisplay.getCategories());
        textArea.setText(snippetToDisplay.getContent());
        snippetLangaugeDropdown.setSelectedItem(snippetToDisplay.getProgramingLanguage());
        changeProgramingLanguage(snippetToDisplay.getProgramingLanguage());
    }

    private void createAllSnippetsList(){

        for(Snippet snippet : Globals.snippetHelper.getSnippetsOrderByDateDescending()){
            allSnippetsModel.addElement(snippet);
        }

        //Setting list view and selection mode
        allSnippets.setCellRenderer(new AllSnippetsCellRenderer());
        allSnippets.setModel(allSnippetsModel);
        allSnippets.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void addProgramingLanguages(){

        for(String language : Globals.getAllProgramingLanguages()) {
            this.snippetLangaugeDropdown.addItem(language);
        }
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
                }
                else {
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
                }
                else {
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
        this.validateData();
        boolean newSnippet = true;
        for(Snippet snippet : Globals.snippetHelper.getAllSnippets()){

            //if(snippet.getName().equals(this.snippetNameTextField.getText())){
            if(snippet.getId() == this.allSnippets.getSelectedValue().getId()){

                snippet.setContent(this.textArea.getText());
                snippet.setCategories(this.categoryTextField.getText());
                snippet.setProgramingLanguage(String.valueOf(this.snippetLangaugeDropdown.getSelectedItem()));
                Globals.snippetHelper.updateSnippets(Globals.snippetHelper.getAllSnippets());
                newSnippet = false;
                break;
            }
        }

        if(newSnippet){
            Snippet snippet = new Snippet();
            snippet.setName(this.snippetNameTextField.getText());
            snippet.setDateCreated(new Date());
            snippet.setContent(this.textArea.getText());
            snippet.setCategories(this.categoryTextField.getText());
            snippet.setProgramingLanguage(String.valueOf(this.snippetLangaugeDropdown.getSelectedItem()));
            Globals.snippetHelper.getAllSnippets().add(snippet);
            Globals.snippetHelper.updateSnippets(Globals.snippetHelper.getAllSnippets());
            update();
        }
    }

    private void validateData(){

    }

    private void update(){

        allSnippetsModel = new DefaultListModel<>();

        for(Snippet snippet : Globals.snippetHelper.getAllSnippets()){
            allSnippetsModel.addElement(snippet);
        }

        this.allSnippets.setModel(allSnippetsModel);
    }

    private void search(){

        allSnippetsModel = new DefaultListModel<>();

        for(Snippet snippet : Globals.snippetHelper.getAllSnippets()){
            if(snippet.getName().contains(searchTextField.getText())) {

                allSnippetsModel.addElement(snippet);
            }
        }

        this.allSnippets.setModel(allSnippetsModel);
    }
}
