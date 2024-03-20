package be.pokemon.model.SquirtleEvo;

import java.util.Locale;

import be.pokemon.implementations.Attack1;
import be.pokemon.implementations.Attack2;
import be.pokemon.implementations.Evolve;

public class Squirtle extends be.pokemon.Pokemon implements Attack1, Attack2, Evolve {

    private double damage;
    private int evolutionStage;

    public Squirtle(String name, int level, double height, double HP, int XP) {
        super(name, level, height, HP, XP);
        this.evolutionStage = 1;
    }

    public double bubble() {
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

    public double aquaTail() {
        if (this.power >= 3) {
            double result = (this.level * 1.6) + this.height;
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

    @Override
    public double doAttackOne() {
        return bubble();
    }

    @Override
    public double doAttackTwo() {
        return aquaTail();
    }

    public void evolveToWartortle() {
        if (this.XP >= 50 && this.level >= 25) {
            Wartortle wartortle = new Wartortle(this.name, this.level, this.height, this.HP, this.XP);

            wartortle.setHP(wartortle.getHP() + 30);
            wartortle.setLevel(wartortle.getLevel() + 1);
            wartortle.setHeight(wartortle.getHeight() * 2.5);
            wartortle.setXP(wartortle.getXP() - 50);
            wartortle.setPower(this.power);
            System.out.println(this.name + " has evolved into Wartortle!");

            this.name = wartortle.getName();
            this.level = wartortle.getLevel();
            this.height = wartortle.getHeight();
            this.HP = wartortle.getHP();
            this.XP = wartortle.getXP();
            this.power = wartortle.getPower();
        } else {
            System.out.println("Not enough XP or level to evolve Squirtle to Wartortle!");
        }
    }

    public void evolveToBlastoise() {
        if (this.XP >= 50 && this.level >= 25) {
            Blastoise blastoise = new Blastoise(this.name, this.level, this.height, this.HP, this.XP);

            blastoise.setHP(blastoise.getHP() + 30);
            blastoise.setLevel(blastoise.getLevel() + 1);
            blastoise.setHeight(blastoise.getHeight() * 2.5);
            blastoise.setXP(blastoise.getXP() - 50);
            blastoise.setPower(this.power);
            System.out.println(this.name + " has evolved into Blastoise!");

            this.name = blastoise.getName();
            this.level = blastoise.getLevel();
            this.height = blastoise.getHeight();
            this.HP = blastoise.getHP();
            this.XP = blastoise.getXP();
            this.power = blastoise.getPower();
        } else {
            System.out.println("Not enough XP or level to evolve Squirtle to Wartortle!");
        }
    }

    @Override
    public void evolvePokemon() {
        if (this.evolutionStage == 1) {
            this.evolutionStage = 2;
            evolveToWartortle();
        } else if (this.evolutionStage == 2) {
            evolveToBlastoise();
            this.evolutionStage = 3;
        } else if (this.evolutionStage == 3) {
            System.out.println("Can not evolve this Pokémon further!");
        }
    }
}
