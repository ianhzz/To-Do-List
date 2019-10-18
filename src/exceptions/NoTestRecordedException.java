package exceptions;

import static userInterface.ui.Main.iw;

public class NoTestRecordedException extends InvalidInputException {
    public NoTestRecordedException() {
        iw.changeOutput("No tests recorded for this subject!");
    }
}
