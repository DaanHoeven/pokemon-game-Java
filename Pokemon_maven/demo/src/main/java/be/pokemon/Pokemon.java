package be.pokemon;

import java.util.Scanner;

import be.pokemon.model.DomainException;
import be.pokemon.model.EeveeEvo.Eevee;
import be.pokemon.model.EeveeEvo.Jolteon;
import be.pokemon.model.SquirtleEvo.Blastoise;
import be.pokemon.model.SquirtleEvo.Squirtle;
import be.pokemon.model.SquirtleEvo.Wartortle;
import be.pokemon.model.ZubatEvo.Crobat;
import be.pokemon.model.ZubatEvo.Golbat;
import be.pokemon.model.ZubatEvo.Zubat;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "typeOfPokemon")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Eevee.class, name = "eevee"),
        @JsonSubTypes.Type(value = Jolteon.class, name = "jolteon"),
        @JsonSubTypes.Type(value = Blastoise.class, name = "blastoise"),
        @JsonSubTypes.Type(value = Squirtle.class, name = "squirtle"),
        @JsonSubTypes.Type(value = Wartortle.class, name = "wartortle"),
        @JsonSubTypes.Type(value = Crobat.class, name = "crobat"),
        @JsonSubTypes.Type(value = Zubat.class, name = "zubat"),
        @JsonSubTypes.Type(value = Golbat.class, name = "golbat")
})

public abstract class Pokemon {
    protected String name;
    protected String element;
    protected int level;
    protected double height;
    protected double hp;
    protected int xp;
    protected int power;
    private String type;

    public Pokemon(String name, String element, int level, double height, double hp, int xp) {
        setName(name);
        setElement(element);
        setLevel(level);
        setHeight(height);
        sethp(hp);
        setxp(xp);
        setPower(power);
        this.type = this.getClass().getAnnotation(JsonTypeName.class).value();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new DomainException("Name cannot be null or empty");
        }
        this.name = name;
    }

    public String getElement() {
        return this.element;
    }

    public void setElement(String element) {
        if (element == null || element.isEmpty()) {
            throw new DomainException("Element cannot be null or empty");
        }
        this.element = element;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        if (level <= 0) {
            throw new DomainException("Level must be a positive integer");
        }
        this.level = level;
    }

    public double getHeight() {
        return this.height;
    }

    public void setHeight(double height) {
        if (height <= 0) {
            throw new DomainException("Height must be a positive number");
        }
        this.height = height;
    }

    public double gethp() {
        return this.hp;
    }

    public void sethp(Double hp) {
        if (hp == null || hp <= 0) {
            this.hp = 1.0;
        } else {
            this.hp = hp;
        }
    }

    public int getxp() {
        return this.xp;
    }

    public void setxp(int xp) {
        if (xp <= 0) {
            throw new DomainException("xp must be a positive integer");
        }
        this.xp = xp;
    }

    public int getPower() {
        return this.power;
    }

    public void setPower(int power) {
        if (power < 0) {
            throw new DomainException("Power cannot be negative");
        }
        this.power = power;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (!isValidType(type)) {
            throw new DomainException("Invalid Pokemon type: " + type);
        }
        this.type = type;
    }

    private boolean isValidType(String type) {
        return type != null && !type.isEmpty() &&
                ("eevee".equalsIgnoreCase(type) || "jolteon".equalsIgnoreCase(type) ||
                        "blastoise".equalsIgnoreCase(type) || "squirtle".equalsIgnoreCase(type) ||
                        "wartortle".equalsIgnoreCase(type) || "crobat".equalsIgnoreCase(type) ||
                        "zubat".equalsIgnoreCase(type) || "golbat".equalsIgnoreCase(type));
    }

    public void takeDamage(double damage) {
        if (damage <= 0) {
            throw new DomainException("Damage must be a positive number");
        }

        this.hp -= damage;
        if (this.hp < 0) {
            this.hp = 0;
        }
        System.out.println(this.name + " remaining hp: " + this.hp);
        if (this.hp == 0) {
            System.out.println(this.name + " fainted!");
        }
    }

    public int levelUpByNumber(int levels) {
        if (levels <= 0) {
            throw new DomainException("Number of levels to increase must be positive");
        }
        return this.level += levels;
    }

    public int powerUpByNumber(int power) {
        if (power <= 0) {
            throw new DomainException("Power increase must be positive");
        }
        return this.power += power;
    }

    public int powerUp() {
        return this.power++;
    }

    public void rest() {
        double gainhp = this.hp * 0.4;
        String formattedGainhp = String.format("%.2f", gainhp).replace(',', '.');
        System.out.println(this.getName() + " used rest! It gained +2 power and " + formattedGainhp + "hp");
        this.powerUp();
        this.powerUp();
        this.hp += gainhp;
    }

    public double doAttackOne() {
        return 0.0;
    }

    public double doAttackTwo() {
        return 0.0;
    }

    public abstract void evolvePokemon();

    public int getUserAttackChoice(Scanner scanner) {
        int choice = 0;
        do {
            System.out.println(
                    "Choose your attack (1 for Attack 1 (cost = 1 power), 2 for Attack 2 (cost = 3 power), 3 for Rest (cost = 0 power), 4 for evolution (cost = 20 power)): ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter 1, 2, 3, or 4.");
                scanner.next();
            }
            choice = scanner.nextInt();
        } while (choice != 1 && choice != 2 && choice != 3 && choice != 4);

        return choice;
    }

    public void fight(Pokemon opponent) {
        boolean isPlayer1Turn = true;
        Scanner scanner = new Scanner(System.in);
        this.power += 5;
        opponent.power += 5;

        while (this.gethp() > 0 && opponent.gethp() > 0) {
            System.out.println("-------------------");
            if (isPlayer1Turn) {
                System.out.println(this.getName() + "'s turn:");
                System.out.println("Current Power: " + this.power);
                int attackChoice = getUserAttackChoice(scanner);
                if (attackChoice == 1 || attackChoice == 2) {
                    double damageDealt = (attackChoice == 1) ? this.doAttackOne() : this.doAttackTwo();
                    opponent.takeDamage(damageDealt);
                } else if (attackChoice == 3) {
                    this.rest();
                } else if (attackChoice == 4 && this.power >= 20) {
                    evolvePokemon();
                } else if (attackChoice == 4 && this.power < 20) {
                    System.out.println("Not enough power to evolve!");
                }
            } else {
                System.out.println(opponent.getName() + "'s turn:");
                System.out.println("Current Power: " + opponent.power);
                int opponentAttackChoice = opponent.getUserAttackChoice(scanner);
                if (opponentAttackChoice == 1 || opponentAttackChoice == 2) {
                    double damageDealt = (opponentAttackChoice == 1) ? opponent.doAttackOne() : opponent.doAttackTwo();
                    this.takeDamage(damageDealt);
                } else if (opponentAttackChoice == 3) {
                    opponent.rest();
                } else if (opponentAttackChoice == 4 && opponent.power >= 20) {
                    opponent.evolvePokemon();
                } else if (opponentAttackChoice == 4 && opponent.power < 20) {
                    System.out.println("Not enough power to evolve!");
                }
            }

            isPlayer1Turn = !isPlayer1Turn;
        }
    }

}