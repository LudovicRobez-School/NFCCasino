package fr.Players.Exceptions;

/**
 * Created by rl613611 on 17/01/2017.
 */
public class PlayerException extends RuntimeException{

        public PlayerException(){
            super("Unknown error.");
        }

        public PlayerException(String reason, Exception cause) {
            super(reason, cause);
        }
}
