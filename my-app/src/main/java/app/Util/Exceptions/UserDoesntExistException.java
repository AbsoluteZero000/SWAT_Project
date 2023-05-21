package app.Util.Exceptions;

public class UserDoesntExistException extends Exception{
    public UserDoesntExistException(){
        super("user isn't authenticated");
    }
}
