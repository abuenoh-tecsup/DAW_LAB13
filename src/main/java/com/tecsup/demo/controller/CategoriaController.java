package com.tecsup.demo.controller;

import com.tecsup.demo.model.Categoria;
import com.tecsup.demo.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @PostMapping
    public String crearCategoria(@RequestBody Categoria categoria) {
        categoriaRepository.save(categoria);
        return "Categoria creada correctamente";
    }

    @GetMapping
    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Categoria buscarCategoria(@PathVariable Long id) {
        return categoriaRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public String actualizarCategoria(@RequestBody Categoria categoria, @PathVariable Long id) {
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);
        if (categoriaOptional.isPresent()) {
            Categoria categoriaAux = categoriaOptional.get();
            categoriaAux.setNombre(categoria.getNombre());
            categoriaRepository.save(categoriaAux);
            return "Categoria actualizada correctamente";
        } else {
            return "Categoria no encontrada";
        }
    }

    @DeleteMapping("/{id}")
    public String eliminarCategoria(@PathVariable Long id) {
        if (categoriaRepository.existsById(id)) {
            categoriaRepository.deleteById(id);
            return "Categoria eliminada correctamente";
        } else {
            return "Categoria no encontrada";
        }
    }
}
