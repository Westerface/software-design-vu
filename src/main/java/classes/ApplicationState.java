package classes;

import globals.Globals;
import views.AllSnippetsForm;
import views.DashboardForm;
import views.SettingsForm;

import java.io.IOException;

public class ApplicationState {

    public static final String STATE_DASHBOARD      = "STATE_DASHBOARD";
    public static final String STATE_SETTINGS       = "STATE_SETTINGS";
    public static final String STATE_ADD_SNIPPET    = "STATE_ADD_SNIPPET";

    public void changeState(String newState) throws IOException {

        Globals.currentState = newState;
        switch (newState){
            case STATE_DASHBOARD:
                Globals.mainFrame.getContentPane().removeAll();
                Globals.mainFrame.getContentPane().add(new DashboardForm().mainPanel);
                Globals.mainFrame.getContentPane().revalidate();
                Globals.mainFrame.getContentPane().repaint();
                break;
            case STATE_ADD_SNIPPET:
                //Globals.setFrame(new AddSnippetFrame().mainPanel);
                Globals.mainFrame.getContentPane().removeAll();
                Globals.mainFrame.getContentPane().add(new AllSnippetsForm().mainPanel);
                Globals.mainFrame.getContentPane().revalidate();
                Globals.mainFrame.getContentPane().repaint();
                break;
            case STATE_SETTINGS:
                Globals.mainFrame.getContentPane().removeAll();
                Globals.mainFrame.getContentPane().add(new SettingsForm().mainPanel);
                Globals.mainFrame.getContentPane().revalidate();
                Globals.mainFrame.getContentPane().repaint();
                break;
            default:
                System.out.println("NOT SUCH STATE EXISTS");
        }
    }
}
