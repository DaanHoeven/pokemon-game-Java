package be.pokemon.model.EeveeEvo;

import java.util.Locale;

import com.fasterxml.jackson.annotation.JsonTypeName;

import be.pokemon.implementations.Attack1;
import be.pokemon.implementations.Attack2;
import be.pokemon.implementations.Evolve;

@JsonTypeName("eevee")
public class Eevee extends be.pokemon.Pokemon implements Attack1, Attack2, Evolve {

    private double damage;
    private int evolutionStage;

    public Eevee(String name, String element, int level, double height, double hp, int xp) {
        super(name, element, level, height, hp, xp);
        this.evolutionStage = 1;
    }

    public double tailWhip() {
        if (this.getPower() > 0) {
            double result = this.getLevel() + (this.getHeight() * 0.33);
            this.damage = Double.parseDouble(String.format(Locale.US, "%.2f", result));
            this.power -= 1;
            this.xp += 30;
            System.out.println("You did " + this.damage + " damage!");
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
            this.xp += 70;
            System.err.println("You did " + this.damage + " damage!");
            return this.damage;
        } else {
            System.out.println("Not enough power to attack!");
            return 0;
        }
    }

    public Jolteon evolveToJolteon() {

        if (this.getxp() >= 50) {
            Jolteon jolteon = new Jolteon(this.getName(), this.getElement(), this.getLevel(),
                    this.getHeight(), this.gethp(),
                    this.getxp());

            jolteon.sethp(jolteon.gethp() + 30);
            jolteon.setLevel(jolteon.getLevel() + 1);
            jolteon.setHeight(jolteon.getHeight() * 1.4);
            jolteon.setxp(jolteon.getxp() - 50);
            System.out.println(this.getName() + " has evolved into Jolteon!");

            return jolteon;
        } else {
            System.out.println("Not enough xp to evolve to Jolteon!");
            return null;
        }
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
            this.element = "electricity";
            evolveToJolteon();
        } else if (this.evolutionStage == 2) {
            System.out.println("Can not evolve this Pokémon further!");
        }
    }
}