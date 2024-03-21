package be.pokemon.model.ZubatEvo;

import java.util.Locale;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("H7rT5g")
public class Zubat extends be.pokemon.Pokemon {
    private double damage;
    private int evolutionStage;

    public Zubat(String name, String type, String element, int level, double height, double hp, int xp) {
        super(name, type, element, level, height, hp, xp);
    }

    public String getId() {
        return "H7rT5g";
    }

    public double bite() {
        if (this.power > 0) {
            double result = this.level + (this.height * 1.50);
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

    public double lifeSteal() {
        if (this.power >= 3) {
            double result = (this.level * 1.6) + (this.height * 2.8);
            this.damage = Double.parseDouble(String.format(Locale.US, "%.2f", result));
            this.power -= 3;
            this.xp += 70;
            this.hp *= 1.3;
            System.err.println("You did " + this.damage + " damage!");
            return this.damage;
        } else {
            System.out.println("Not enough power to attack!");
            return 0;
        }
    }

    public void evolveToGolbat() {
        if (this.xp >= 50 && this.level >= 25) {
            Golbat golbat = new Golbat(this.getName(), this.getType(), this.getElement(), this.getLevel(),
                    this.getHeight(), this.gethp(),
                    this.getxp());

            golbat.sethp(golbat.gethp() + 30);
            golbat.setLevel(golbat.getLevel() + 1);
            golbat.setHeight(golbat.getHeight() * 2.5);
            golbat.setxp(golbat.getxp() - 50);
            golbat.setPower(this.power);
            System.out.println(this.name + " has evolved into Golbat!");

            this.name = golbat.getName();
            this.level = golbat.getLevel();
            this.height = golbat.getHeight();
            this.hp = golbat.gethp();
            this.xp = golbat.getxp();
            this.power = golbat.getPower();
        } else {
            System.out.println("Not enough xp or level to evolve Zubat to Golbat!");
        }
    }

    public void evolveToCrobat() {
        if (this.xp >= 50 && this.level >= 25) {
            Crobat crobat = new Crobat(this.getName(), this.getType(), this.getElement(), this.getLevel(),
                    this.getHeight(), this.gethp(),
                    this.getxp());

            crobat.sethp(crobat.gethp() + 30);
            crobat.setLevel(crobat.getLevel() + 1);
            crobat.setHeight(crobat.getHeight() * 2.5);
            crobat.setxp(crobat.getxp() - 50);
            crobat.setPower(this.power);
            System.out.println(this.name + " has evolved into Crobat!");

            this.name = crobat.getName();
            this.level = crobat.getLevel();
            this.height = crobat.getHeight();
            this.hp = crobat.gethp();
            this.xp = crobat.getxp();
            this.power = crobat.getPower();
        } else {
            System.out.println("Not enough xp or level to evolve Golbat to Crobat!");
        }
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
