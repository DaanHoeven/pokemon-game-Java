package be.pokemon.model.SquirtleEvo;

import java.util.Locale;

import com.fasterxml.jackson.annotation.JsonTypeName;

import be.pokemon.implementations.Attack1;
import be.pokemon.implementations.Attack2;
import be.pokemon.implementations.Evolve;

@JsonTypeName("iR3O0A")
public class Squirtle extends be.pokemon.Pokemon implements Attack1, Attack2, Evolve {

    private double damage;
    private int evolutionStage;

    public Squirtle(String name, String type, String element, int level, double height, double hp, int xp) {
        super(name, type, element, level, height, hp, xp);
        this.evolutionStage = 1;
    }

    public String getId() {
        return "iR3O0A";
    }

    public double bubble() {
        if (this.power > 0) {
            double result = this.level + (this.height * 0.33);
            this.damage = Double.parseDouble(String.format(Locale.US, "%.2f", result));
            this.power -= 1;
            this.xp += 30;
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
            this.xp += 70;
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
        if (this.xp >= 50 && this.level >= 25) {
            Wartortle wartortle = new Wartortle(this.getName(), this.getType(), this.getElement(), this.getLevel(),
                    this.getHeight(), this.gethp(),
                    this.getxp());

            wartortle.sethp(wartortle.gethp() + 30);
            wartortle.setLevel(wartortle.getLevel() + 1);
            wartortle.setHeight(wartortle.getHeight() * 2.5);
            wartortle.setxp(wartortle.getxp() - 50);
            wartortle.setPower(this.power);
            System.out.println(this.name + " has evolved into Wartortle!");

            this.name = wartortle.getName();
            this.level = wartortle.getLevel();
            this.height = wartortle.getHeight();
            this.hp = wartortle.gethp();
            this.xp = wartortle.getxp();
            this.power = wartortle.getPower();
        } else {
            System.out.println("Not enough xp or level to evolve Squirtle to Wartortle!");
        }
    }

    public void evolveToBlastoise() {
        if (this.xp >= 50 && this.level >= 25) {
            Blastoise blastoise = new Blastoise(this.getName(), this.getType(), this.getElement(), this.getLevel(),
                    this.getHeight(), this.gethp(),
                    this.getxp());

            blastoise.sethp(blastoise.gethp() + 30);
            blastoise.setLevel(blastoise.getLevel() + 1);
            blastoise.setHeight(blastoise.getHeight() * 2.5);
            blastoise.setxp(blastoise.getxp() - 50);
            blastoise.setPower(this.power);
            System.out.println(this.name + " has evolved into Blastoise!");

            this.name = blastoise.getName();
            this.level = blastoise.getLevel();
            this.height = blastoise.getHeight();
            this.hp = blastoise.gethp();
            this.xp = blastoise.getxp();
            this.power = blastoise.getPower();
        } else {
            System.out.println("Not enough xp or level to evolve Squirtle to Wartortle!");
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
