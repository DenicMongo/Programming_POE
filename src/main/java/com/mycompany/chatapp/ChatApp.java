/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.chatapp;
import java.util.Scanner;
/**
 *
 * @author mongezi sithole
 */
public class ChatApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LoginClass obj = new LoginClass();
        
        System.out.println("====================================================");
        System.out.println("              WELCOME TO MY CHAT APP                ");
        System.out.println("====================================================");
        
        // User inputs code
        // trim code removes unnecessary spaces at the beginning and ending of the user inputs results
        System.out.print("Enter your first name: ");
        String firstName = scanner.nextLine().trim();
        
        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine().trim();
        
        System.out.print("Enter username (max 5 chars, must include '_'): ");
        String username = scanner.nextLine().trim();
        
        System.out.print("Create a strong password : ");
        String password = scanner.nextLine();
        
        System.out.print("Enter cellphone number (+27 XXXXXXXXX): ");
        String number = scanner.nextLine().trim();
        
        //Below displays the results from the user's personal information.
        
        System.out.println(obj.registerUser(firstName, lastName, username, password, number));
        
        System.out.println("\n================= ChatApp Login ==================");
        
        System.out.print("Username: ");
        String loginUsername = scanner.nextLine().trim();
        
        System.out.print("Password: ");
        String loginPassword = scanner.nextLine();
        
        // below code shows if the username and password are the same 
        
        boolean loggedIn = obj.loginUser(loginUsername, loginPassword);
        
        // below are the results from the login information a user entered.
        
        System.out.println(obj.returnLoginStatus(loggedIn));
        if (!loggedIn) {
            System.out.println("Login failed. Exiting.");
            scanner.close();
            return;
        }
        
        scanner.close();
    }
}
