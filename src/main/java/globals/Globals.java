package globals;

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

    public static void setFrame(JPanel panel){
        mainFrame.setContentPane(panel);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.setUndecorated(false);
        mainFrame.setSize(1650,1080);
        mainFrame.setVisible(true);
    }
}
