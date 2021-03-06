package com.example.PokemonDB.api;

import com.example.PokemonDB.model.Pokemon;
import com.example.PokemonDB.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/pokemon")
@RestController
public class PokemonController {

    private final PokemonService pokemonService;

    @Autowired
    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @PostMapping
    public void addPokemon(@Valid @NotNull @RequestBody Pokemon pokemon) {
        pokemonService.addPokemon(pokemon);
    }

    @GetMapping
    public List<Pokemon> getAllPokemon() {
        return pokemonService.getAllPokemon();
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    public Pokemon getPokemonById(@PathVariable("id") UUID id) {
        return pokemonService.getPokemonById(id).orElse(null);
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.DELETE)
    public void deletePokemonById(@PathVariable("id") UUID id) {
        pokemonService.deletePokemonById(id);
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.PUT)
    public void updatePokemonById(@PathVariable("id") UUID id, @Valid @NotNull @RequestBody Pokemon pokemonToUpdate) {
        pokemonService.updatePokemonById(id, pokemonToUpdate);
    }

    @RequestMapping(value = "/type/{type}", method = RequestMethod.GET)
    public List<Pokemon> getPokemonByType(@PathVariable("type") String type) {
        return pokemonService.getPokemonByType(type);
    }

}
