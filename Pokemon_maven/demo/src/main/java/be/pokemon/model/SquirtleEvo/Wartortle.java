package be.pokemon.model.SquirtleEvo;

import java.util.Locale;

import be.pokemon.implementations.Attack1;
import be.pokemon.implementations.Attack2;
import be.pokemon.implementations.Evolve;

public class Wartortle extends be.pokemon.Pokemon implements Attack1, Attack2, Evolve {

    private double damage;
    private int evolutionStage;

    public Wartortle(String name, int level, double height, double HP, int XP) {
        super(name, level, height, HP, XP);
        this.evolutionStage = 2;
    }

    public double aquaJet() {
        if (this.power > 0) {
            double result = this.level + (this.height * 0.33);
            this.damage = Double.parseDouble(String.format(Locale.US, "%.2f", result));
            this.power -= 1;
            this.XP += 30;
            System.err.println("You did " + this.damage + " damage!");
            return this.damage;
        } else {
            System.out.println("Not enough power to attack!");
            return 0;
        }
    }

    public double rapidSpin() {
        if (this.power >= 3) {
            double result = (this.level * 3.2) + this.height;
            this.damage = Double.parseDouble(String.format(Locale.US, "%.2f", result));
            this.power -= 3;
            this.XP += 70;
            System.err.println("You did " + this.damage + " damage!");
            return this.damage;
        } else {
            System.out.println("Not enough power to attack!");
            return 0;
        }
    }

    public Blastoise evolveToBlastoise() {

        if (this.XP >= 100 && this.level > 50) {
            Blastoise blastoise = new Blastoise(this.name, this.level, this.height, this.HP, this.XP);

            blastoise.setHP(blastoise.getHP() + 30);
            blastoise.setLevel(blastoise.getLevel() + 1);
            blastoise.setHeight(blastoise.getHeight() * 2.6);
            blastoise.setXP(blastoise.getXP() - 100);
            blastoise.setPower(this.power);
            System.out.println(this.name + " has evolved into Blastoise!");

            return blastoise;
        } else if (this.level < 50) {
            System.out.println("You have to be level 50 or higher to evolve to Blastoise!");
            return null;
        } else {
            System.out.println("You need " + (100 - this.XP) + " more XP to evolve!");
            return null;
        }
    }

    public void showXP() {
        System.out.println(this.XP);
    }

    public int getXP(int amount) {
        return this.XP += amount;
    }

    @Override
    public double doAttackOne() {
        return aquaJet();
    }

    @Override
    public double doAttackTwo() {
        return rapidSpin();
    }

    @Override
    public void evolvePokemon() {
        if (this.evolutionStage == 2) {
            this.evolutionStage = 3;
            evolveToBlastoise();
        } else if (this.evolutionStage == 3) {
            System.out.println("Can not evolve this Pokémon further!");
        }
    }
}
