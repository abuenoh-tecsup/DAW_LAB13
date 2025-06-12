package com.tecsup.demo.controller;

import com.tecsup.demo.model.Categoria;
import com.tecsup.demo.model.Producto;
import com.tecsup.demo.repository.CategoriaRepository;
import com.tecsup.demo.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @PostMapping
    public String crearProducto(@RequestBody Producto producto) {
        if (producto.getCategoria() == null || !categoriaRepository.existsById(producto.getCategoria().getId())){
            throw new IllegalArgumentException("La categoria no existe");
        }
        productoRepository.save(producto);
        return "Producto creada correctamente";
    }

    @GetMapping
    public List<Producto> getProductos() {
        return productoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Producto getProducto(@PathVariable Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public String actualizarProducto(@PathVariable Long id, @RequestBody Producto producto) {
        return productoRepository.findById(id).map(producto_aux -> {
            producto_aux.setNombre(producto.getNombre());
            producto_aux.setPrecio(producto.getPrecio());
            if (producto.getCategoria() != null) {
                Optional<Categoria> categoria = categoriaRepository.findById(producto.getCategoria().getId());
                categoria.ifPresent(producto::setCategoria);
            }

            productoRepository.save(producto_aux);
            return "Producto modificada correctamente";
        }).orElse("No se encontro el producto");
    }

    @DeleteMapping("/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
            return "Producto eliminada correctamente";
        } else {
            return "No se encontro el producto";
        }
    }
}
