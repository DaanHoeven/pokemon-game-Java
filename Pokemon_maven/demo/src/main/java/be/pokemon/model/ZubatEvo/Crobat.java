package be.pokemon.model.ZubatEvo;

import java.util.Locale;

import com.fasterxml.jackson.annotation.JsonTypeName;

import be.pokemon.implementations.Evolve;

@JsonTypeName("crobat")
public class Crobat extends be.pokemon.Pokemon implements Evolve {
    private double damage;

    public Crobat(String name, String element, int level, double height, double hp, int xp) {
        super(name, element, level, height, hp, xp);
    }

    public double echoBeam() {
        if (this.power > 0) {
            double result = this.level + (this.height * 1.80);
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

    public double lifeSteal() {
        if (this.power >= 3) {
            double result = (this.level * 1.8) + (this.height * 3);
            this.damage = Double.parseDouble(String.format(Locale.US, "%.2f", result));
            this.power -= 3;
            this.xp += 70;
            this.hp *= 1.7;
            System.err.println("You did " + this.damage + " damage!");
            return this.damage;
        } else {
            System.out.println("Not enough power to attack!");
            return 0;
        }
    }

    @Override
    public double doAttackOne() {
        return echoBeam();
    }

    @Override
    public double doAttackTwo() {
        return lifeSteal();
    }

    public void evolveToCrobat() {
        if (this.xp >= 50 && this.level >= 25) {
            Crobat crobat = new Crobat(this.name, this.element, this.level, this.height, this.hp, this.xp);

            crobat.hp += 30;
            crobat.level += 1;
            crobat.height *= 2.5;
            crobat.xp -= 50;
            crobat.power = this.power;
            System.out.println(this.name + " has evolved into Crobat!");

            this.name = crobat.name;
            this.level = crobat.level;
            this.height = crobat.height;
            this.hp = crobat.hp;
            this.xp = crobat.xp;
            this.power = crobat.power;
        } else {
            System.out.println("Not enough xp or level to evolve Golbat to Crobat!");
        }
    }

    @Override
    public void evolvePokemon() {
        System.out.println("Can not evolve this Pokémon!");
    }
}
