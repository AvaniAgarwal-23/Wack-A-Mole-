package game.exceptions;
public class ScoreFileException extends Exception {

    public ScoreFileException(String msg) {
        super(msg);
    }

    public ScoreFileException(String msg, Throwable cause) {
        super(msg, cause);
    }
}