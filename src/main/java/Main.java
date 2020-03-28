import classes.ApplicationState;
import globals.Globals;
import views.DashboardForm;

import java.io.IOException;

public class Main {
    public static void main (String[] args) throws IOException {


        System.out.println(Main.class.getName());
        Globals.currentState = ApplicationState.STATE_DASHBOARD;
        Globals.setFrame(new DashboardForm().mainPanel);
    }
}