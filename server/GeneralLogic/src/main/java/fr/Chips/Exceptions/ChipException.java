package fr.Chips.Exceptions;

/**
 * Created by rl613611 on 17/01/2017.
 */
public class ChipException extends RuntimeException {

    public ChipException(){
        super("Unknown error.");
    }

    public ChipException(String reason, Exception cause) {
        super(reason, cause);
    }
}
