package classes;

import dataParsing.SnippetsParser;
import globals.Globals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SnippetHelper{

    private SnippetsParser parser;
    private ArrayList<Snippet> allSnippets;

    public SnippetHelper(){

        this.parser = new SnippetsParser();
        this.allSnippets = parser.getAllSnippets();
    }

    public ArrayList<Snippet> getAllSnippets(){

        return this.allSnippets;
    }

    public void updateSnippets(ArrayList<Snippet> updatedSnipptes){

        this.parser.updateSnippets(updatedSnipptes);
    }

    public void deleteSnippet(Snippet snippetToBeRemoved){

        this.allSnippets.remove(snippetToBeRemoved);
        System.out.println("SnippetRemoved");
        this.parser.updateSnippets(this.allSnippets);
    }

    public void editSnippet(Snippet snippetToBeEdited){


    }

    /*
    *
    * Ordering of the snippets
    * Both methods will return otdered list with snippets
    *
    */
    public ArrayList<Snippet> getSnippetsOrderByDateAscending(){

        ArrayList<Snippet> snippetsOrderedByDateAscending = this.allSnippets;
        Collections.sort(snippetsOrderedByDateAscending, new Comparator<Snippet>() {
            public int compare(Snippet o1, Snippet o2) {
                if (o1.getDateCreated() == null || o2.getDateCreated() == null)
                    return 0;
                return o1.getDateCreated().compareTo(o2.getDateCreated());
            }
        });

        return snippetsOrderedByDateAscending;
    }

    public ArrayList<Snippet> getSnippetsOrderByDateDescending(){

        ArrayList<Snippet> snippetsOrderedByDateDescending = getSnippetsOrderByDateAscending();
        Collections.reverse(snippetsOrderedByDateDescending);
        return snippetsOrderedByDateDescending;
    }

    public ArrayList<Snippet> getSnippetsOrderByName(){

        return this.allSnippets;
    }

    /*
     *
     * Filtering of the snippets
     * Both methods will return filtered list with snippets
     *
     */
    public ArrayList<Snippet> getSnippetsFilteredByLanguage(String filterLanguage){

        return this.allSnippets;
    }

    public ArrayList<Snippet> getSnippetsFilteredByCategory(ArrayList<String> categories){

        return this.allSnippets;
    }


    public String toString(Snippet snippet) {

        return  "Snippet name : "                       + snippet.getName() + "\n" +
                "Snippet programingLanguage : "         + snippet.getProgramingLanguage() + "\n" +
                "Snippet dateCreated : "                + Globals.formatter.format(snippet.getDateCreated()) + "\n" +
                "Snippet content : "                    + "\n" + snippet.getContent() + "\n" +
                "Snippet categories : "                 + snippet.getCategories() + "\n";
    }
}
