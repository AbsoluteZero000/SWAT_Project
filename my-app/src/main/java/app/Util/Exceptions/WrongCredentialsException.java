package app.Util.Exceptions;

public class WrongCredentialsException extends Exception {
    public WrongCredentialsException() {
        super("Wrong Email or password");
    }
}
