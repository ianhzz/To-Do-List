package exceptions;

public class NotAnIntegerException  extends InvalidInputException{
    public NotAnIntegerException() {};

    public NotAnIntegerException(String msg) {super(msg);}
}
