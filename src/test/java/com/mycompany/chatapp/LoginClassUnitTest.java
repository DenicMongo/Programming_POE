/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.chatapp;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author mongezi sithole
 */
public class LoginClassUnitTest {

    LoginClass obj = new LoginClass();
    
    @Test
    public void testCheckUserNameCorrect() {
        assertTrue(obj.checkUserName("Ta_ta"));
    }

    @Test
    public void testCheckUserNameIncorrect() {
        assertFalse(obj.checkUserName("Tata!!!!!!"));
    }

    @Test
    public void testCheckPasswordComplexityCorrect() {
        assertTrue(obj.checkPasswordComplexity("@2468@AbCd"));
    }

    @Test
    public void testCheckPasswordComplexityIncorrect() {
        assertFalse(obj.checkPasswordComplexity("password"));
    }

    @Test
    public void testCheckCellPhoneNumberCorrect() {
        assertTrue(obj.checkCellPhoneNumber("+27726979355"));
    }

    @Test
    public void testCheckCellPhoneNumberIncorrect() {
        assertFalse(obj.checkCellPhoneNumber("08966553"));
    }

    @Test
    public void testLoginSuccessful() {

        obj.registerUser(
                "Mongezi",
                "Sithole",
                "Ta_ta",
                "@2468@AbCd",
                "+27726979355"
        );

        assertTrue(
                obj.loginUser("Ta_ta", "@2468@AbCd")
        );
    }

    @Test
    public void testLoginFailed() {

        obj.registerUser(
                "Mongezi",
                "Sithole",
                "Ta_ta",
                "@2468@AbCd",
                "+27726979355"
        );

        assertFalse(
                obj.loginUser("wrong_username", "wrong_password")
        );
    }
}
