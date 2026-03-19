package game.models;

import javax.swing.ImageIcon;

public abstract class HoleOccupant {

    protected boolean visible;
    protected int timeLeft;
    protected int durationLimit;

    public HoleOccupant(int durationLimit) {
        this.durationLimit = durationLimit;
        this.timeLeft = durationLimit;
        this.visible = true;
    }

    public abstract int whack();
    public abstract ImageIcon getImage();

    public void hide() {
        visible = false;
    }

    public void tick() {
        if (visible && timeLeft > 0) {
            timeLeft--;
            if (timeLeft == 0) {
                hide();
            }
        }
    }

    public void show() {
        visible = true;
        timeLeft = durationLimit;
    }
    
    public boolean isVisible() {
        return visible;
    }

    public int getTimeRemaining() {
        return timeLeft;
    }
}
