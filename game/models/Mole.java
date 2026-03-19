package game.models;

import javax.swing.ImageIcon;

public class Mole extends HoleOccupant {

    private static final int POINT_VALUE = 100;
    private static final int VISIBLE_TIME = 2;
    private static ImageIcon icon;

    static {
        try {
            icon = new ImageIcon("resources/images/mole.png");
        } catch (Exception ex) {
            System.out.println("Failed to load mole image: " + ex.getMessage());
        }
    }

    public Mole() {
        super(VISIBLE_TIME);
    }

    @Override
    public int whack() {
        hide();
        return POINT_VALUE;
    }

    @Override
    public ImageIcon getImage() {
        return icon;
    }
}
