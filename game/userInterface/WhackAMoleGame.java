package game.userInterface;
import game.engine.Engine;
import game.models.HoleOccupant;
import game.data.*;
import game.exceptions.*;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class WhackAMoleGame extends JFrame {

    private HighScoreManager scoreManager;
    private List<PlayerRecord> scoreList;
    private int bestScore;

    private JLabel scoreDisplay;
    private JLabel bestScoreDisplay;
    private JLabel timerDisplay;

    private JButton[] holeButtons;
    private JButton startBtn;
    private JButton quitBtn;

    private 
    Engine engine;
    private Thread engineThread;

    private static final int TOTAL_HOLES = 18;
    private static final int ROW_COUNT = 3;
    private static final int COLUMN_COUNT = 6;

    public WhackAMoleGame() {
        setTitle("Whack A Mole");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);

        scoreManager = new HighScoreManager();
        readScores();

        buildUI();
        registerCloseHandler();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void readScores() {
        try {
            scoreList = scoreManager.readScores();
            bestScore = scoreManager.fetchHighest(scoreList);
        } catch (ScoreFileException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Unable to load saved scores.",
                    "Error",
                    JOptionPane.WARNING_MESSAGE
            );
            scoreList = new ArrayList<>();
            bestScore = 0;
        }
    }

    private void buildUI() {

        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 10));
        topBar.setBackground(new Color(52, 73, 94));

        scoreDisplay = new JLabel("Score: 0");
        scoreDisplay.setFont(new Font("Arial", Font.BOLD, 18));
        scoreDisplay.setForeground(Color.WHITE);

        bestScoreDisplay = new JLabel("High Score: " + bestScore);
        bestScoreDisplay.setFont(new Font("Arial", Font.BOLD, 18));
        bestScoreDisplay.setForeground(Color.WHITE);

        timerDisplay = new JLabel("Time: 30s");
        timerDisplay.setFont(new Font("Arial", Font.BOLD, 18));
        timerDisplay.setForeground(Color.WHITE);

        topBar.add(scoreDisplay);
        topBar.add(bestScoreDisplay);
        topBar.add(timerDisplay);

        add(topBar, BorderLayout.NORTH);


        JPanel grid = new JPanel(new GridLayout(ROW_COUNT, COLUMN_COUNT, 10, 10));
        grid.setBackground(new Color(173, 216, 230));
        grid.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        holeButtons = new JButton[TOTAL_HOLES];

        for (int i = 0; i < TOTAL_HOLES; i++) {
            holeButtons[i] = new JButton();
            holeButtons[i].setPreferredSize(new Dimension(100, 100));
            holeButtons[i].setEnabled(false);
            holeButtons[i].setFocusPainted(false);
            holeButtons[i].setOpaque(true);
            holeButtons[i].setBorderPainted(false);
            holeButtons[i].setBackground(new Color(60, 60, 60));

            int index = i;
            holeButtons[i].addActionListener(e -> handleHolePress(index));

            grid.add(holeButtons[i]);
        }

        add(grid, BorderLayout.CENTER);

        JPanel bottomBar = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        bottomBar.setBackground(new Color(52, 73, 94));

        startBtn = new JButton("Start Game");
        startBtn.setPreferredSize(new Dimension(150, 40));
        startBtn.setFont(new Font("Arial", Font.BOLD, 16));
        startBtn.addActionListener(e -> beginGame());

        quitBtn = new JButton("Exit");
        quitBtn.setPreferredSize(new Dimension(150, 40));
        quitBtn.setFont(new Font("Arial", Font.BOLD, 16));
        quitBtn.addActionListener(e -> System.exit(0));

        bottomBar.add(startBtn);
        bottomBar.add(quitBtn);

        add(bottomBar, BorderLayout.SOUTH);
    }

    private void beginGame() {

        startBtn.setEnabled(false);

        for (JButton h : holeButtons) h.setEnabled(true);

        engine = new Engine();
        engineThread = new Thread(engine);
        engineThread.start();

        triggerUIUpdates();
    }

    private void handleHolePress(int index) {
        if (engine != null && engine.isRunning()) {
            engine.clickHole(index);
            refreshHole(index);
        }
    }

    private void refreshHole(int index) {
        HoleOccupant obj = engine.fetchOccupant(index);

        if (obj != null && obj.isVisible()) {
            holeButtons[index].setIcon(obj.getImage());
        } else {
            holeButtons[index].setIcon(null);
            holeButtons[index].setBackground(new Color(60, 60, 60));
        }
    }

    private void triggerUIUpdates() {
        Timer uiTimer = new Timer(100, e -> {
            if (engine != null) {
                SwingUtilities.invokeLater(() -> {

                    scoreDisplay.setText("Score: " + engine.getPoints());
                    timerDisplay.setText("Time: " + engine.getRemainingTime() + "s");
                    bestScoreDisplay.setText("High Score: " + bestScore);

                    for (int i = 0; i < TOTAL_HOLES; i++) refreshHole(i);

                    if (engine==null) {
                        ((Timer) e.getSource()).stop();
                        showEndScreen();
                    }
                });
            }
        });
        uiTimer.start();
    }

    private void showEndScreen() {

        for (JButton h : holeButtons) h.setEnabled(false);

        int finalScore = engine.getPoints();

        JPanel overlay = new JPanel();
        overlay.setLayout(null);
        overlay.setBounds(0, 0, getWidth(), getHeight());
        overlay.setBackground(new Color(0, 0, 0, 180));

        JLabel msg = new JLabel("GAME OVER", SwingConstants.CENTER);
        msg.setFont(new Font("Arial", Font.BOLD, 60));
        msg.setForeground(Color.WHITE);
        msg.setBounds(150, 150, 500, 80);

        JLabel showScore = new JLabel("Your Score: " + finalScore, SwingConstants.CENTER);
        showScore.setFont(new Font("Arial", Font.BOLD, 24));
        showScore.setForeground(Color.WHITE);
        showScore.setBounds(250, 260, 300, 40);

        JLabel highScoreLine = new JLabel("High Score: " + bestScore, SwingConstants.CENTER);
        highScoreLine.setFont(new Font("Arial", Font.BOLD, 24));
        highScoreLine.setForeground(Color.WHITE);
        highScoreLine.setBounds(250, 310, 300, 40);

        JButton retry = new JButton("Play Again");
        retry.setBounds(250, 400, 150, 50);
        retry.setFont(new Font("Arial", Font.BOLD, 18));
        retry.setBackground(new Color(76, 175, 80));
        retry.setForeground(Color.WHITE);
        retry.addActionListener(e -> {
            getLayeredPane().remove(overlay);
            repaint();

            String user = JOptionPane.showInputDialog(this, "Enter your name:");

            if (user != null && !user.trim().isEmpty()) {
                scoreList.add(new PlayerRecord(user.trim(), finalScore));

                try {
                    scoreManager.writeScores(scoreList);
                    bestScore = scoreManager.fetchHighest(scoreList);
                    bestScoreDisplay.setText("High Score: " + bestScore);
                } catch (ScoreFileException ex) {
                    System.err.println("Failed saving score.");
                }
            }

            startBtn.setEnabled(true);
        });

        JButton quit = new JButton("Exit");
        quit.setBounds(420, 400, 150, 50);
        quit.setFont(new Font("Arial", Font.BOLD, 18));
        quit.setBackground(new Color(158, 158, 158));
        quit.setForeground(Color.WHITE);
        quit.addActionListener(e -> System.exit(0));

        overlay.add(msg);
        overlay.add(showScore);
        overlay.add(highScoreLine);
        overlay.add(retry);
        overlay.add(quit);

        getLayeredPane().add(overlay, JLayeredPane.POPUP_LAYER);
        repaint();
    }

    private void registerCloseHandler() {
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                if (engineThread != null && engineThread.isAlive()) {
                    engineThread.interrupt();
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(WhackAMoleGame::new);
    }
}