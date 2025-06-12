package com.tecsup.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/templates")
public class TemplateController {

    @GetMapping("/ingredientes-list")
    public String ingredientesList() {
        return "ingredientes-list";
    }

    @GetMapping("/ingrediente-form")
    public String ingredienteForm() {
        return "ingrediente-form";
    }

    @GetMapping("/recetas-list")
    public String recetasList() {
        return "recetas-list";
    }

    @GetMapping("/receta-form")
    public String recetaForm() {
        return "receta-form";
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }
}