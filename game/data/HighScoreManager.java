package game.data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import game.exceptions.ScoreFileException;

public class HighScoreManager {

    private static final String FILE_NAME = "scores.dat";

    public void writeScores(List<PlayerRecord> scores) throws ScoreFileException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(scores);
            System.out.println("Scores saved.");
        } catch (IOException ex) {
            throw new ScoreFileException("Unable to store score data.", ex);
        }
    }

    public List<PlayerRecord> readScores() throws ScoreFileException {
        File f = new File(FILE_NAME);

        if (!f.exists()) {
            System.out.println("No existing score file. Creating a fresh one.");
            return new ArrayList<>();
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            List<PlayerRecord> result = (List<PlayerRecord>) in.readObject();
            System.out.println("Scores loaded.");
            return result;

        } catch (IOException ex) {
            throw new ScoreFileException("Error loading score data.", ex);

        } catch (ClassNotFoundException ex) {
            throw new ScoreFileException("Saved score format mismatch.", ex);
        }
    }
    public int fetchHighest(List<PlayerRecord> scores) {
        if (scores == null || scores.isEmpty()) {
            return 0;
        }

        int max = 0;
        for (PlayerRecord score : scores) {
            if (score.getPoints() > max) {
                max = score.getPoints();
            }
        }
        return max;
    }
}