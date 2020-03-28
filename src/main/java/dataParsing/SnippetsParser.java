package dataParsing;

import classes.Snippet;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class SnippetsParser {

    private Gson gsonObject;
    Type snippetListType;
    ArrayList<Snippet> snippetList;

    private ClassLoader classloader;
    private InputStream snippetsIS;

    public SnippetsParser() {

        classloader = Thread.currentThread().getContextClassLoader();
        snippetsIS = classloader.getResourceAsStream("json_files/snippets.json");

        this.gsonObject = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
        this.snippetListType = new TypeToken<ArrayList<Snippet>>(){}.getType();
        this.snippetList = new ArrayList<>();
    }

    public ArrayList<Snippet> getAllSnippets(){

        this.snippetList = this.gsonObject.fromJson(new InputStreamReader(snippetsIS), this.snippetListType);

        return this.snippetList;
    }

    public void updateSnippets(ArrayList<Snippet> updatedSnippets){

        try {

            String jsonString = this.gsonObject.toJson(updatedSnippets, this.snippetListType);

            FileWriter fw = new FileWriter(classloader.getResource("json_files/snippets.json").getFile());
            fw.write(jsonString);
            fw.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}
