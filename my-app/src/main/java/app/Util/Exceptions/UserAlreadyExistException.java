package app.Util.Exceptions;

public class UserAlreadyExistException extends Exception{
    public UserAlreadyExistException(){
        super("user Already Exist");
    }
}
