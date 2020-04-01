package classes;

import java.awt.*;

public class Settings {

    private String defaultLanguage;
    private String colorTheme;
    private String categories;

    public Settings(){

        this.defaultLanguage = "Text";
        this.colorTheme = "THE_GREEN";
        this.categories = "Category 1";
    }

    public Settings(String defaultLanguage, String colorTheme, String categories) {
        this.defaultLanguage = defaultLanguage;
        this.colorTheme = colorTheme;
        this.categories = categories;
    }

    public String getDefaultLanguage() {
        return defaultLanguage;
    }

    public void setDefaultLanguage(String defaultLanguage) {
        this.defaultLanguage = defaultLanguage;
    }

    public String getColorTheme() {
        return colorTheme;
    }

    public void setColorTheme(String colorTheme) {
        this.colorTheme = colorTheme;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }
}
