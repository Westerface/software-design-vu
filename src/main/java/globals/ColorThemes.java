package globals;

import classes.ColorTheme;
import java.awt.*;

public class ColorThemes {

    public static final String THEME_PINK = "THE_PINK";
    public static final String THEME_ORANGE = "THE_ORANGE";
    public static final String THEME_GREEN = "THE_GREEN";

    public static ColorTheme thePink = new ColorTheme(
        new Color(240, 224, 229),
        new Color(72, 35, 46),
        new Color(227, 211, 216),
        new Color(72, 35, 46),
        new Color(198, 147, 178),
        new Color(58, 30, 39),
        new Color(255, 255, 255),
        new Color(72, 35, 46),
        new Color(98, 27, 57),
        new Color(255, 255, 255),
        new Color(0,0,0),
        new Color(255, 255, 255),
        new Color(0,0,0),
        new Color(198, 147, 178),
        new Color(58, 30, 39),
        new Color(255, 255, 255),
        new Color(72, 35, 46),
        new Color(98, 27, 57),
        new Color(255, 255, 255),
        new Color(0,0,0),
        new Color(72, 35, 46),
        new Color(58, 30, 39),
        new Color(255, 255, 255),
        new Color(98, 27, 57),
        new Color(255, 255, 255)
    );

    public static ColorTheme theOrange = new ColorTheme(
        new Color(44, 76, 80),
        new Color(194, 63, 13),
        new Color(80, 180, 191),
        new Color(194, 63, 13),
        new Color(67, 79, 78),
        new Color(172, 36, 21),
        new Color(255, 255, 255),
        new Color(194, 63, 13),
        new Color(210, 105, 42),
        new Color(255, 255, 255),
        new Color(0,0,0),
        new Color(255, 255, 255),
        new Color(0,0,0),
        new Color(67, 79, 78),
        new Color(172, 36, 21),
        new Color(255, 255, 255),
        new Color(194, 63, 13),
        new Color(210, 105, 42),
        new Color(255, 255, 255),
        new Color(0,0,0),
        new Color(194, 63, 13),
        new Color(172, 36, 21),
        new Color(255, 255, 255),
        new Color(210, 105, 42),
        new Color(255, 255, 255)
    );

    public static ColorTheme theGreen = new ColorTheme(

        new Color(173, 139, 55),
        new Color(124, 106, 71),
        new Color(255, 224, 163),
        new Color(124, 106, 71),
        new Color(221, 195, 80),
        new Color(65, 55, 53),
        new Color(255, 255, 255),
        new Color(124, 106, 71),
        new Color(179, 161, 70),
        new Color(255, 255, 255),
        new Color(0,0,0),
        new Color(255, 255, 255),
        new Color(0,0,0),
        new Color(221, 195, 80),
        new Color(65, 55, 53),
        new Color(255, 255, 255),
        new Color(124, 106, 71),
        new Color(179, 161, 70),
        new Color(255, 255, 255),
        new Color(0,0,0),
        new Color(124, 106, 71),
        new Color(65, 55, 53),
        new Color(255, 255, 255),
        new Color(179, 161, 70),
        new Color(255, 255, 255)
    );


    public static ColorTheme getCurrentSelectedColorTheme() {

        ColorTheme currentTheme = new ColorTheme();
        switch (Globals.currentColorTheme){

            case "THE_PINK":
                currentTheme = thePink;
                break;
            case "THE_ORANGE":
                currentTheme = theOrange;
                break;
            case "THE_GREEN":
                currentTheme = theGreen;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + Globals.currentColorTheme);
        }

        return currentTheme;
    }

}
