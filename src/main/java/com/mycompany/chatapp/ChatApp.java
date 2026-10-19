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
        
 
        
        // CHAT APP Login
        
    
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
        
        System.out.println("\n=============== Welcome to ChatApp ===============");
        
        int choice;
        int messageNumber = 0;
        int messageLimit = 0;
        
        do {
            System.out.println("\n1. Send Messages");
            System.out.println("2. Show recently sent messages");
            System.out.println("3. Quit");
            System.out.print("Choose an option: ");
            
            String input = scanner.nextLine();
            
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter 1, 2, or 3.");
                choice = 0;
                continue;
            }
            
            switch (choice) {

                case 1:
                    if (messageLimit == 0) {
                        System.out.print(
                                "How many messages would you like to enter? ");

                        try {
                            messageLimit = Integer.parseInt(
                                    scanner.nextLine());

                            if (messageLimit <= 0) {
                                System.out.println(
                                        "Enter a number greater than zero.");
                                messageLimit = 0;
                                break;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Please enter a valid number.");
                            break;
                        }
                    }

                    while (messageNumber < messageLimit) {

                        System.out.println("\nMessage "
                                + (messageNumber + 1) + " of "
                                + messageLimit);

                        System.out.print("Enter recipient (+27XXXXXXXXX): ");
                        String recipient = scanner.nextLine().trim();

                        System.out.print("Enter your message: ");
                        String messageText = scanner.nextLine();

                        if (messageText.length() > 250) {
                            System.out.println(
                                "Please enter a message of less than 250 characters.");
                            continue;
                        }

                        Message msg = new Message(
                                messageNumber + 1,
                                recipient,
                                messageText);

                        if (!msg.checkMessageID()) {
                            System.out.println("Invalid message ID.");
                            continue;
                        }

                        String recipientResult = msg.checkRecipientCell();

                        if (!recipientResult.startsWith("Cell phone number successfully")) {
                            System.out.println(recipientResult);
                            continue;
                        }

                        System.out.println("Message sent");

                        // Pass the existing Scanner to Message.java.
                        System.out.println(msg.SentMessage(scanner));

                        if ("Sent".equals(msg.getStatus())) {
                            messageNumber++;

                            System.out.println("\nMessage ID: "
                                    + msg.getMessageID());
                            System.out.println("Message Hash: "
                                    + msg.getMessageHash());
                            System.out.println("Recipient: "
                                    + msg.getRecipient());
                            System.out.println("Message: "
                                    + msg.getMessageText());
                        }

                        if (messageNumber >= messageLimit) {
                            System.out.println(
                                    "\nYou have reached your message limit.");
                            break;
                        }

                        System.out.print(
                                "\nWould you like to enter another message? (yes/no): ");

                        String answer = scanner.nextLine().trim();

                        if (!answer.equalsIgnoreCase("yes")) {
                            break;
                        }
                    }

                    break;

                case 2:
                    System.out.println("Coming Soon.");
                    break;

                case 3:
                    System.out.println("Total messages sent: " + Message.returnTotalMessagess());
                    System.out.println("Goodbye.");
                    break;

                default:
                    System.out.println("Invalid option. Choose 1, 2, or 3.");
            }

        } while (choice != 3);
        
        
        scanner.close();
    }
}
