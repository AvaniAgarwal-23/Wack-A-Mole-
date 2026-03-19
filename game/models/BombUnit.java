package game.models;

import javax.swing.ImageIcon;

public class BombUnit extends HoleOccupant {

    private static final int PENALTY_VALUE = -500;
    private static final int VISIBLE_DURATION = 3;
    private static ImageIcon icon;

    static {
        try {
            icon = new ImageIcon("resources/images/bomb.png");
        } catch (Exception ex) {
            System.out.println("Could not load bomb graphic: " + ex.getMessage());
        }
    }

    public BombUnit() {
        super(VISIBLE_DURATION);
    }

    @Override
    public int whack() {
        hide();
        return PENALTY_VALUE;
    }

    @Override
    public ImageIcon getImage() {
        return icon;
    }
}