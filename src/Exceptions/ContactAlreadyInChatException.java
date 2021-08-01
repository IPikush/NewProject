package Exceptions;

public class ContactAlreadyInChatException extends Exception{
    public ContactAlreadyInChatException(String message) {
        super(message);
    }
}
