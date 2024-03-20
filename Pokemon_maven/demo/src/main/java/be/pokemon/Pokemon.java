package be.pokemon;

import java.util.Scanner;

public abstract class Pokemon {
    protected String name;
    protected int level;
    protected double height;
    protected double HP;
    protected int XP;
    protected int power;

    public Pokemon(String name, int level, double height, double HP, int XP) {
        setName(name);
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
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getHP() {
        return HP;
    }

    public void setHP(double HP) {
        this.HP = HP;
    }

    public int getXP() {
        return XP;
    }

    public void setXP(int XP) {
        this.XP = XP;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void takeDamage(double damage) {
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
        return this.level += levels;
    }

    public int powerUpByNumber(int power) {
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