package lab5_GameReverse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;

/**
 * Created by Nadine on 01.04.2016.
 * Class describe array of cards in game window
 */

public class Cards {

    final int DEFAULT = 5;
    int sizeMas;

    //array of cards at game window
    JButton[][] masButton;
    //array of cards
    StateGameCards stateGameCards;

    public Cards(JPanel gameboard){
        generateNewGame(5, gameboard);
    }

    //set color for game window card (JButton)
    public void setColor(int raw, int col){
        if (stateGameCards.getCard(raw,col) == stateGameCards.ONETYPE)
            masButton[raw][col].setBackground(Color.WHITE);
        else
            masButton[raw][col].setBackground(Color.BLUE);
    }

    public void setCounts(JLabel label1, JLabel label2){
        label1.setText("" + stateGameCards.getCountOneType());
        label2.setText("" + stateGameCards.getCountSecondType());
    }

    //start new game
    public void generateGameStart(JLabel label, JLabel label2) throws InterruptedException {
        stateGameCards.generateGame();
        for (int i = 0; i < sizeMas; i++) {
            for (int j = 0; j < sizeMas; j++) {
                setColor(i, j);
                eventActionLister(i, j, label, label2);
            }
        }
        setCounts(label, label2);
    }


    public void setColorForRawAndCol(){
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++) {
                if (stateGameCards.getCard(i,j) == stateGameCards.ONETYPE)
                    masButton[i][j].setBackground(Color.WHITE);
                else
                    masButton[i][j].setBackground(Color.BLUE);
            }
    }

    //change color for current button
    public void changeColor(int raw, int col, JLabel label, JLabel label1){
        stateGameCards.changeState(raw, col);
        for (int i = 0; i < sizeMas; i++) {
            for (int j = 0; j < sizeMas; j++) {
                setColor(i, j);
            }
        }
        setCounts(label, label1);
        //check win
        if(stateGameCards.checkWin() == -1)
            System.out.println("No Win One " + stateGameCards.getCountOneType()+" Second "+stateGameCards.getCountSecondType());
    }


    //lister to click on button
    public  void eventActionLister(int i, int j, JLabel label, JLabel label1){
        this.masButton[i][j].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (masButton[i][j].getBackground() == Color.WHITE) {
                    changeColor(i, j, label, label1);
                } else {
                    changeColor(i, j, label, label1);
                }
            }
        });
    }

    public  void setEventActionLister(int size, JLabel label, JLabel label1){
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                this.eventActionLister(i, j, label, label1);
            }
        }
    }

    //when user click on new game, all data and game board take a new initial state
    public void generateNewGame(int num, JPanel gameboard){
        masButton= new JButton[num][num];
        sizeMas = num;
        stateGameCards = new StateGameCards(sizeMas);
        gameboard.setLayout(new GridLayout(num, num));
        for (int i = 0; i < sizeMas; i++) {
            for (int j = 0; j < sizeMas; j++) {
                masButton[i][j] = new JButton();
                setColor(i, j);
                masButton[i][j].setPreferredSize(new Dimension(50, 50));
                gameboard.add(masButton[i][j]);
            }
        }
        stateGameCards.show();
    }

    public void clearGameBoard(JPanel gameboard){
        for (int i = 0; i < sizeMas; i++) {
            for (int j = 0; j < sizeMas; j++) {
                gameboard.remove(masButton[i][j]);
                System.out.println("Clear");
            }
        }
    }


    //when user choose new counts of cards with help of slider
    public  void generateNewGameWithOtherSize(int num, JPanel gameboard){
        clearGameBoard(gameboard);
        generateNewGame(num, gameboard);
    }

    public  void setSerializeGame(){
        stateGameCards.setSerializ();
    }

    //getting saved game
    public  void getSaveGame(JPanel gameboard, JLabel label1, JLabel label2){
        stateGameCards.deSerialize();
        System.out.println(stateGameCards.masCards.length);
        stateGameCards.show();
        clearGameBoard(gameboard);
        sizeMas = stateGameCards.masCards.length;
        masButton= new JButton[sizeMas][sizeMas];
        label1.setText(" "+ stateGameCards.getCountOneType());
        label2.setText(" "+ stateGameCards.getCountSecondType());

        gameboard.setLayout(new GridLayout(sizeMas, sizeMas));

        for (int i = 0; i <sizeMas; i++) {
            for (int j = 0; j < sizeMas; j++) {
                masButton[i][j] = new JButton();
                setColor(i, j);
                masButton[i][j].setPreferredSize(new Dimension(50, 50));
                gameboard.add(masButton[i][j]);
                eventActionLister(i, j, label1, label2);
            }
        }

    }
}
