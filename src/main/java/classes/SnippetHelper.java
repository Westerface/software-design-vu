package classes;

import dataParsing.SnippetsParser;
import globals.Globals;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class SnippetHelper {

    private SnippetsParser parser;
    private ArrayList<Snippet> allSnipptets;

    public SnippetHelper(){

        this.parser = new SnippetsParser();
        this.allSnipptets = parser.getAllSnippets();
    }

    public ArrayList<Snippet> getAllSnippets(){

        return this.allSnipptets;
    }

    public void updateSnippets(ArrayList<Snippet> updatedSnipptes){

        this.parser.updateSnippets(updatedSnipptes);
    }

    /*
    *
    * Ordering of the snippets
    * Both methods will return otdered list with snippets
    *
    */
    public ArrayList<Snippet> getSnippetsOrderByDate(){

        return this.allSnipptets;
    }

    public ArrayList<Snippet> getSnippetsOrderByName(){

        return this.allSnipptets;
    }

    /*
     *
     * Filtering of the snippets
     * Both methods will return filtered list with snippets
     *
     */
    public ArrayList<Snippet> getSnippetsFilteredByLanguage(String filterLanguage){

        return this.allSnipptets;
    }

    public ArrayList<Snippet> getSnippetsFilteredByCategory(ArrayList<String> categories){

        return this.allSnipptets;
    }


    public String toString(Snippet snippet) {



        return  "Snippet name : "                       + snippet.getName() + "\n" +
                "Snippet programingLanguage : "         + snippet.getProgramingLanguage() + "\n" +
                "Snippet dateCreated : "                + Globals.formatter.format(snippet.getDateCreated()) + "\n" +
                "Snippet content : "                    + "\n" + snippet.getContent() + "\n" +
                "Snippet categories : "                 + snippet.getCategories() + "\n";
    }
}
