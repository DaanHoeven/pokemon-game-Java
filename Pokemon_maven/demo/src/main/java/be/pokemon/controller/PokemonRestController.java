package be.pokemon.controller;

import be.pokemon.Pokemon;

import be.pokemon.service.PokemonService;
import be.pokemon.service.ServiceException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pokemons")
public class PokemonRestController {
    private PokemonService pokemonService;

    public PokemonRestController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping
    public List<Pokemon> getAllPokemons(@RequestParam(required = false) String name) {
        if (name == null) {
            return pokemonService.getAllPokemons();
        } else {
            return pokemonService.pokemonByName(name);
        }
    }

    @PostMapping
    public ResponseEntity<String> addPokemon(@RequestBody Pokemon pokemon) {
        try {
            Pokemon newPokemon = Pokemon.createPokemon(pokemon.getName(), pokemon.getType(), pokemon.getElement(),
                    pokemon.getLevel(), pokemon.getHeight(), pokemon.gethp(), pokemon.getxp(), pokemon.getId());
            pokemonService.addPokemon(newPokemon);
            return ResponseEntity.ok(newPokemon.getType() + " added successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<String> deletePokemonByName(@PathVariable String name) {
        try {
            pokemonService.deletePokemonByName(name);
            return ResponseEntity.ok("Pokemon with name " + name + " deleted successfully");
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
