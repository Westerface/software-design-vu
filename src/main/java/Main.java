import classes.ApplicationState;
import globals.Globals;
import views.DashboardForm;

import java.io.IOException;

public class Main {

    //public static String folderPath = "";

    public static void main (String[] args) throws IOException {

        if(args[0].equals("-p") || args[0].equals("--path")){

            Globals.folderPath = setUpFolderPath(args[1]);
            System.out.println("FOLDER: " + Globals.folderPath);
            Globals.currentState = ApplicationState.STATE_DASHBOARD;
            Globals.setFrame(new DashboardForm().mainPanel);

        } else {

            System.out.println(
                    "Please provide the path of the JSON files adding:\n " +
                    "-p \"<path to json folder>\"\n" +
                    "or\n" +
                    "-path \"<path to json folder>\"\n"
            );

            System.exit(0);
        }

    }

    private static String setUpFolderPath(String rawPath){

        if(!rawPath.substring(rawPath.length() - 1).equals("/")){
            rawPath += "/";
        }

        return rawPath;
    }
}