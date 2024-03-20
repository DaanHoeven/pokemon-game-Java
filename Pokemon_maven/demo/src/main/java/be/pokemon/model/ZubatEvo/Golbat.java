package be.pokemon.model.ZubatEvo;

import java.util.Locale;

import be.pokemon.implementations.Attack1;
import be.pokemon.implementations.Attack2;
import be.pokemon.implementations.Evolve;

public class Golbat extends be.pokemon.Pokemon implements Attack1, Attack2, Evolve {
    private double damage;
    private int evolutionStage;

    public Golbat(String name, int level, double height, double HP, int XP) {
        super(name, level, height, HP, XP);
        this.evolutionStage = 2;
    }

    public double echoBeam() {
        if (this.power > 0) {
            double result = this.level + (this.height * 1.80);
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

    public double lifeSteal() {
        if (this.power >= 3) {
            double result = (this.level * 1.8) + (this.height * 3);
            this.damage = Double.parseDouble(String.format(Locale.US, "%.2f", result));
            this.power -= 3;
            this.XP += 70;
            this.HP *= 1.7;
            System.err.println("You did " + this.damage + " damage!");
            return this.damage;
        } else {
            System.out.println("Not enough power to attack!");
            return 0;
        }
    }

    public Crobat evolveToWartortle() {

        if (this.XP >= 500 && this.level >= 50) {
            Crobat crobat = new Crobat(this.name, this.level, this.height, this.HP, this.XP);

            crobat.setHP(crobat.getHP() + 30);
            crobat.setLevel(crobat.getLevel() + 1);
            crobat.setHeight(crobat.getHeight() * 2.5);
            crobat.setXP(crobat.getXP() - 50);
            crobat.setPower(this.power);
            System.out.println(this.name + " has evolved into Crobat!");

            return crobat;
        } else if (this.level < 25) {
            System.out.println("You have to be level 25 or higher to evolve to Crobat!");
            return null;
        } else {
            System.out.println("You need " + (50 - this.XP) + " to evolve to Crobat!");
            return null;
        }
    }

    public int getXP(int amount) {
        return this.XP += amount;
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
        if (this.XP >= 50 && this.level >= 25) {
            Crobat crobat = new Crobat(this.name, this.level, this.height, this.HP, this.XP);

            crobat.setHP(crobat.getHP() + 30);
            crobat.setLevel(crobat.getLevel() + 1);
            crobat.setHeight(crobat.getHeight() * 2.5);
            crobat.setXP(crobat.getXP() - 50);
            crobat.setPower(this.power);
            System.out.println(this.name + " has evolved into Crobat!");

            this.name = crobat.getName();
            this.level = crobat.getLevel();
            this.height = crobat.getHeight();
            this.HP = crobat.getHP();
            this.XP = crobat.getXP();
            this.power = crobat.getPower();
        } else {
            System.out.println("Not enough XP or level to evolve Golbat to Crobat!");
        }
    }

    @Override
    public void evolvePokemon() {
        if (this.evolutionStage == 2) {

            evolveToCrobat();
            this.evolutionStage = 3;
        } else if (this.evolutionStage == 3) {
            System.out.println("Can not evolve this Pokémon further!");
        }
    }
}
