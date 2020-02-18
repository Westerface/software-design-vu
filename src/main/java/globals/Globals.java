package globals;

import classes.Snippet;
import classes.SnippetHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Globals {

    public static final String APPLICATION_NAME = "Coniunx";
    public static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    public static String currentState = "";
    public static SnippetHelper snippetHelper = new SnippetHelper();
}
