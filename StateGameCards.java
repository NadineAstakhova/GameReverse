package lab5_GameReverse;

import java.io.*;
import java.util.Objects;
import java.util.Random;

/**
 * Created by Nadine on 01.04.2016.
 * Serializable class which describe array of state cards
 */
public class StateGameCards implements Serializable {

    int[][] masCards;
    int size;

    final int DEFAULT = 5;

    //type of cards
    final int ONETYPE = 0;
    final int SECONDTYPE = 1;

    static int countOneType = 0, countSecondType = 0, countAll = 0;


    public StateGameCards(int num){

        masCards = new int[num][num];
        this.size = num;
        countAll = num * num;
        //getting winner state
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++) {
                masCards[i][j] = ONETYPE;
            }
        }

    }

    public void generateGame(){
        Random rand = new Random();
        countOneType = 0;
        countSecondType = 0;
        //generate initial state for game
        for (int i = 0; i < 40; i++) {
            changeState(rand.nextInt(size),rand.nextInt(size));
        }
        setCounts();

    }

    public int getCard(int raw, int col){
        return this.masCards[raw][col];
    }

    //set counts of cards at game board
    public void setCounts(){
        for (int i = 0; i<size; i++){
            for (int j = 0; j < size; j++){
                if (masCards[i][j] == ONETYPE) {
                     countOneType++;
                }
                else {
                  countSecondType++;
                }
            }
        }
    }

    public  int getCountOneType(){
        return countOneType;
    }

    public  int getCountSecondType(){
        return countSecondType;
    }

    public void show(){
        for(int[] arr : this.masCards) {
            for(int i : arr)
                System.out.printf(" %4d", i);
            System.out.println();
        }
        System.out.println();
    }

    //change state of cards after click
    public void changeState(int raw, int col){
        for (int i = 0; i < this.size; i++){
            for (int j = 0; j < this.size; j++){
                if (i == raw || j == col){
                    if (masCards[i][j] == ONETYPE) {
                        masCards[i][j] = SECONDTYPE;
                    }
                    else {
                        masCards[i][j] = ONETYPE;
                    }
                }
            }
        }

    }

    //check state of winner
    public int checkWin(){
        if (countOneType == countAll)
            return 0;
        if (countSecondType == countAll)
            return 1;
        return -1;
    }

    //save state of game to file
    public  void setSerializ()  {
        try {
            FileOutputStream  fos = new FileOutputStream("C:\\filesave\\data.out");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(masCards);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //get state of game from file
    public void deSerialize(){

        ObjectInputStream  ois;
        try {
            ois = new ObjectInputStream(new FileInputStream("C:\\filesave\\data.out"));
            if (ois != null) {
                masCards = (int[][]) ois.readObject();
                System.out.println(masCards.length);
                ois.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
