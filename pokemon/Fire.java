package pokemon;
public abstract class Fire extends Pokemon {
    public Fire(String name, int health) {
        super(name, health);
    }
    @Override
    public String getType() {
        return "Fire";
    }
}4