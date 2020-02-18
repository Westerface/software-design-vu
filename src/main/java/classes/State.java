package classes;

import globals.Globals;

public class State {

    static final String stateDashboard      = "STATE_DASHBOARD";
    static final String stateSettings       = "STATE_SETTINGS";
    static final String stateAddSnippet     = "STATE_ADD_SNIPPET";

    public void changeState(String newState){

        Globals.currentState = newState;
    }
}
