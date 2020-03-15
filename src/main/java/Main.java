import classes.State;
import globals.Globals;
import views.DashboardForm;

public class Main {
    public static void main (String[] args) {


        System.out.println(Main.class.getName());
        Globals.currentState = State.STATE_DASHBOARD;
        Globals.setFrame(new DashboardForm().mainPanel);
    }
}