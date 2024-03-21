package be.pokemon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import be.pokemon.model.EeveeEvo.Eevee;
import be.pokemon.model.SquirtleEvo.Squirtle;
import be.pokemon.model.SquirtleEvo.Wartortle;
import be.pokemon.model.ZubatEvo.Zubat;

@SpringBootApplication
public abstract class PokemonApplication {
    public static void main(String[] args) {
        SpringApplication.run(PokemonApplication.class, args);

        Eevee Eevee1 = new Eevee("Daan", "normal", 200, 60.12, 1220, 2000);
        Squirtle Squirtle1 = new Squirtle("Bert", "water", 26, 51.87, 122, 2000);
        Squirtle Squirtle2 = new Squirtle("Bertrand", "water", 38, 57.87, 1129, 2080);
        Wartortle Wartortle1 = new Wartortle("Gevechtschilpad", "water", 120, 120, 800, 3000);
        Zubat Zubat1 = new Zubat("Zuiger", "poison / flying", 100, 30, 200, 1000);

        Eevee1.powerUpByNumber(3000);
        Squirtle2.powerUpByNumber(10);
        Wartortle1.powerUpByNumber(20);
        Zubat1.powerUpByNumber(300);
        Squirtle1.powerUpByNumber(21);

        System.out.println(Eevee1.getType());

        // Zubat1.fight(Squirtle1);
    }
}