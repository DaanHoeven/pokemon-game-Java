package be.pokemon.controller;

import be.pokemon.Pokemon;
import be.pokemon.model.EeveeEvo.Eevee;
import be.pokemon.model.EeveeEvo.Jolteon;
import be.pokemon.model.SquirtleEvo.Blastoise;
import be.pokemon.model.SquirtleEvo.Squirtle;
import be.pokemon.model.SquirtleEvo.Wartortle;
import be.pokemon.model.ZubatEvo.Crobat;
import be.pokemon.model.ZubatEvo.Golbat;
import be.pokemon.model.ZubatEvo.Zubat;
import be.pokemon.service.PokemonService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonTypeName;

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
        String pokemonType = pokemon.getClass().getAnnotation(JsonTypeName.class).value();

        if (pokemonType.equalsIgnoreCase("eevee")) {
            Eevee eevee = new Eevee(pokemon.getName(), pokemon.getElement(),
                    pokemon.getLevel(),
                    pokemon.getHeight(), pokemon.gethp(), pokemon.getxp());
            pokemonService.addPokemon(eevee);
            return ResponseEntity.ok("Eevee added successfully");
        } else if (pokemonType.equalsIgnoreCase("jolteon")) {
            Jolteon jolteon = new Jolteon(pokemon.getName(), pokemon.getElement(),
                    pokemon.getLevel(),
                    pokemon.getHeight(), pokemon.gethp(), pokemon.getxp());
            pokemonService.addPokemon(jolteon);
            return ResponseEntity.ok("Jolteon added successfully");
        } else if (pokemonType.equalsIgnoreCase("blastoise")) {
            Blastoise blastoise = new Blastoise(pokemon.getName(), pokemon.getElement(),
                    pokemon.getLevel(),
                    pokemon.getHeight(), pokemon.gethp(), pokemon.getxp());
            pokemonService.addPokemon(blastoise);
            return ResponseEntity.ok("Blastoise added successfully");
        } else if (pokemonType.equalsIgnoreCase("squirtle")) {
            Squirtle squirtle = new Squirtle(pokemon.getName(), pokemon.getElement(),
                    pokemon.getLevel(),
                    pokemon.getHeight(), pokemon.gethp(), pokemon.getxp());
            pokemonService.addPokemon(squirtle);
            return ResponseEntity.ok("Squirtle added successfully");
        } else if (pokemonType.equalsIgnoreCase("wartortle")) {
            Wartortle wartortle = new Wartortle(pokemon.getName(), pokemon.getElement(),
                    pokemon.getLevel(),
                    pokemon.getHeight(), pokemon.gethp(), pokemon.getxp());
            pokemonService.addPokemon(wartortle);
            return ResponseEntity.ok("Wartortle added successfully");
        } else if (pokemonType.equalsIgnoreCase("crobat")) {
            Crobat crobat = new Crobat(pokemon.getName(), pokemon.getElement(),
                    pokemon.getLevel(),
                    pokemon.getHeight(), pokemon.gethp(), pokemon.getxp());
            pokemonService.addPokemon(crobat);
            return ResponseEntity.ok("Crobat added successfully");
        } else if (pokemonType.equalsIgnoreCase("zubat")) {
            Zubat zubat = new Zubat(pokemon.getName(), pokemon.getElement(),
                    pokemon.getLevel(),
                    pokemon.getHeight(), pokemon.gethp(), pokemon.getxp());
            pokemonService.addPokemon(zubat);
            return ResponseEntity.ok("Zubat added successfully");
        } else if (pokemonType.equalsIgnoreCase("golbat")) {
            Golbat golbat = new Golbat(pokemon.getName(), pokemon.getElement(),
                    pokemon.getLevel(),
                    pokemon.getHeight(), pokemon.gethp(), pokemon.getxp());
            pokemonService.addPokemon(golbat);
            return ResponseEntity.ok("Golbat added successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Pokemon type");
        }
    }

}
