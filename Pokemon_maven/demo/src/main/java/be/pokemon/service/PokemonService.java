package be.pokemon.service;

import be.pokemon.Pokemon;
import be.pokemon.model.DomainException;
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

    public void addPokemon(Pokemon pokemon) {
        Pokemon existingPokemon = pokemonRepository.getPokemonByName(pokemon.getName());
        if (existingPokemon != null) {
            throw new DomainException("Pokemon already exists");
        } else {
            pokemonRepository.addPokemon(pokemon);
        }
    }

    public void deletePokemonByName(String name) {
        if (!pokemonExists(name)) {
            throw new ServiceException("Pokemon with name " + name + " does not exist");
        }
        pokemonRepository.deletePokemonByName(name);
    }

    public List<Pokemon> pokemonByName(String name) {
        List<Pokemon> pokemonList = pokemonRepository.pokemonsByName(name);
        if (pokemonList.size() == 0) {
            throw new ServiceException("There are no pokemons with this name.");
        } else {
            return pokemonList;
        }
    }

    public boolean pokemonExists(String name) {
        try {
            return pokemonRepository.pokemonExists(name);
        } catch (Exception e) {
            throw new ServiceException("There are no such pokemon in the repository.");
        }
    }
}
