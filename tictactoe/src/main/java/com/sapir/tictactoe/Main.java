package com.sapir.tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter 1 for User vs Computer");
        System.out.println("Enter 2 for Computer vs Computer");

        int choice = scanner.nextInt();
        while(choice != 1 && choice != 2) {
            System.out.println("Invalid choice. Please enter 1 or 2:");
            choice = scanner.nextInt();
        }
        if (choice == 1) {
            System.out.println("Starting User vs Computer...");
            new UserGame();
            scanner.nextLine();
        } else if (choice == 2) {
            System.out.println("Starting Computer vs Computer...");
            new SelfGame();
        }

        
    }
}


