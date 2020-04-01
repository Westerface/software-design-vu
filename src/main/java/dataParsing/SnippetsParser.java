package dataParsing;

import classes.Snippet;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import globals.GlobalsFilesPath;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class SnippetsParser {

    private Gson gsonObject;
    private String filePath;
    Type snippetListType;
    ArrayList<Snippet> snippetList;


    private final static SnippetsParser parser = new SnippetsParser() ; //early init

    private SnippetsParser () {
        this.gsonObject = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
        this.filePath = GlobalsFilesPath.folderPath + "snippets.json";
        this.snippetListType = new TypeToken<ArrayList<Snippet>>(){}.getType();
        this.snippetList = new ArrayList<>();
    }


    public static SnippetsParser getInstance() {
        return parser;
    }

    public ArrayList<Snippet> getAllSnippets() throws IOException {

        try {
            this.snippetList = this.gsonObject.fromJson(new FileReader(this.filePath), this.snippetListType);
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            Snippet defaultSnippet = new Snippet();
            this.snippetList.add(defaultSnippet);

            updateSnippets(this.snippetList);

        } finally {

            this.snippetList = this.gsonObject.fromJson(new FileReader(this.filePath), this.snippetListType);
        }

        return this.snippetList;
    }

    public void updateSnippets(ArrayList<Snippet> updatedSnippets){

        try {

            String jsonString = this.gsonObject.toJson(updatedSnippets, this.snippetListType);

            FileWriter fw = new FileWriter(this.filePath);
            fw.write(jsonString);
            fw.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}
