package be.pokemon.model.ZubatEvo;

import java.util.Locale;

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
            Golbat golbat = new Golbat(this.name, this.level, this.height, this.HP, this.XP);

            golbat.setHP(golbat.getHP() + 30);
            golbat.setLevel(golbat.getLevel() + 1);
            golbat.setHeight(golbat.getHeight() * 2.5);
            golbat.setXP(golbat.getXP() - 50);
            golbat.setPower(this.power);
            System.out.println(this.name + " has evolved into Golbat!");

            this.name = golbat.getName();
            this.level = golbat.getLevel();
            this.height = golbat.getHeight();
            this.HP = golbat.getHP();
            this.XP = golbat.getXP();
            this.power = golbat.getPower();
        } else {
            System.out.println("Not enough XP or level to evolve Zubat to Golbat!");
        }
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
            evolveToCrobat();
            this.evolutionStage = 3;

        } else if (this.evolutionStage == 3) {

            System.out.println("Can not evolve this Pokémon further!");
        }
    }
}
