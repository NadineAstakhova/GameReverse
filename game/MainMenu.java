package lab5_GameReverse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Nadine on 01.04.2016.
 * Menu at game window
 */
public class MainMenu extends JMenuBar{

    private  JMenuBar menuBar;
    private JMenu itemMainMenu;

    public final int SEPARATOR = 1;
    public final int NOSEPARATOR = 0;

    public MainMenu(){
        menuBar = new JMenuBar();
    }

    public JMenu createJMenu(String name, Font font){
        itemMainMenu = new  JMenu(name);
        itemMainMenu.setFont(font);
        return itemMainMenu;
    }

    public void setItemMainMenu1(String name, JMenu itemMainMenu, Font font, int sep){
        JMenuItem itemMenu = new JMenuItem(name);
        itemMenu.setFont(font);
        itemMainMenu.add(itemMenu);
        if (sep == SEPARATOR)
            itemMainMenu.addSeparator();
    }

    public JMenuItem setItemMainMenu(String name, JMenu itemMainMenu, Font font, int sep){
        JMenuItem itemMenu = new JMenuItem(name);
        itemMenu.setFont(font);
        itemMainMenu.add(itemMenu);
        if (sep == SEPARATOR)
            itemMainMenu.addSeparator();
        return itemMenu;
    }

    public void setJMenu (JMenu itemMainMenu){
        this.menuBar.add(itemMainMenu);
    }


    public JMenuBar getMenuBar(){
        return this.menuBar;
    }

    //action of game saving
    public void eventActionSave(JMenuItem itemMainMenu, Cards cards){
        itemMainMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cards.setSerializeGame();
                System.out.println("save");
            }
        });
    }

    //action of download a saved game
    public void eventActionOpen(JMenuItem itemMainMenu, Cards cards, JPanel gameboard, JLabel label1, JLabel label2){
        itemMainMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cards.getSaveGame(gameboard, label1, label2);
                System.out.println("open");

            }
        });
    }
}
