package org.example;

import java.util.Objects;

public class Flower implements FieldAble{

    private int transistiors;

    private int roewIndex;
    private int columnIndex;

    public int getRoewIndex() {
        return roewIndex;
    }
    public int getColumnIndex() {
        return columnIndex;
    }
    public void setRoewIndex(int roewIndex) {
        this.roewIndex = roewIndex;
    }

    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }


    public int getTransistiors() {
        return transistiors;
    }
    public Flower(int transistiors,int roewIndex, int columnIndex) {
        this.transistiors = transistiors;
        this.roewIndex = roewIndex;
        this.columnIndex = columnIndex;
    }

    @Override
    public String getSymbol() {
        return String.valueOf(" "+transistiors+" ");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flower flower = (Flower) o;

        if (roewIndex != flower.roewIndex) return false;
        return columnIndex == flower.columnIndex;
    }

    @Override
    public int hashCode() {
        int result = roewIndex;
        result = 31 * result + columnIndex;
        return result;
    }
}
