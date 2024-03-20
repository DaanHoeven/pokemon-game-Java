package be.pokemon.model.EeveeEvo;

import java.util.Locale;

import be.pokemon.implementations.Attack1;
import be.pokemon.implementations.Attack2;
import be.pokemon.implementations.Evolve;

public class Eevee extends be.pokemon.Pokemon implements Attack1, Attack2, Evolve {

    private double damage;
    private int evolutionStage;

    public Eevee(String name, int level, double height, double HP, int XP) {
        super(name, level, height, HP, XP);
        this.evolutionStage = 1;
    }

    public double tailWhip() {
        if (this.getPower() > 0) {
            double result = this.getLevel() + (this.getHeight() * 0.33);
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

    public double quickAttack() {
        if (this.getPower() >= 3) {
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

    public Jolteon evolveToJolteon() {

        if (this.getXP() >= 50) {
            Jolteon jolteon = new Jolteon(this.getName(), this.getLevel(), this.getHeight(), this.getHP(),
                    this.getXP());

            jolteon.setHP(jolteon.getHP() + 30);
            jolteon.setLevel(jolteon.getLevel() + 1);
            jolteon.setHeight(jolteon.getHeight() * 1.4);
            jolteon.setXP(jolteon.getXP() - 50);
            System.out.println(this.getName() + " has evolved into Jolteon!");

            return jolteon;
        } else {
            System.out.println("Not enough XP to evolve to Jolteon!");
            return null;
        }
    }

    public int getXP(int amount) {
        return this.XP += amount;
    }

    @Override
    public double doAttackOne() {
        return this.tailWhip();
    }

    @Override
    public double doAttackTwo() {
        return this.quickAttack();
    }

    @Override
    public void evolvePokemon() {
        if (this.evolutionStage == 1) {
            this.evolutionStage = 2;
            evolveToJolteon();
        } else if (this.evolutionStage == 2) {
            System.out.println("Can not evolve this Pokémon further!");
        }
    }
}