package oak;
import java.util.Scanner;
import trainer.Trainer;
import pokemon.*;

public class OakJournal {

    private Trainer[] trainers;
    private int trainerCount;

    public OakJournal() {
        trainers = new Trainer[10];
        trainerCount = 0;
    }

    public void addTrainer(Trainer t) {
        if (trainerCount < trainers.length) {
            trainers[trainerCount++] = t;
        }
    }
    private int determineWinner(Pokemon p1, Pokemon p2) {
        String t1 = p1.getType();
        String t2 = p2.getType();

        if (t1.equals("Water") && t2.equals("Fire")) return 1;
        if (t2.equals("Water") && t1.equals("Fire")) return 2;

        if (t1.equals("Fire") && t2.equals("Grass")) return 1;
        if (t2.equals("Fire") && t1.equals("Grass")) return 2;

        if (t1.equals("Grass") && t2.equals("Water")) return 1;
        if (t2.equals("Grass") && t1.equals("Water")) return 2;

        if (t1.equals("Electric") && t2.equals("Water")) return 1;
        if (t2.equals("Electric") && t1.equals("Water")) return 2;

        if (p1.getHealth() > p2.getHealth()) return 1;
        if (p2.getHealth() > p1.getHealth()) return 2;

        return 0;
    }

    public void runShowdown(Trainer t1, Trainer t2) {
    System.out.println("\nShowdown: " + t1.getName() + " vs. " + t2.getName());
    int w1 = 0, w2 = 0;
    for (int i = 1; i <= 6; i++) {
        Pokemon p1 = t1.getPokemonAtSlot(i);
        Pokemon p2 = t2.getPokemonAtSlot(i);
        String s1 = (p1 == null) ? "No Pokemon" : p1.toString();
        String s2 = (p2 == null) ? "No Pokemon" : p2.toString();

        if (p1 == null && p2 == null) {
            System.out.println("Round " + i + ": No Pokemon vs No Pokemon -> Draw!");
        } else if (p1 != null && p2 == null) {
            w1++;
            System.out.println("Round " + i + ": " + s1 + " vs No Pokemon -> Winner: " + t1.getName());
        } else if (p1 == null && p2 != null) {
            w2++;
            System.out.println("Round " + i + ": No Pokemon vs " + s2 + " -> Winner: " + t2.getName());
        } else {
            int res = determineWinner(p1, p2);
            if (res == 1) {
                w1++;
                System.out.println("Round " + i + ": " + s1 + " vs " + s2 + " -> Winner: " + t1.getName());
            } else if (res == 2) {
                w2++;
                System.out.println("Round " + i + ": " + s1 + " vs " + s2 + " -> Winner: " + t2.getName());
            } else {
                System.out.println("Round " + i + ": " + s1 + " vs " + s2 + " -> Draw!");
            }
        }
    }
    if (w1 > w2) {
        System.out.println("Winner of the battle is: " + t1.getName());
        t1.recordWin(); t2.recordLoss();
    } else if (w2 > w1) {
        System.out.println("Winner of the battle is: " + t2.getName());
        t2.recordWin(); t1.recordLoss();
    } else {
        System.out.println("Final Result: Draw!");
    }
}
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        OakJournal oak = new OakJournal();

        Trainer Android = new Trainer("Android");
        Android.addPokemon(1, new Charmander("Charmander", 50, 12));
        Android.addPokemon(2, new Pikachu("Pikachu", 45, 15));
        Android.addPokemon(3, new Bulbasaur("Bulbasaur", 55, 10));
        Android.addPokemon(4, new Vulpix("Vulpix", 40, 9));
        Android.addPokemon(5, new Greninja("Greninja", 60, 18));
        Android.addPokemon(6, new Chikorita("Chikorita", 48, 11));
        Trainer Apple = new Trainer("Apple");
        Apple.addPokemon(1, new Squirtle("Squirtle", 52, 10));
        Apple.addPokemon(2, new Psyduck("Psyduck", 46, 8));
        Apple.addPokemon(3, new Bulbasaur("Bulbasaur", 54, 11));
        Apple.addPokemon(4, new Charmander("Charmander", 51, 12));
        Apple.addPokemon(5, new Vulpix("Vulpix", 43, 10));
        Apple.addPokemon(6, new Pikachu("Pikachu", 47, 14));
        oak.addTrainer(Android);
        oak.addTrainer(Apple);

        oak.runShowdown(Android, Apple);
        while (true) {
            System.out.println("\n ---- Oak Journal Menu -------");
            System.out.println("| 1. Add Trainer              |");
            System.out.println("| 2. Add Pokemon to Trainer   |");
            System.out.println("| 3. Run Showdown             |");
            System.out.println("| 4. Exit                     |");
             System.out.println(" ---------------------------");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter Trainer name: ");
                    String tName = sc.nextLine();
                    oak.addTrainer(new Trainer(tName));
                    System.out.println("Trainer added.");
                    break;
                case 2:
                    System.out.print("Enter trainer index (0-" + (oak.trainerCount - 1) + "): ");
                    int idx = sc.nextInt(); 
                    Trainer t = oak.trainers[idx];
                    System.out.print("Enter Pokemon type (1=Fire,2=Water,3=Grass,4=Electric): ");
                    int type = sc.nextInt();
                    System.out.print("Enter slot (1-6): ");
                    int slot = sc.nextInt(); 
                    System.out.print("Enter name: "); String name = sc.nextLine();
                    System.out.print("Enter health: "); int hp = sc.nextInt();
                    System.out.print("Enter special dmg: "); int dmg = sc.nextInt();
                    Pokemon p = null;
                    if (type == 1) p = (slot % 2 == 0) ? new Vulpix(name,hp,dmg) : new Charmander(name,hp,dmg);
                    else if (type == 2) p = (slot % 2 == 0) ? new Psyduck(name,hp,dmg) : new Squirtle(name,hp,dmg);
                    else if (type == 3) p = (slot % 2 == 0) ? new Chikorita(name,hp,dmg) : new Bulbasaur(name,hp,dmg);
                    else if (type == 4) p = new Pikachu(name,hp,dmg);
                    t.addPokemon(slot, p);
                    System.out.println("Pokemon added.");
                    break;
                case 3:
                    System.out.print("Enter trainer1 index: ");
                    int i1 = sc.nextInt();
                    System.out.print("Enter trainer2 index: ");
                    int i2 = sc.nextInt();
                    oak.runShowdown(oak.trainers[i1], oak.trainers[i2]);
                    break;
                case 4:
                    System.out.println("Exiting");
                    sc.close();
                    return;
            }
        }
    }
}