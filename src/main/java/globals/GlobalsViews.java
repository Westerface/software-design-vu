package globals;

import classes.ColorTheme;

import javax.swing.*;
import java.awt.*;

public class GlobalsViews {

    public static ImageIcon getScaledImageIcons(ImageIcon imageIcon, int width, int height){

        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);  // transform it back_

        return imageIcon;
    }

    public static void setupOptionsButton(JButton optionButton, int width, int height, ColorTheme colorTheme){

        optionButton.setBackground(colorTheme.getOptionsButtonBackgroundColor());
        optionButton.setFocusPainted(false);
        optionButton.setBorderPainted(true);
        optionButton.setMinimumSize(new Dimension(width,height));
        optionButton.setPreferredSize(new Dimension(width,height));

        optionButton.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseEntered(java.awt.event.MouseEvent evt) {

                optionButton.setBackground(colorTheme.getOptionsButtonHoverBackgroundColor());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {

                optionButton.setBackground(colorTheme.getOptionsButtonBackgroundColor());
            }
        });
    }
}
