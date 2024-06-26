package be.pokemon.model.SquirtleEvo;

import java.util.Locale;

import com.fasterxml.jackson.annotation.JsonTypeName;

import be.pokemon.implementations.Attack1;
import be.pokemon.implementations.Attack2;
import be.pokemon.implementations.Evolve;

@JsonTypeName("5dFj8Q")
public class Blastoise extends be.pokemon.Pokemon implements Attack1, Attack2, Evolve {

    private double damage;

    public Blastoise(String name, String type, String element, int level, double height, double hp, int xp) {
        super(name, type, element, level, height, hp, xp);
    }

    public String getId() {
        return "pR2e9T";
    }

    public void growl() {
        System.out.println(this.name + "Bwaaaaarggg");
    }

    public double bite() {
        if (this.power > 0) {
            double result = this.level + (this.height * 0.20);
            this.damage = Double.parseDouble(String.format(Locale.US, "%.2f", result));
            this.power -= 1;
            System.err.println("You did " + this.damage + " damage!");
            return this.damage;
        } else {
            System.out.println("Not enough power to attack!");
            return 0;
        }
    }

    public double iceBeam() {
        if (this.power >= 3) {
            double result = (this.level * 2.2) + this.height;
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
        return bite();
    }

    @Override
    public double doAttackTwo() {
        return iceBeam();
    }

    @Override
    public void evolvePokemon() {
        System.out.println("Can not evolve this Pokémon!");
    }
}
