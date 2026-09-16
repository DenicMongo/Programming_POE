/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

/**
 *
 * @author mongezi sithole
 */
class LoginClass {
    
    String firstName;
    String lastName;
    String username;
    String password;
    String cellPhoneNumber;
    
    public boolean checkUserName(String username){
        if (username.contains("_") && username.length() <= 5) {
            System.out.println("\nUsername successfully captured.");
            return true;
        } else {
            System.out.println(
                "Username is not correctly formatted; please ensure that " +
                "your username contains an underscore and is no more than " +
                "5 characters in length."
            );
            return false;
        }
    }
    public boolean checkPasswordComplexity(String password){
        boolean hasUpper   = password.matches(".*[A-Z].*");
        boolean hasDigit   = password.matches(".*[0-9].*");
        boolean hasSpecial = password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*");
        boolean hasLength  = password.length() >= 8;
        
        if (hasUpper && hasDigit && hasSpecial && hasLength) {
            System.out.println("Password successfully captured.");
            return true;
        } else {
            System.out.println(
                "Password is not correctly formatted; please ensure that " +
                "the password contains at least 8 characters, a capital " +
                "letter, a number, and a special character."
            );
            return false;
        }
    }
    public boolean checkCellPhoneNumber(String number){
        if (number.matches("^\\+27(?: ?)\\d{9}$")) {
            System.out.println("Cellphone number successfully added.");
            return true;
        } else {
            System.out.println(
                "Cellphone number incorrectly formatted or does not contain international code."
            );
            return false;
        }
    }
    public String registerUser(String firstName, String lastName, String username, String password, String number){
        if (checkUserName(username) && checkPasswordComplexity(password)
                && checkCellPhoneNumber(number)) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.username = username;
            this.password = password;
            this.cellPhoneNumber = number;
            return "User has been registered successfully.";
        } else {
            return "Registration failed due to invalid input.";
        }
    }
    public boolean loginUser(String username, String password){
        
        if (this.username == null || this.password == null) {
            return false;
        }
        return this.username.equals(username) && this.password.equals(password);
    }
    public String returnLoginStatus(boolean success){
        
        if (success) {
            return "Welcome " + firstName + " " + lastName + ", it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}
