package dataParsing;

import classes.Settings;
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

public class SettingsParser {

    private Gson gsonObject;
    private String filePath;
    Settings settings;

    public SettingsParser() {
        gsonObject = new Gson();
        this.filePath = "src/main/java/JSONFiles/settings.json";
        this.settings = new Settings();
    }

    public Settings getSettings(){

        try {
            this.settings = this.gsonObject.fromJson(new FileReader(this.filePath), Settings.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return this.settings;
    }

    public void setSettings(Settings settings){

        try {

            String jsonString = this.gsonObject.toJson(settings, Settings.class);

            FileWriter fw = new FileWriter(this.filePath);
            fw.write(jsonString);
            fw.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}
