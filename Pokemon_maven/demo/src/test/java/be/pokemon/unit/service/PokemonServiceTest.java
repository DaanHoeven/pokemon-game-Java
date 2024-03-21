package be.pokemon.unit.service;

import be.pokemon.model.DomainException;
import be.pokemon.model.EeveeEvo.Eevee;
import be.pokemon.repository.PokemonRepository;
import be.pokemon.service.PokemonService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PokemonServiceTest {

    private PokemonRepository pokemonRepository;
    private PokemonService pokemonService;

    @BeforeEach
    void setUp() {
        pokemonRepository = new PokemonRepository();
        pokemonService = new PokemonService(pokemonRepository);
    }

    @Test
    void givenNewPokemon_whenAddPokemon_thenPokemonAddedSuccessfully() {
        Eevee newPokemon = new Eevee("Daan", "normal", 200, 60.12, 1220, 2000);

        pokemonService.addPokemon(newPokemon);

        assertTrue(pokemonService.getAllPokemons().contains(newPokemon));
    }

    @Test
    void givenExistingPokemon_whenAddPokemon_thenThrowDomainException() {
        Eevee existingPokemon = new Eevee("Daan", "normal", 200, 60.12, 1220, 2000);
        pokemonRepository.addPokemon(existingPokemon);

        DomainException exception = assertThrows(DomainException.class,
                () -> pokemonService.addPokemon(existingPokemon));
        assertEquals("Pokemon already exists", exception.getMessage());
    }

    @Test
    void givenExistingPokemon_whenPokemonExists_thenReturnTrue() {
        Eevee existingPokemon = new Eevee("Pikachu", "normal", 200, 60.12, 1220, 2000);
        pokemonRepository.addPokemon(existingPokemon);

        boolean result = pokemonService.pokemonExists("Pikachu", "electric");

        assertTrue(result);
    }

    @Test
    void givenNonExistingPokemon_whenPokemonExists_thenReturnFalse() {
        Eevee existingPokemon = new Eevee("Daan", "normal", 200, 60.12, 1220, 2000);
        pokemonRepository.addPokemon(existingPokemon);

        boolean result = pokemonService.pokemonExists("Charmander", "fire");

        assertFalse(result);
    }
}
