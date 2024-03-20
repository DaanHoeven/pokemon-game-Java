package be.pokemon.model.ZubatEvo;

import java.util.Locale;

import be.pokemon.model.SquirtleEvo.Wartortle;

public class Zubat extends be.pokemon.Pokemon {
    private double damage;
    private int evolutionStage;

    public Zubat(String name, int level, double height, double HP, int XP) {
        super(name, level, height, HP, XP);
        this.evolutionStage = 1;
    }

    public double bite() {
        if (this.power > 0) {
            double result = this.level + (this.height * 1.50);
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
            double result = (this.level * 1.6) + (this.height * 2.8);
            this.damage = Double.parseDouble(String.format(Locale.US, "%.2f", result));
            this.power -= 3;
            this.XP += 70;
            this.HP *= 1.3;
            System.err.println("You did " + this.damage + " damage!");
            return this.damage;
        } else {
            System.out.println("Not enough power to attack!");
            return 0;
        }
    }

    public void evolveToGolbat() {
        if (this.XP >= 50 && this.level >= 25) {
            Wartortle wartortle = new Wartortle(this.name, this.level, this.height, this.HP, this.XP);

            wartortle.setHP(wartortle.getHP() + 30);
            wartortle.setLevel(wartortle.getLevel() + 1);
            wartortle.setHeight(wartortle.getHeight() * 2.5);
            wartortle.setXP(wartortle.getXP() - 50);
            wartortle.setPower(this.power);
            System.out.println(this.name + " has evolved into Golbat!");

            this.name = wartortle.getName();
            this.level = wartortle.getLevel();
            this.height = wartortle.getHeight();
            this.HP = wartortle.getHP();
            this.XP = wartortle.getXP();
            this.power = wartortle.getPower();
        } else {
            System.out.println("Not enough XP or level to evolve Squirtle to Golbat!");
        }
    }

    public int getXP(int amount) {
        return this.XP += amount;
    }

    @Override
    public double doAttackOne() {
        return bite();
    }

    @Override
    public double doAttackTwo() {
        return lifeSteal();
    }

    @Override
    public void evolvePokemon() {
        if (this.evolutionStage == 1) {
            evolveToGolbat();
            this.evolutionStage = 2;

        } else if (this.evolutionStage == 2) {
            evolveToGolbat();
            this.evolutionStage = 3;

        } else if (this.evolutionStage == 3) {

            System.out.println("Can not evolve this Pokémon further!");
        }
    }
}