package views.listCellRenderers;

import classes.Snippet;
import globals.Globals;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;


public class AllSnippetsCellRenderer extends JPanel implements ListCellRenderer<Snippet> {

    private JLabel snippetName      = new JLabel();



    public AllSnippetsCellRenderer(){

        this.setLayout(new GridLayout(1,2));
        this.setOpaque(true);
        Border padding = BorderFactory.createEmptyBorder(20, 15, 20, 15);
        this.setBorder(padding);
        this.add(snippetName);

    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Snippet> jList, Snippet snippet, int index, boolean isSelected, boolean cellHasFocus) {

        snippetName.setText(snippet.getName());
        snippetName.setFont(new Font("Andale Mono", Font.BOLD, 14));
        snippetName.setPreferredSize(new Dimension(200,25));


        if ( isSelected ) {

            setBackground(jList.getSelectionBackground());
            setForeground(jList.getSelectionForeground());
        } else {

            setBackground(jList.getBackground());
            setForeground(jList.getForeground());
        }

        return this;
    }
}
