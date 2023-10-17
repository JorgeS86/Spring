/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jorge.biblioteca.controladores;

import com.jorge.biblioteca.entidades.Editorial;
import com.jorge.biblioteca.excepciones.MiException;
import com.jorge.biblioteca.servicios.EditorialServicio;
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
@RequestMapping("/editorial") //localhost:8080/editorial
public class EditorialControlador {

    @Autowired
    private EditorialServicio editorialServicio;

    @GetMapping("/registrar") //localhost:8080/editorial/registrar
    public String registrar() {
        return "editorial_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, ModelMap modelo) {
        try {
            //System.out.println("Nombre: "+nombre);
            editorialServicio.crearEditorial(nombre);
            modelo.put("exito","La Editorial fue cargada Correctamente");
            
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "editorial_form.html";
        }

        return "index.html";
    }
    
    @GetMapping("/lista")
    public String listar(ModelMap modelo){
        
        List<Editorial> editoriales = editorialServicio.listarEditoriales();
        
        modelo.addAttribute("editoriales", editoriales);
        
        return "editorial_list.html";
    }
}
