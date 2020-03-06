package views.listCellRenderers;

import classes.Snippet;
import globals.Globals;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;


public class RecentlyAddedCellRenderer extends JPanel implements ListCellRenderer<Snippet> {

    private JLabel snippetName      = new JLabel();
    private JLabel snippetDate      = new JLabel();


    public RecentlyAddedCellRenderer(){

        this.setLayout(new GridLayout(1,2));
        this.setOpaque(true);
        Border padding = BorderFactory.createEmptyBorder(20, 30, 20, 30);
        this.setBorder(padding);
        this.add(snippetName);
        this.add(snippetDate);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Snippet> jList, Snippet snippet, int index, boolean isSelected, boolean cellHasFocus) {

        snippetName.setText(snippet.getName());
        snippetName.setFont(new Font("Andale Mono", Font.BOLD, 14));
        snippetName.setPreferredSize(new Dimension(200,50));

        snippetDate.setText(Globals.formatter.format(snippet.getDateCreated()));
        snippetDate.setFont(new Font("Andale Mono", Font.BOLD, 14));
        snippetDate.setPreferredSize(new Dimension(90,50));

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
