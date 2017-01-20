package lab5_GameReverse;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * Created by Nadine on 31.03.2016.
 * All elements at game window
 * Swing
 */
public class GameWindow extends JFrame{

    private JPanel panel1;
    private JToolBar ToolBar;
    static int previous_size;
    static int size;
    Cards gameCards;

    public GameWindow(){
        super("Reverse");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //set font
        Font font = new Font("Verdana", Font.PLAIN, 11);
        //set menu for window
        MainMenu menuBar = new MainMenu();
        JMenu itemMenu =  menuBar.createJMenu("File", font);

        JMenuItem menuItemNew =  menuBar.setItemMainMenu("New", itemMenu, font, menuBar.NOSEPARATOR);
        JMenuItem menuItemOpen = menuBar.setItemMainMenu("Open", itemMenu, font, menuBar.NOSEPARATOR);
        JMenuItem menuItemSave = menuBar.setItemMainMenu("Save", itemMenu, font, menuBar.NOSEPARATOR);

        JMenuItem menuItemSaveAs =  menuBar.setItemMainMenu("Save as", itemMenu, font, menuBar.SEPARATOR);
        JMenuItem menuItemExit = menuBar.setItemMainMenu("Exit", itemMenu, font, menuBar.NOSEPARATOR);

        menuBar.setJMenu(itemMenu);

        JMenu itemMenuHelp =  menuBar.createJMenu("Help", font);
        JMenuItem menuItemAbout =  menuBar.setItemMainMenu("About", itemMenuHelp, font, menuBar.NOSEPARATOR);
        menuBar.setJMenu(itemMenuHelp);

        this.setJMenuBar(menuBar.getMenuBar());

        //set toolbar
        JToolBar toolBar = new JToolBar("Tool Bar", JToolBar.VERTICAL);
        ImageIcon icon =  new ImageIcon("src/lab5_GameReverse/start.png");

        //button of start game
        JButton startButton = new JButton( icon);
        startButton.setPreferredSize(new Dimension(40, 20));
        toolBar.add(startButton);

        JLabel labelOne = new JLabel("White: ");
        labelOne.setFont(font);
        toolBar.add(labelOne);

        JLabel labelCountOne = new JLabel("0");
        labelCountOne.setFont(font);
        toolBar.add(labelCountOne);

        JLabel labelSecond = new JLabel("Blue: ");
        labelSecond.setFont(font);
        toolBar.add(labelSecond);

        JLabel labelCountTwo = new JLabel("0");
        labelCountTwo.setFont(font);
        toolBar.add(labelCountTwo);

        //button of new game
        JButton newButton = new JButton( "New");
        newButton.setPreferredSize(new Dimension(40, 20));
        toolBar.add(newButton);

        panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.LINE_AXIS));
        panel1.add(toolBar);

        //set slider for size of game board
        JSlider slider = new JSlider(JSlider.VERTICAL, 3, 15, 5);
        slider.setMinorTickSpacing(1);
        slider.setMajorTickSpacing(2);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        size = slider.getValue();
        panel1.add(slider);

        //set game panel
        JPanel game = new JPanel();
        game.setPreferredSize(new Dimension(400, 400));
        game.setBackground(Color.white);

        //set listener for slider when user change it state
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                System.out.println("Slider2: " + slider.getValue());
                size = slider.getValue();
            }
        });

        //set cards
        gameCards = new Cards(game);

        //set action to click on start button
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    //generate game
                    gameCards.generateGameStart(labelCountOne, labelCountTwo);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        });

        //set action to click on new button
        newButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //generate new game and game boatd
                gameCards.generateNewGameWithOtherSize(size, game);
            }
        });

        panel1.add(Box.createHorizontalStrut(20));
        panel1.add(Box.createVerticalStrut(20));
        panel1.add(game);
        panel1.add(Box.createVerticalStrut(10));
        panel1.add(Box.createHorizontalStrut(10));

        this.setContentPane(panel1);

        this.setPreferredSize(new Dimension(600, 500));
        pack();

        menuBar.eventActionSave(menuItemSave, gameCards);
        menuBar.eventActionOpen(menuItemOpen, gameCards, game, labelCountOne, labelCountTwo);

        setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
