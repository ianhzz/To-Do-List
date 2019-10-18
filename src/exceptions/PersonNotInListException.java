package exceptions;

import static userInterface.ui.Main.iw;

public class PersonNotInListException extends InvalidInputException {
    public PersonNotInListException(String msg) {iw.changeOutput(msg);
    }
}
