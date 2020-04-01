package classes;

import dataParsing.SnippetsParser;
import globals.Globals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SnippetHelper{

    private SnippetsParser parser;
    private ArrayList<Snippet> allSnippets;

    public SnippetHelper() throws IOException {

        this.parser = SnippetsParser.getInstance();
        this.allSnippets = parser.getAllSnippets();
    }

    public ArrayList<Snippet> getAllSnippets(){

        return this.allSnippets;
    }

    public void updateSnippets(ArrayList<Snippet> updatedSnippets){

        this.parser.updateSnippets(updatedSnippets);
    }

    public void deleteSnippet(Snippet snippetToBeRemoved){

        this.allSnippets.remove(snippetToBeRemoved);
        System.out.println("SnippetRemoved");
        this.parser.updateSnippets(this.allSnippets);
    }

    public void updateSnippet(Snippet snippetToBeEdited){

        for(Snippet snippet : this.allSnippets){

            //if(snippet.getName().equals(this.snippetNameTextField.getText())){
            if(snippetToBeEdited.getId() == snippet.getId()){

                allSnippets.remove(snippet);
                this.allSnippets.add(snippetToBeEdited);
                break;
            }
        }

        this.updateSnippets(this.allSnippets);
    }

    /*
    *
    * Ordering of the snippets
    * Both methods will return otdered list with snippets
    *
    */
    public ArrayList<Snippet> getSnippetsOrderByDateAscending(ArrayList<Snippet> listToSort){

        listToSort.sort((snippet1, snippet2) -> {

            if (snippet1.getDateCreated() == null || snippet2.getDateCreated() == null){

                return 0;
            }
            return snippet1.getDateCreated().compareTo(snippet2.getDateCreated());
        });

        return listToSort;
    }

    public ArrayList<Snippet> getSnippetsOrderByDateDescending(ArrayList<Snippet> listToSort){

        listToSort = getSnippetsOrderByDateAscending(listToSort);
        Collections.reverse(listToSort);

        return listToSort;
    }

    public ArrayList<Snippet> getSnippetsOrderByNameAscending(ArrayList<Snippet> listToSort){

        listToSort.sort((snippet1, snippet2) -> {

            if (snippet1.getName() == null || snippet2.getName() == null){

                return 0;
            }
            return snippet1.getName().compareTo(snippet2.getName());
        });

        return listToSort;
    }

    public ArrayList<Snippet> getSnippetsOrderByNameDescending(ArrayList<Snippet> listToSort){

        listToSort = getSnippetsOrderByNameAscending(listToSort);
        Collections.reverse(listToSort);

        return listToSort;
    }

    public boolean checkNameExists(String newSnippetName){

        for(Snippet snippet : allSnippets){

            if(snippet.getName().equals(newSnippetName)){
                return true;
            }
        }

        return false;
    }


    public String toString(Snippet snippet) {

        return  "Snippet name : "                       + snippet.getName() + "\n" +
                "Snippet programingLanguage : "         + snippet.getProgramingLanguage() + "\n" +
                "Snippet dateCreated : "                + Globals.formatter.format(snippet.getDateCreated()) + "\n" +
                "Snippet content : "                    + "\n" + snippet.getContent() + "\n" +
                "Snippet categories : "                 + snippet.getCategories() + "\n";
    }
}
