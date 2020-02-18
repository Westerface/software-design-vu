package dataParsing;

import classes.Snippet;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class SnippetsParser {

    private Gson gson;
    private String filePath;
    Type snipperListType;
    ArrayList<Snippet> snippetList;

    public SnippetsParser() {
        this.gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
        this.filePath = "src/main/java/JSONFiles/snippets.json";
        this.snipperListType = new TypeToken<ArrayList<Snippet>>(){}.getType();
        this.snippetList = new ArrayList<>();
    }

    public ArrayList<Snippet> getAllSnippets(){

        try {
            this.snippetList = this.gson.fromJson(new FileReader(this.filePath), this.snipperListType);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return this.snippetList;
    }

    public void updateSnippets(ArrayList<Snippet> updatedSnippets){

        try {

            String jsonString = this.gson.toJson(updatedSnippets, this.snipperListType);

            FileWriter fw = new FileWriter(this.filePath);
            fw.write(jsonString);
            fw.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}
