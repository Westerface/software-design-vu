package views;

import globals.Globals;
import org.fife.ui.rsyntaxtextarea.*;
import views.listCellRenderers.FilterListCellRenderer;

import javax.swing.*;

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
    public JList allSnippets;
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
    public JList categoriesFilterList;
    public JLabel programingLanguagesFilerLabel;
    public JLabel categoriesFilerLabel;

    private RSyntaxTextArea textArea = new RSyntaxTextArea(20, 60);

    public AllSnippetsForm() {

       createTextArea();
       addProgramingLanguages();
       createFilters();

       snippetLangaugeDropdown.addActionListener(e -> changeProgramingLanguage());
    }

    private void createTextArea(){

        textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
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

    private void changeProgramingLanguage(){

        switch (String.valueOf(this.snippetLangaugeDropdown.getSelectedItem())){
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
}
