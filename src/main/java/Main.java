import classes.State;
import globals.Globals;

public class Main {
    public static void main (String[] args) {

        Globals.currentState = State.STATE_DASHBOARD;
        new State().changeState(Globals.currentState);

    }
}