package classes;

import java.awt.*;

public class Settings {

    private String defaultLanguage;
    private ColorTheme colorTheme;
    private String categories;

    public Settings(){

        this.defaultLanguage = "";
        this.colorTheme = new ColorTheme();
        this.categories = "";
    }

    public Settings(String defaultLanguage, ColorTheme colorTheme, String categories) {
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

    public ColorTheme getColorTheme() {
        return colorTheme;
    }

    public void setColorTheme(ColorTheme colorTheme) {
        this.colorTheme = colorTheme;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }
}
