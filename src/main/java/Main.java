import classes.Snippet;
import classes.SnippetHelper;
import globals.Globals;
import views.MainForm;
import javax.swing.*;
import java.util.ArrayList;

public class Main {
    public static void main (String[] args) {

        SnippetHelper snippetHelper = new SnippetHelper();
        ArrayList<Snippet> allSnippets = snippetHelper.getAllSnippets();

        for(Snippet snippet : allSnippets){
            System.out.println(snippetHelper.toString(snippet));
        }


        JFrame mainFrame = new JFrame(Globals.APPLICATION_NAME);
        mainFrame.setContentPane(new MainForm().mainPannel);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.setUndecorated(false);
        mainFrame.setSize(1650,1080);
        mainFrame.setVisible(true);
//        snippetHelper.updateSnippets(allSnippets);
//        allSnippets = snippetHelper.getAllSnippets();

    }
}