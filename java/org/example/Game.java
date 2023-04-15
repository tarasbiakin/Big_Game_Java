package org.example;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private int rows;
    private  int columns;
    private int amountOfEnemis;
    private int transistorsNeeded;
    private int turnsLeft;
    private int transistorsGethered;
    private Field field;
    private boolean isGameFinish = false;
    private int amountOfFlowers;
    private ArrayList<Flower> flowerArrayList = new ArrayList<Flower>();
    private ArrayList<Enemy>enemyArrayList = new ArrayList<Enemy>();
    private Random randomNumber = new Random();
    private Player player;
    private Scanner scanner = new Scanner(System.in);
    private boolean isIncorrectCommand = true;
    private int triesToRegenerate = 10;





    public Game(int rows, int columns, int amountOfEnemis, int transistorsNeeded, int turnsLeft, int amountOfFlowers) {
        this.rows = rows;
        this.columns = columns;
        this.amountOfEnemis = amountOfEnemis;
        this.transistorsNeeded = transistorsNeeded;
        this.turnsLeft = turnsLeft;
        this.amountOfFlowers=amountOfFlowers;
//        this.flowersGethered = flowersGethered;
        field = new Field(rows, columns);
    }

    public Field getField() {
        return this.field;
    }

    public ArrayList<Flower> getFlowerArrayList() {
        return this.flowerArrayList;
    }

    public void setTransistorsGethered(int transistorsToAdd) {
        this.transistorsGethered += transistorsToAdd;
    }

    public void fillFieldWithEmtyObj(){
        for (int i = 0; i< rows; i++){
            for (int j = 0; j< columns; j++){
                field.setFieldable(i,j,new Empty());
            }
        }
    }

    public void startGame(){

        possesPlayer();
        possesEnemies();
        possesFlowers();

        while (!isGameFinish){
            showField();
            playerTurn();
            if (isIncorrectCommand)
            {
                isIncorrectCommand();
                continue;
            }
            computerTurn();
            checkIfGameNotFinished();
        }
    }

    private void isIncorrectCommand() {
        System.out.println("You have enter incorrect command, please verifi and try again");
    }

    private void checkIfGameNotFinished() {
        if (turnsLeft == 0) {

            System.out.println("No more turns left, you lost!");
            isGameFinish = true;

        }
        else if (transistorsGethered >= transistorsNeeded) {

            System.out.println("You have gathered the required " +
                    "number of transistors, you won!!!");
            isGameFinish = true;

        }
    }

    private void possesPlayer() {
        int playerRowPos = randomNumber.nextInt(rows);
        int playerColumnPos = randomNumber.nextInt(columns);
        player = new Player(playerRowPos,playerColumnPos,this);

    }
    private void possesEnemies() {
        genereteEnemies();

    }

    private void genereteEnemies() {

        for (int i = amountOfEnemis - enemyArrayList.size();i>0;){

            int enemyRowPos = randomNumber.nextInt(rows);
            int enemyColumnPos = randomNumber.nextInt(columns);

            if (field.getFieldable(enemyRowPos,enemyColumnPos) instanceof Empty){
                Enemy enemy = new Enemy(enemyRowPos,enemyColumnPos);
                field.setFieldable(enemyRowPos,enemyColumnPos,enemy);
                enemyArrayList.add(enemy);
                i--;
            }

        }

    }

    private void possesFlowers() {
        genereteFlowers();
    }
    private void showField(){
        System.out.println("\n\n Turns left: "+ turnsLeft +
                ", transistors gathered:" + transistorsGethered +
                "/"+transistorsNeeded);
        field.showFild();
    }

    private void playerTurn() {
        System.out.println("Please enter your command");
        String command = scanner.nextLine();
        isIncorrectCommand= player.makeMove(command);
    }
    private void computerTurn() {
        enemyMove();
        genereteFlowers();
        turnsLeft--;
    }

    private void enemyMove() {
        int rowIndex = 0;
        int columnIndex = 0;
        int newRowIndex = 0;
        int newColumnIndex = 0;
        int regenerateIndex = 0;
        boolean isNeededToRegenerate = true;

        for (Enemy enemy:enemyArrayList){
            rowIndex = enemy.getRowIndex();
            columnIndex = enemy.getColumnIndex();

        do {
            int deltaRow = randomNumber.nextInt(3)-1;
            int deltaColumn = randomNumber.nextInt(3)-1;
            newRowIndex = rowIndex+ deltaRow;
            newColumnIndex = columnIndex+deltaColumn;

            if ((newRowIndex <0)||(newColumnIndex<0)||(newRowIndex>=field.getSizeX())||
            (newColumnIndex >=field.getSizeY())||field.getFieldable(newRowIndex,newColumnIndex)instanceof Player||
                    field.getFieldable(newRowIndex,newColumnIndex)instanceof Enemy){
                regenerateIndex++;
                isNeededToRegenerate=true;
            }
            else {
                if (field.getFieldable(newRowIndex,newColumnIndex)instanceof Flower){
                    Flower flower = (Flower) field.getFieldable(newRowIndex,newColumnIndex);
                    flowerArrayList.remove(flower);

                    field.setFieldable(newRowIndex,newColumnIndex,enemy);
                    field.setFieldable(rowIndex,columnIndex,new Empty());
                    enemy.setRowIndex(newRowIndex);
                    enemy.setColumnIndex(newColumnIndex);
                    isNeededToRegenerate = swapEnemy(rowIndex,columnIndex,newRowIndex,newColumnIndex,enemy);
                }
                else {
                    field.setFieldable(newRowIndex,newColumnIndex,enemy);
                    field.setFieldable(rowIndex,columnIndex,new Empty());
                    enemy.setRowIndex(newRowIndex);
                    enemy.setColumnIndex(newColumnIndex);
                    isNeededToRegenerate = swapEnemy(rowIndex,columnIndex,newRowIndex,newColumnIndex,enemy);
                }
            }

        }
        while (isNeededToRegenerate && regenerateIndex<=10);

        }


    }

    private boolean swapEnemy(int rowIndex,int columnIndex,int newRowIndex,int newColumnIndex,Enemy enemy){
        field.setFieldable(newRowIndex,newColumnIndex,enemy);
        field.setFieldable(rowIndex,columnIndex,new Empty());
        enemy.setRowIndex(newRowIndex);
        enemy.setColumnIndex(newColumnIndex);
        return  false;
    }



    private void genereteFlowers(){

        for (int i = amountOfFlowers - flowerArrayList.size();i>0;){
            int flofersAmountOfTransistors = randomNumber.nextInt(9)+1;
            int floferRowPos = randomNumber.nextInt(rows);
            int floferColumnPos = randomNumber.nextInt(columns);

            if (field.getFieldable(floferRowPos,floferColumnPos)instanceof Player){
                transistorsGethered = transistorsGethered +flofersAmountOfTransistors;
                i--;
            }
            else if (field.getFieldable(floferRowPos,floferColumnPos) instanceof Empty){
                Flower flower = new Flower(floferColumnPos,floferRowPos,floferColumnPos);
                field.setFieldable(floferRowPos,floferColumnPos,flower);
                flowerArrayList.add(flower);
                i--;
            }

    }

}
}
