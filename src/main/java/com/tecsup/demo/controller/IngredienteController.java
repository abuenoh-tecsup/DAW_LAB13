package com.tecsup.demo.controller;

import com.tecsup.demo.model.Ingrediente;
import com.tecsup.demo.repository.IngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ingredientes")
public class IngredienteController {

    @Autowired
    private IngredienteRepository ingredienteRepository;

    @PostMapping
    public Ingrediente crearIngrediente(@RequestBody Ingrediente ingrediente) {
        return ingredienteRepository.save(ingrediente);
    }

    @GetMapping
    public List<Ingrediente> listarIngredientes() {
        return ingredienteRepository.findAll();
    }

    @GetMapping("/{id}")
    public Ingrediente obtenerIngrediente(@PathVariable Long id) {
        return ingredienteRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Ingrediente actualizarIngrediente(@PathVariable Long id, @RequestBody Ingrediente ingrediente) {
        return ingredienteRepository.findById(id).map(aux -> {
            aux.setNombre(ingrediente.getNombre());
            return ingredienteRepository.save(aux);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public String eliminarIngrediente(@PathVariable Long id) {
        if (ingredienteRepository.existsById(id)) {
            ingredienteRepository.deleteById(id);
            return "Ingrediente eliminado correctamente";
        }
        return "Ingrediente no encontrado";
    }
}