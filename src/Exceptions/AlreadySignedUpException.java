package Exceptions;

public class AlreadySignedUpException extends Exception{
    public AlreadySignedUpException(String message){
        super(message);
    }
}
