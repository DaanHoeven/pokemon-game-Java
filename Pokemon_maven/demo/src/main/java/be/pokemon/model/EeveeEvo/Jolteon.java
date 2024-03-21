package be.pokemon.model.EeveeEvo;

import java.util.Locale;

import com.fasterxml.jackson.annotation.JsonTypeName;

import be.pokemon.implementations.Attack1;
import be.pokemon.implementations.Attack2;
import be.pokemon.implementations.Evolve;

@JsonTypeName("pR2e9T")
public class Jolteon extends be.pokemon.Pokemon implements Attack1, Attack2, Evolve {

    private double damage;

    public Jolteon(String name, String type, String element, int level, double height, double hp, int xp) {
        super(name, type, element, level, height, hp, xp);
    }

    public String getId() {
        return "pR2e9T";
    }

    public double doubleKick() {
        if (this.getPower() > 0) {
            double result = this.getLevel() + (this.height * 0.49);
            this.damage = Double.parseDouble(String.format(Locale.US, "%.2f", result));
            this.power -= 1;
            System.err.println("You did " + this.damage + " damage!");
            return this.damage;
        } else {
            System.out.println("Not enough power to attack!");
            return 0;
        }
    }

    public double thunderbolt() {
        if (this.power >= 3) {
            double result = (this.level * 3.9) + this.height;
            this.damage = Double.parseDouble(String.format(Locale.US, "%.2f", result));
            this.power -= 3;
            System.err.println("You did " + this.damage + " damage!");
            return this.damage;
        } else {
            System.out.println("Not enough power to attack!");
            return 0;
        }
    }

    @Override
    public double doAttackOne() {
        return doubleKick();
    }

    @Override
    public double doAttackTwo() {
        return thunderbolt();
    }

    @Override
    public void evolvePokemon() {
        System.out.println("Can not evolve this Pokémon!");
    }
}
