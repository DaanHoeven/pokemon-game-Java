package be.pokemon.service;

import be.pokemon.Pokemon;
import be.pokemon.repository.PokemonRepository;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class PokemonService {
    private PokemonRepository pokemonRepository;

    public PokemonService(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    public List<Pokemon> getAllPokemons() {
        return pokemonRepository.getAllPokemons();
    }
}
