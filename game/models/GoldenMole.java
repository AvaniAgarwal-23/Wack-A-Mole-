package game.models;

import javax.swing.ImageIcon;

public class GoldenMole extends HoleOccupant {

    private static final int REWARD_POINTS = 1000;
    private static final int SHOW_TIME = 1;
    private static ImageIcon icon;

    static {
        try {
            icon = new ImageIcon("resources/images/bonus_mole.png");
        } catch (Exception ex) {
            System.out.println("Bonus mole image failed to load: " + ex.getMessage());
        }
    }

    public GoldenMole() {
        super(SHOW_TIME);
    }

    @Override
    public int whack() {
        hide();
        return REWARD_POINTS;
    }

    @Override
    public ImageIcon getImage() {
        return icon;
    }
}