package com.mycompany.chatapp;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author mongezi sithole
 */
public class MessageTest {
    
    private Message message1;
    private Message message2;
    
    @BeforeEach
    public void setUp() {
        message1 = new Message(
                0, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        
        message2 = new Message(
                1, "08575975889", "Hi Keegan, did you receive the payment?");
    }
    
    @Test
    public void testMessageLengthSuccess() {
        assertEquals( 
                "Message ready to send.", message1.checkMessageLength());
    }
    
    @Test
    public void testMessageLengthFailure() {
        String longMessage = "A".repeat(260);
        Message longMsg = new Message(
                0, "+27718693002", longMessage);
        
        assertEquals(
            "Message exceeds 250 characters by 10; please reduce the size.",
            longMsg.checkMessageLength());
    }
    
    @Test
    public void testRecipientNumberSuccess() {
        assertEquals(
                "Cell phone number successfully captured.",
                message1.checkRecipientCell());
    }
    
    @Test
    public void testRecipientNumberFailure() {
        assertEquals(
                "Cell phone number is incorrectly formatted or does not contain "
                + "an international code. Please correct the number and try again.",
                message2.checkRecipientCell());
    }
    
    @Test
    public void testMessageIDCreated() {
        assertNotNull(message1.getMessageID());
        assertEquals(10, message1.getMessageID().length());
        assertTrue(message1.checkMessageID());
    }
    
    @Test
    public void testSendMessageOptions() {
        // These tests require a testable version of SentMessage
        // that accepts the user's choice as an argument.
        assertEquals(
                "Message successfully sent.",
                message1.processMessageChoice("1")
        );
        
        assertEquals(
                "Press 0 to delete the message.",
                message2.processMessageChoice("2"));
        
        Message message3 = new Message(
                2, "+27718693002", "Stored message");
        
        assertEquals(
               "Message successfully stored.",
                message3.processMessageChoice("3"));
    }
    
    @Test
    public void testTotalMessagesSent() {
        Message.resetTotalMessages();
        
        Message sentMessage = new Message(
                0, "+27718693002", "Test message");
        
        sentMessage.processMessageChoice("1");
        
        assertEquals(1, Message.returnTotalMessagess());
    }
    
    
    
}
