package be.pokemon.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import be.pokemon.Pokemon;
import be.pokemon.model.EeveeEvo.Eevee;
import be.pokemon.model.SquirtleEvo.Squirtle;
import be.pokemon.model.ZubatEvo.Crobat;

@Repository
public class PokemonRepository {
    private List<Pokemon> pokemons;

    public PokemonRepository() {
        pokemons = new ArrayList<>(List.of(
                new Eevee("Léa", "eevee", "normal", 25, 1.4, 50.0, 700),
                new Squirtle("Daan", "squirtle", "water", 32, 2.6, 60.0, 1220),
                new Crobat("Lucy", "crobat", "poison / flying", 34, 3.5, 45.0, 1100)));
    }

    public List<Pokemon> getAllPokemons() {
        return new ArrayList<>(pokemons);
    }
}
