package com.tecsup.demo.controller;

import com.tecsup.demo.model.Ingrediente;
import com.tecsup.demo.model.Receta;
import com.tecsup.demo.repository.IngredienteRepository;
import com.tecsup.demo.repository.RecetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/recetas")
public class RecetaController {

    @Autowired
    private RecetaRepository recetaRepository;

    @Autowired
    private IngredienteRepository ingredienteRepository;

    @PostMapping
    public Receta crearReceta(@RequestBody Receta receta) {
        // Asegurarse de que los ingredientes existen
        List<Ingrediente> ingredientesValidados = new ArrayList<>();
        if (receta.getIngredientes() != null) {
            for (Ingrediente ing : receta.getIngredientes()) {
                ingredienteRepository.findById(ing.getId()).ifPresent(ingredientesValidados::add);
            }
        }
        receta.setIngredientes(ingredientesValidados);
        return recetaRepository.save(receta);
    }

    @GetMapping
    public List<Receta> listarRecetas() {
        return recetaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Receta obtenerReceta(@PathVariable Long id) {
        return recetaRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Receta actualizarReceta(@PathVariable Long id, @RequestBody Receta receta) {
        return recetaRepository.findById(id).map(aux -> {
            aux.setNombre(receta.getNombre());

            List<Ingrediente> ingredientesActualizados = new ArrayList<>();
            if (receta.getIngredientes() != null) {
                for (Ingrediente ing : receta.getIngredientes()) {
                    ingredienteRepository.findById(ing.getId()).ifPresent(ingredientesActualizados::add);
                }
            }
            aux.setIngredientes(ingredientesActualizados);

            return recetaRepository.save(aux);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public String eliminarReceta(@PathVariable Long id) {
        if (recetaRepository.existsById(id)) {
            recetaRepository.deleteById(id);
            return "Receta eliminada correctamente";
        }
        return "Receta no encontrada";
    }
}
