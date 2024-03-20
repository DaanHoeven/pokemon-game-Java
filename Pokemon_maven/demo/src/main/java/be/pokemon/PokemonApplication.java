package be.pokemon;

import be.pokemon.model.EeveeEvo.Eevee;
import be.pokemon.model.SquirtleEvo.Squirtle;
import be.pokemon.model.SquirtleEvo.Wartortle;
import be.pokemon.model.ZubatEvo.Zubat;

public abstract class PokemonApplication {
    public static void main(String[] args) {

        Eevee Eevee1 = new Eevee("Daan", 200, 60.12, 1220, 2000);
        Squirtle Squirtle1 = new Squirtle("Bert", 26, 51.87, 122, 2000);
        Squirtle Squirtle2 = new Squirtle("Bertrand", 38, 57.87, 1129, 2080);
        Wartortle Wartorle1 = new Wartortle("Gevechtschilpad", 120, 120, 800, 3000);
        Zubat Zubat1 = new Zubat("Zuiger", 100, 30, 200, 1000);

        Eevee1.powerUpByNumber(3000);
        Squirtle2.powerUpByNumber(0);
        Wartorle1.powerUpByNumber(0);
        Zubat1.powerUpByNumber(300);

        Zubat1.fight(Squirtle1);
    }
}