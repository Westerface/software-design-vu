import classes.ApplicationState;
import globals.Globals;
import globals.GlobalsFilesPath;
import views.DashboardForm;

import javax.swing.filechooser.FileSystemView;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    //public static String folderPath = "";

    public static void main (String[] args) throws IOException {

        if(args.length > 1  && (args[0].equals("-p") || args[0].equals("--path"))){

            GlobalsFilesPath.folderPath = setUpFolderPath(args[1]);
            //System.out.println("FOLDER: " + GlobalsFilesPath.folderPath);
            Globals.currentState = ApplicationState.STATE_DASHBOARD;
            Globals.setFrame(new DashboardForm().mainPanel);

        } else {

            GlobalsFilesPath.folderPath = setUpFolderPath(System.getProperty("user.home"));
            Globals.currentState = ApplicationState.STATE_DASHBOARD;
            Globals.setFrame(new DashboardForm().mainPanel);
        }

    }

    private static String setUpFolderPath(String rawPath){

        if(!rawPath.substring(rawPath.length() - 1).equals("/")){
            rawPath += "/";
        }

        return rawPath;
    }
}