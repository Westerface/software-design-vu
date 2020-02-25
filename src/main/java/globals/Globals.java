package globals;

import classes.Settings;
import classes.Snippet;
import classes.SnippetHelper;
import views.DashboardForm;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Globals {

    public static final String APPLICATION_NAME = "Coniunx";
    public static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    public static String currentState = "";
    public static SnippetHelper snippetHelper = new SnippetHelper();

    public static JFrame mainFrame = new JFrame(Globals.APPLICATION_NAME);
    public static Settings settings = new Settings();

    public static void setFrame(JPanel panel){
        mainFrame.setContentPane(panel);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.setUndecorated(false);
        mainFrame.setSize(1650,1080);
        mainFrame.setVisible(true);
    }

    public static ArrayList<String> getAllCategories(){

        ArrayList<String> categories = new ArrayList<>();

        for(int i = 0; i < settings.getCategories().split(",").length; i++) {

            categories.add(settings.getCategories().split(",")[i].trim());
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

    public static String[] colorThemesNames = {"Light","Dark","Moonlight"};
}
