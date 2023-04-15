package org.example;

import java.util.Scanner;

public class OptionsMenu {
    static Scanner scanner = new Scanner(System.in);
    static  Integer command;
    public static void showOptionsMenu(){
        do {
            System.out.println("Please make your choice and press Enter\n" +
                    "1: Show current settings\n"+
                    "2: Change all settings\n"+
                    "3: Exit");

            command = scanner.nextInt();

            switch (command){
                case 1:
                    System.out.println("Current settings:\n" +
                            "\nrows:"+ Main.sizeX +
                            "\ncolumns:"+ Main.sizeY +
                            "\nenemies:"+Main.amountOfEnemis+
                            "\ntransistors:"+Main.transistorsNeeded+
                            "\nmoves:"+Main.moves+
                            "\nflowers:"+Main.getamountOfFlowers);
                    break;
                case 2:
                    System.out.println("Enter new value for ROWS");
                    Main.sizeX =scanner.nextInt();
                    System.out.println("Enter new value for columns");
                    Main.sizeY =scanner.nextInt();
                    System.out.println("Enter new value for enemies");
                    Main.amountOfEnemis =scanner.nextInt();
                    System.out.println("Enter new value for transistors");
                    Main.transistorsNeeded=scanner.nextInt();
                    System.out.println("Enter new value for moves");
                    Main.moves=scanner.nextInt();
                    System.out.println("Enter new value for flowers");
                    Main.getamountOfFlowers=scanner.nextInt();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Command not recognized! Please try again");
                    break;


            }

        }
        while (command!=3);
    }
}
