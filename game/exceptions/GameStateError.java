package game.exceptions;

public class GameStateError extends RuntimeException {

    public GameStateError(String msg) {
        super(msg);
    }

    public GameStateError(String msg, Throwable cause) {
        super(msg, cause);
    }
}