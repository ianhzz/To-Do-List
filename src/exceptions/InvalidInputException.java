package exceptions;

import static userInterface.ui.Main.iw;

public class InvalidInputException extends Exception {
    public InvalidInputException() {
    }

    public InvalidInputException(String msg) {
        iw.addToOutput( "\n\n\n"+msg);
    }
}
