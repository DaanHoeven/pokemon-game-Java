package be.pokemon.controller;

import be.pokemon.Pokemon;
import be.pokemon.service.PokemonService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PokemonRestController {
    private PokemonService pokemonService;

    public PokemonRestController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping("/pokemons")
    public List<Pokemon> getAllPokemons() {
        return pokemonService.getAllPokemons();
    }
}
