package globals;

import classes.*;
import dataParsing.SettingsParser;

import javax.swing.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Globals {

    public static final String APPLICATION_NAME = "Coniunx";
    public static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    public static String currentState = "";
    public static SettingsParser settingsParser = SettingsParser.getInstance();
    public static JFrame mainFrame = new JFrame(Globals.APPLICATION_NAME);
    public static String currentSnippetState = SnippetState.SNIPPET_NORMAL;
    public static String currentSnippetOrder = SnippetState.SNIPPET_ORDER_NAME_ASCENDING;
    public static boolean isFilterSelected = false;

    public static SnippetHelper snippetHelper;

    static {
        try {
            snippetHelper = new SnippetHelper();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String currentColorTheme;

    static {
        try {
            currentColorTheme = settingsParser.getSettings().getColorTheme();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setFrame(JPanel panel){
        mainFrame.setContentPane(panel);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.setUndecorated(false);
        mainFrame.setSize(1650,1080);
        mainFrame.setVisible(true);
    }

    public static ArrayList<String> getAllCategories() throws IOException {

        ArrayList<String> categories = new ArrayList<>();

        for(int i = 0; i < settingsParser.getSettings().getCategories().split(",").length; i++) {

            categories.add(settingsParser.getSettings().getCategories().split(",")[i].trim());
        }

        return categories;

    }

    public static ArrayList<String> getAllProgramingLanguages(){

        ArrayList<String> langauges = new ArrayList<>();

        langauges.add("Text");
        langauges.add("Java");
        langauges.add("C++");
        langauges.add("Python");
        langauges.add("C");
        langauges.add("Scala");
        langauges.add("HTML");
        langauges.add("CSS");
        langauges.add("JSON");
        langauges.add("JavaScript");
        langauges.add("SQL");
        langauges.add("C#");

        return langauges;
    }

    public static String[] colorThemesNames = {
            "The Pink",
            "The Orange",
            "The Green"
    };
}
