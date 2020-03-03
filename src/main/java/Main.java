import classes.ColorTheme;
import classes.Settings;
import classes.State;
import com.google.gson.Gson;
import globals.Globals;
import views.DashboardForm;

public class Main {
    public static void main (String[] args) {

        Globals.currentState = State.STATE_DASHBOARD;
        Globals.setFrame(new DashboardForm().mainPanel);
    }
}