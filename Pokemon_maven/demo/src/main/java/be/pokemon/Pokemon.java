package be.pokemon;

import java.util.Scanner;

import be.pokemon.model.DomainException;

public abstract class Pokemon {
    protected String name;
    protected String type;
    protected String element;
    protected int level;
    protected double height;
    protected double HP;
    protected int XP;
    protected int power;

    public Pokemon(String name, String type, String element, int level, double height, double HP, int XP) {
        setName(name);
        setType(type);
        setElement(element);
        setLevel(level);
        setHeight(height);
        setHP(HP);
        setXP(XP);
        setPower(XP);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new DomainException("Name cannot be null or empty");
        }
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (type == null || type.isEmpty()) {
            throw new DomainException("Type cannot be null or empty");
        }
        this.type = type;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        if (element == null || element.isEmpty()) {
            throw new DomainException("Element cannot be null or empty");
        }
        this.element = element;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        if (level <= 0) {
            throw new DomainException("Level must be a positive integer");
        }
        this.level = level;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        if (height <= 0) {
            throw new DomainException("Height must be a positive number");
        }
        this.height = height;
    }

    public double getHP() {
        return HP;
    }

    public void setHP(Double HP) {
        if (HP == null || HP <= 0) {
            this.HP = 1.0;
        } else {
            this.HP = HP;
        }
    }

    public int getXP() {
        return XP;
    }

    public void setXP(int XP) {
        if (XP <= 0) {
            throw new DomainException("XP must be a positive integer");
        }
        this.XP = XP;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        if (power < 0) {
            throw new DomainException("Power cannot be negative");
        }
        this.power = power;
    }

    public void takeDamage(double damage) {
        if (damage <= 0) {
            throw new DomainException("Damage must be a positive number");
        }

        this.HP -= damage;
        if (this.HP < 0) {
            this.HP = 0;
        }
        System.out.println(this.name + " remaining HP: " + this.HP);
        if (this.HP == 0) {
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
        double gainHP = this.HP * 0.4;
        String formattedGainHP = String.format("%.2f", gainHP).replace(',', '.');
        System.out.println(this.getName() + " used rest! It gained +2 power and " + formattedGainHP + "HP");
        this.powerUp();
        this.powerUp();
        this.HP += gainHP;
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

        while (this.getHP() > 0 && opponent.getHP() > 0) {
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