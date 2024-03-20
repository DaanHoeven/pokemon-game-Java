package be.pokemon.model.ZubatEvo;

import java.util.Locale;

import be.pokemon.model.SquirtleEvo.Wartortle;

import be.pokemon.implementations.Evolve;

public class Crobat extends be.pokemon.Pokemon implements Evolve {
    private double damage;

    public Crobat(String name, int level, double height, double HP, int XP) {
        super(name, level, height, HP, XP);
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

    public Wartortle evolveToWartortle() {

        if (this.XP >= 50 && this.level >= 25) {
            Wartortle wartortle = new Wartortle(this.name, this.level, this.height, this.HP, this.XP);

            wartortle.setHP(wartortle.getHP() + 30);
            wartortle.setLevel(wartortle.getLevel() + 1);
            wartortle.setHeight(wartortle.getHeight() * 2.5);
            wartortle.setXP(wartortle.getXP() - 50);
            wartortle.setPower(this.power);
            System.out.println(this.name + " has evolved into Wartortle!");

            return wartortle;
        } else if (this.level < 25) {
            System.out.println("You have to be level 25 or higher to evolve to Wartorle!");
            return null;
        } else {
            System.out.println("You need " + (50 - this.XP) + " to evolve to Wartortle!");
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

            crobat.HP += 30;
            crobat.level += 1;
            crobat.height *= 2.5;
            crobat.XP -= 50;
            crobat.power = this.power;
            System.out.println(this.name + " has evolved into Crobat!");

            this.name = crobat.name;
            this.level = crobat.level;
            this.height = crobat.height;
            this.HP = crobat.HP;
            this.XP = crobat.XP;
            this.power = crobat.power;
        } else {
            System.out.println("Not enough XP or level to evolve Squirtle to Crobat!");
        }
    }

    @Override
    public void evolvePokemon() {
        System.out.println("Can not evolve this Pokémon!");
    }
}
