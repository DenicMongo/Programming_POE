/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

import java.util.Random;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mongezi sithole
 */
public class Message {
    
    //Attributes
    
    String messageID;
    int messageNumber;
    String recipient;
    String messageText;
    String messageHash;
    String status;
    
    // Counts the total number of messages successfully sent.
    private static int totalMessages = 0;
    
    // Stores sent messages while the application is running.
    private static final List<Message> messages = new ArrayList<>();
    
    //Message Methods below
    
    public Message(int messageNumber, String recipient,String messageText){
        this.messageID = generateMessageID();
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageHash = createMessageHash();
        this.status = "Pending";
    }
    
    
    // Generates a random 10-digit message ID.
    private String generateMessageID() {
        Random rand = new Random();
        long id = (long)(rand.nextDouble() * 9_000_000_000L) + 1_000_000_000L;
        return String.valueOf(id);
    }
    
    
    // Checks that the message ID contains no more than 10 characters.
    public boolean checkMessageID(){
        return messageID != null && messageID.length() <= 10;
    }
    
    
    // Checks whether the message contains 250 characters or fewer
    public String checkMessageLength() {
        if (messageText.length() <= 250) {
        return "Message ready to send.";
        }
        
        return "Message exceeds 250 characters by " 
                + (messageText.length() - 250) + "; please reduce the size.";
    }
    
    
    // Checks whether the recipient uses the South African +27 format.
    public String checkRecipientCell(){
        if (recipient != null && recipient.matches("^\\+27(?: ?)\\d{9}$")) {
            return "Cellphone number successfully captured.";
        }
        
        return "Cell phone number is incorrectly formatted or does not contain "
            + "an international code. Please correct the number and try again.";
    }
    
    
    
    // Creates a message hash using the ID, message number,
    // first word and last word of the message.
    public String createMessageHash() {
        String[] words = messageText.trim().split("\\s+");
        
        String firstWord = words.length > 0 ? words[0] : "";
        String lastWord = words.length > 0 ? words[words.length - 1] : "";
        
        // Use the first two characters of the message ID.
        String idStart = messageID.substring(0, 2);
        
        return (idStart + ":" + messageNumber + ":" + firstWord + lastWord).toUpperCase();
    }
    
    
    // Displays the available actions and asks the user to choose one
    public String SentMessage(Scanner scanner) {
        
        if (messageText.length() > 250)
            return "Please enter a message of less than 250 characters.";
        if (!checkRecipientCell().contains("successfully"))
            return checkRecipientCell();
        if (!checkMessageID())
            return "Message ID is invalid.";
        
        System.out.println("\nChoose an option:");
        System.out.println("1. Send Message");
        System.out.println("2. Disregard Message");
        System.out.println("3. Store Message to send later");
        System.out.print("\nEnter your choice: ");
        String choice = scanner.nextLine();
        
        return processMessageChoice(choice);
    }
    //SentMessage ends here
        
    public String processMessageChoice(String choice){   
        switch (choice) {
            case "1":
                status = "Sent";
                totalMessages++;
                messages.add(this);
                return "Message successfully sent";
                
            case "2":
                status = "Disregarded";
                return "Press O to delete the message";
                
            case "3":
                status = "Stored";
                storeMessage();
                return "Message successfully stored";
                
            default:
                return "Invalid choice.";
        }
    }
    
    
    //Returns the total number of messages sent
    public static void resetTotalMessages() {
        totalMessages = 0;
    }
    
    public String printMessages() {
        StringBuilder output = new StringBuilder();
        
        for (Message msg : messages) {
           output.append("Message ID: ").append(msg.messageID).append("\n");
           output.append("Message Hash: ").append(msg.messageHash).append("\n");
           output.append("Recipient: ").append(msg.recipient).append("\n");
           output.append("Message: ").append(msg.messageText).append("\n\n");
        }
        return output.toString();
    }
    
    
    // Resets the message count; useful when running unit tests
    public static int returnTotalMessagess() {
        return totalMessages;
    }
    
    
    //Saves a message as a JSON object in messages.json.
    public void storeMessage() {
        try (FileWriter writer = new FileWriter("messages.json", true)) {
        
        writer.write("{"
                + "\"messageID\":\"" + escapeJSON(messageID) + "\","
                + "\"messageNumber\":" + messageNumber + ","
                + "\"messageHash\":\"" + escapeJSON(messageHash) + "\","
                + "\"recipient\":\"" + escapeJSON(recipient) + "\","
                + "\"message\":\"" + escapeJSON(messageText) + "\","
                + "\"status\":\"" + escapeJSON(status) + "\""
                + "}\n");
        }
        catch (IOException e) {
            System.out.println("Error storing message: " + e.getMessage());
        }
    }
    
    
    // Escapes special characters so text can be written safely in JSON.
    private String escapeJSON(String text) {
        return text
                .replace("\\", "\\\\") 
                .replace("\"", "\\\"") 
                .replace("\n", "\\n") 
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }
    
    public String getMessageID() {
        return messageID;
    }
    
    public String getRecipient() {
        return recipient;
    }
    
    public String getMessageText() {
        return messageText;
    }
    
    public String getMessageHash() {
        return messageHash;
    }
    
    public String getStatus() {
        return status;
    }
    
}
