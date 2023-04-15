package org.example;

import java.util.Scanner;

public class Main {
    public static int sizeX = 12;
    public static int sizeY =20;
    public static int amountOfEnemis =12;
    public static int transistorsNeeded =150;
    public static int moves =60;
    public static int getamountOfFlowers =12;
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String command;
        do {
            System.out.println("Welcome to Game.Please make your choice");
            System.out.println("1: Srart new game");
            System.out.println("2: Options");
            System.out.println("3: Credits");
            System.out.println("4: Exit");

            command = scanner.nextLine();

            switch (command){
                case "1":
                    startNewGame();
                    break;
                case "2":
                    OptionsMenu.showOptionsMenu();
                    break;
                case "3":
                    showCredits();
                case "4":
                    break;
                default:
                    System.out.println("Command not recognized! Please try again");
            }

        }
        while (!command.equals("4"));
    }

    private static void showCredits() {
        System.out.println("Это учебная работа, для отработки ООП");
    }
    private static void startNewGame() {
        Game game = new Game(sizeX, sizeY,amountOfEnemis,transistorsNeeded,moves,getamountOfFlowers);

        game.fillFieldWithEmtyObj();

        game.startGame();
    }
}