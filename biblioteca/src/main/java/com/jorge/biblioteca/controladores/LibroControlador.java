/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jorge.biblioteca.controladores;

import com.jorge.biblioteca.entidades.Autor;
import com.jorge.biblioteca.entidades.Editorial;
import com.jorge.biblioteca.excepciones.MiException;
import com.jorge.biblioteca.servicios.AutorServicio;
import com.jorge.biblioteca.servicios.EditorialServicio;
import com.jorge.biblioteca.servicios.LibroServicio;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author jorge
 */
@Controller
@RequestMapping("/libro") //localhost:8080/libro
public class LibroControlador {

    @Autowired
    private LibroServicio libroServicio;
    @Autowired
    private AutorServicio autorServicio;
    @Autowired
    private EditorialServicio editorialServicio;

    @GetMapping("/registrar") //localhost:8080/libro/registrar
    public String registrar(ModelMap modelo) {
        List<Autor> autores = autorServicio.listarAutores();
        List<Editorial> editoriales = editorialServicio.listarEditoriales();

        modelo.addAttribute("autores", autores);
        modelo.addAttribute("editoriales", editoriales);

        return "libro_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, @RequestParam(required = false) Long isbn, @RequestParam(required = false) Integer ejemplares, @RequestParam String idAutor, @RequestParam String idEditorial, ModelMap modelo) {
//            System.out.println("ISBN: "+isbn);
//            System.out.println("Nombre: "+nombre);
//            System.out.println("Ejemplares: "+ejemplares);
//            System.out.println("Id Autor: "+idAutor);
//            System.out.println("Id Editorial: "+idEditorial);

        try {

            libroServicio.crearLibro(isbn, nombre, ejemplares, idAutor, idEditorial);
            modelo.put("exito", "El Libro fue cargado correctamente!");

        } catch (MiException ex) {
            List<Autor> autores = autorServicio.listarAutores();
            List<Editorial> editoriales = editorialServicio.listarEditoriales();

            modelo.addAttribute("autores", autores);
            modelo.addAttribute("editoriales", editoriales);
            modelo.put("error", ex.getMessage());
            
            return "libro_form.html";
        }

        return "index.html";

    }
}
