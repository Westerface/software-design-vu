import classes.Settings;
import classes.State;
import globals.Globals;
import views.DashboardForm;

public class Main {
    public static void main (String[] args) {

        // TODO: PARSE SETTINGS
        Settings settings = new Settings();
        settings.setCategories("functions, formulas, custom, school, work, tutorial");
        Globals.settings = settings;


        Globals.currentState = State.STATE_DASHBOARD;
        Globals.setFrame(new DashboardForm().mainPanel);
    }
}