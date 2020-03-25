package classes;

import globals.Globals;

import java.util.ArrayList;

public class SnippetState {

    public static final String SNIPPET_ADD                      = "SNIPPET_ADD";
    public static final String SNIPPET_EDIT                     = "SNIPPET_EDIT";
    public static final String SNIPPET_DELETE                   = "SNIPPET_DELETE";
    public static final String SNIPPET_NORMAL                   = "SNIPPET_NORMAL";

    public static final String SNIPPET_ORDER_NAME_ASCENDING     = "SNIPPET_ORDER_NAME_ASCENDING";
    public static final String SNIPPET_ORDER_NAME_DESCENDING    = "SNIPPET_ORDER_NAME_DESCENDING";
    public static final String SNIPPET_ORDER_DATE_ASCENDING     = "SNIPPET_ORDER_DATE_ASCENDING";
    public static final String SNIPPET_ORDER_DATE_DESCENDING    = "SNIPPET_ORDER_DATE_DESCENDING";
    public static final String SNIPPET_ORDER_DATE_MODIFIED      = "SNIPPET_ORDER_DATE_MODIFIED";

    public static ArrayList<Snippet> getCurrentSnippetList(ArrayList<Snippet> snippetsFromList){
        ArrayList<Snippet> snippets = new ArrayList<>();
        System.out.println("getSnippets");
//        if(!Globals.isFilterSelected){
//            snippets = Globals.snippetHelper.getAllSnippets();
//            return snippets;
//        } else {
            switch (Globals.currentSnippetOrder){
                case SNIPPET_ORDER_NAME_ASCENDING:
                    snippets = Globals.snippetHelper.getSnippetsOrderByNameAscending(snippetsFromList);
                    System.out.println("NameA");
                    return snippets;
                 case SNIPPET_ORDER_NAME_DESCENDING:
                    snippets = Globals.snippetHelper.getSnippetsOrderByNameDescending(snippetsFromList);
                     System.out.println("NameD");
                    return snippets;
                case SNIPPET_ORDER_DATE_ASCENDING:
                    snippets = Globals.snippetHelper.getSnippetsOrderByDateAscending(snippetsFromList);
                    System.out.println("DateA");
                    return snippets;
                case SNIPPET_ORDER_DATE_DESCENDING:
                    snippets = Globals.snippetHelper.getSnippetsOrderByDateDescending(snippetsFromList);
                    System.out.println("DateD");
                    return snippets;
                case SNIPPET_ORDER_DATE_MODIFIED:
                    snippets = Globals.snippetHelper.getSnippetsOrderByDateDescending(snippetsFromList);
                    return snippets;
                default:
                    snippets = Globals.snippetHelper.getAllSnippets();
                    System.out.println("DEF");
                    return snippets;
            }
//        }
    }
}
