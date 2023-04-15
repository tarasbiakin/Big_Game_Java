package org.example;

public class Player implements FieldAble {
    private static final String MOVE_LEFT = "a";
    private static final String MOVE_RIGHT = "d";
    private static final String MOVE_UP = "w";
    private static final String MOVE_DOWN = "s";
    private static final String NO_MOVE = "z";
    private int rowIndex;
    private int columnIndex;
    private Field field;
    private Game game;
    @Override
    public String getSymbol() {

        return "\uD83D\uDE10";
    }

    public Player(int rowIndex, int columnIndex, Game game) {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
        this.game = game;
        this.field = game.getField();
        field.setFieldable(rowIndex,columnIndex,this);
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    public Boolean makeMove(String command){
        Boolean isIncorrectMove = true;
        switch (command){
            case MOVE_LEFT:
                isIncorrectMove = movePlayer(0,-1);
                break;
            case MOVE_RIGHT:
                isIncorrectMove = movePlayer(0,1);
                break;
            case MOVE_UP:
                isIncorrectMove = movePlayer(-1,0);
                break;
            case MOVE_DOWN:
                isIncorrectMove = movePlayer(1,0);
            case NO_MOVE:
                isIncorrectMove = false;
                break;
            default:
                showError(command);
                break;

        }
        return isIncorrectMove;
    }

    private void showError(String command) {

        System.out.println("Command not recognized! Please try again"+ command);

    }

    private Boolean movePlayer(int deltaRowiIndex, int deltaColumniIndex){
        int newRowIndex = rowIndex+deltaRowiIndex;
        int newColumnIndex = columnIndex+deltaColumniIndex;

        if ((newRowIndex>=0) &&(newRowIndex < field.getSizeX())&&(newColumnIndex >=0)&&(newColumnIndex<field.getSizeY())&&
         !((field.getFieldable(newRowIndex,newColumnIndex))instanceof Enemy)){
            if (field.getFieldable(newRowIndex,newColumnIndex)instanceof Flower){
                Flower flower = (Flower) field.getFieldable(newRowIndex,newColumnIndex);
                game.setTransistorsGethered(flower.getTransistiors());
                game.getFlowerArrayList().remove(flower);
                swapPlayer(newRowIndex,newColumnIndex);
            }
            if (field.getFieldable(newRowIndex,newColumnIndex)instanceof Empty){
                swapPlayer(newRowIndex,newColumnIndex);
            }
            return false;
        }
        else {
            return true;
        }


    }
    private void swapPlayer(int newRowIndex,int newColumnIndex){
        field.setFieldable(newRowIndex,newColumnIndex,this);
        field.setFieldable(rowIndex,columnIndex, new Empty());
        rowIndex=newRowIndex;
        columnIndex = newColumnIndex;
    }

}
