package org.example;

public class Field {
    private int sizeX;
    private int sizeY;

    private FieldAble[][] field;

    public Field(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        field = new FieldAble[sizeX][sizeY];

    }

    public int getSizeX() {
        return sizeX;
    }
    public int getSizeY(){
        return sizeY;

    }
    public void setFieldable(int x,int y, FieldAble object){
        field[x][y]= object;

    }

    public FieldAble getFieldable(int sizeX,int sizeY){
        return field[sizeX][sizeY];
    }

    public void showFild(){
        System.out.println();
        for (int i = 0; i< sizeX; i++){
            System.out.println();
            for (int j = 0; j< sizeY; j++){
                System.out.print(field[i][j].getSymbol());
            }
        }
        System.out.println();

    }
}
