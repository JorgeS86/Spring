/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jorge.noticias.controladores;

import com.jorge.noticias.entidades.Noticia;
import com.jorge.noticias.excepciones.MiException;
import com.jorge.noticias.servicios.NoticiaServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author jorge
 */
@Controller
@RequestMapping("/noticia")
public class NoticiaControlador {

    @Autowired
    private NoticiaServicio noticiaServicio;

    @GetMapping("/registrar")
    public String crear() {
        return "noticia_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam(required = false) String titulo, @RequestParam(required = false) Long id, String cuerpo, ModelMap modelo) {

        try {
            //System.out.println("Titulo "+titulo);
            noticiaServicio.crearNoticia(id, titulo, cuerpo);
            modelo.put("exito", "La Noticia fue cargada correctamente!!!");
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "noticia_form.html";
        }

        return "index.html";
    }

    @GetMapping("/lista")
    public String listar(ModelMap modelo) {
        //List<Noticia> noticias = noticiaServicio.listarNoticias();
        List<Noticia> noticias = noticiaServicio.listarNoticias();
        modelo.addAttribute("noticias", noticias);

        return "index.html";

    }

    @GetMapping("/modificar")
    public String listaMod(ModelMap modelo) {
        //List<Noticia> noticias = noticiaServicio.listarNoticias();
        List<Noticia> noticias = noticiaServicio.listarNoticias();
        modelo.addAttribute("noticias", noticias);

        return "noticia_lista_modificar.html";
    }

    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable Long id, ModelMap modelo) {
        modelo.put("noticia", noticiaServicio.getOne(id));

        return "noticia_modificar.html";
    }

    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable Long id, String titulo, String cuerpo, Boolean alta, ModelMap modelo) {
        System.out.println("id:" + id);
        System.out.println("titulo:" + titulo);
        System.out.println("cuerpo:" + cuerpo);
        System.out.println("alta:" + alta);
        try {
            noticiaServicio.modificarNoticia(id, titulo, cuerpo, alta);
            return "redirect:../lista";
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "noticia_lista_modificar.html";
        }
    }

    
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, ModelMap modelo) {
        modelo.put("noticia", noticiaServicio.getOne(id));
        return "noticia_eliminar.html";
    }
    
    @PostMapping("/eliminar/{id}")
    public String eliminarNoticia(@PathVariable Long id, ModelMap modelo){
        System.out.println("id:" + id);
        
        try {
            noticiaServicio.eliminarNoticia(id);
            return "redirect:../lista";
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "noticia_lista_modificar.html";
        }
    }
    
     @GetMapping("/ver/{id}")
    public String verNoticia(@PathVariable Long id, ModelMap modelo) {
        modelo.put("noticia", noticiaServicio.getOne(id));
        return "noticia_ver.html";
    }
    
}
