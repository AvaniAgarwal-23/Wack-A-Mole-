package game.engine;
import game.models.*;
public class Engine implements Runnable {

    private int points;
    private int timeLeft;
    private boolean active;
    private HoleOccupant[] slots;

    private static final int TOTAL_TIME = 30;
    private static final int HOLE_COUNT = 18;

    public Engine() {
        points = 0;
        timeLeft = TOTAL_TIME;
        active = false;
        slots = new HoleOccupant[HOLE_COUNT];

        for (int i = 0; i < HOLE_COUNT; i++) {
            slots[i] = null;
        }
    }

    private void refreshOccupants() {
        for (int i = 0; i < slots.length; i++) {
            if (slots[i] != null && slots[i].isVisible()) {
                slots[i].tick();
                if (!slots[i].isVisible()) {
                    slots[i] = null;
                }
            }
        }
    }

    private void createRandomOccupant() {
        int freeSpaces = 0;

        for (HoleOccupant h : slots) {
            if (h == null) freeSpaces++;
        }

        if (freeSpaces < 3) return;

        int tries = 0;

        while (tries < 5) {
            int pos = (int) (Math.random() * HOLE_COUNT);

            if (slots[pos] == null) {
                int typeChance = (int) (Math.random() * 100);

                if (typeChance < 60) {
                    slots[pos] = new Mole();
                } else if (typeChance < 85) {
                    slots[pos] = new BombUnit();
                } else {
                    slots[pos] = new GoldenMole();
                }
                return;
            }
            tries++;
        }
    }

    public void clickHole(int index) {
        if (index >= 0 && index < HOLE_COUNT) {
            if (slots[index] != null && slots[index].isVisible()) {
                int gained = slots[index].whack();
                points += gained;
                System.out.println("Change: " + gained + "  | Score: " + points);
                slots[index] = null;
            }
        }
    }

    public int getPoints() {
        return points;
    }

    public int getRemainingTime() {
        return timeLeft;
    }

    public HoleOccupant fetchOccupant(int index) {
        return (index >= 0 && index < HOLE_COUNT) ? slots[index] : null;
    }

    public boolean isRunning() {
        return active;
    }

    public void stop() {
        active = false;
    }

    private void finish() {
        active = false;
        System.out.println("Time's up! Your Score: " + points);
    }

    @Override
    public void run() {
        active = true;
        int timer = 0;

        while (active && timeLeft > 0) {
            try {
                refreshOccupants();

                if (timer % 1 == 0) {
                    createRandomOccupant();
                }
                timer++;
                timeLeft--;
                Thread.sleep(1000);

            } catch (InterruptedException ex) {
                System.out.println("Engine stopped unexpectedly.");
                active = false;
            }
        }

        finish();
    }
}