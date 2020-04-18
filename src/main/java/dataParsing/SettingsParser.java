package dataParsing;

import classes.Settings;
import classes.Snippet;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import globals.GlobalsFilesPath;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class SettingsParser {

    private Gson gsonObject;
    private String filePath;
    Settings settings;

    private final static SettingsParser parser = new SettingsParser() ; //early init

    private SettingsParser () {
        gsonObject = new Gson();
        this.filePath = GlobalsFilesPath.folderPath + "settings.json";
        this.settings = new Settings();
    }


    public static SettingsParser getInstance() {
        return parser;
    }

    public Settings getSettings() throws IOException {

        try {
            this.settings = this.gsonObject.fromJson(new FileReader(this.filePath), Settings.class);
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            String jsonString = this.gsonObject.toJson(settings, Settings.class);

            FileWriter fw = new FileWriter(this.filePath);
            fw.write(jsonString);
            fw.close();
        } finally {

            this.settings = this.gsonObject.fromJson(new FileReader(this.filePath), Settings.class);
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
