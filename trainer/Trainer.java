package trainer;
import pokemon.Pokemon;
public class Trainer {
    private String name;
    private Pokemon[] pokemons;
    private int badgeCount;
    private int lossCount;
    private boolean eliminated;

    public Trainer(String name) {
        this.name = name;
        this.pokemons = new Pokemon[6];
    }
    public boolean addPokemon(int slot, Pokemon p) {
        if (slot < 1 || slot > 6) return false;
        pokemons[slot - 1] = p;
        return true;
    }
    public Pokemon getPokemonAtSlot(int slot) {
        if (slot < 1 || slot > 6) return null;
        return pokemons[slot - 1];
    } 
    public String getName() {
        return name;
    }
    public int getBadgeCount() {
        return badgeCount;
    }
    public void recordWin() {
        badgeCount++;
    }
    public void recordLoss() {
        badgeCount = Math.max(0, badgeCount - 1);
        lossCount++;
        if (lossCount >= 3) eliminated = true;
    }
    public boolean isEliminated() {
        return eliminated;
    }
}