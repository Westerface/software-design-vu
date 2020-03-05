import classes.State;
import globals.Globals;
import views.DashboardForm;

public class Main {
    public static void main (String[] args) {

        Globals.currentState = State.STATE_DASHBOARD;
        Globals.setFrame(new DashboardForm().mainPanel);
    }
}