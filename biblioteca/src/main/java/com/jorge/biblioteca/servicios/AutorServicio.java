/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jorge.biblioteca.servicios;

import com.jorge.biblioteca.entidades.Autor;
import com.jorge.biblioteca.excepciones.MiException;
import com.jorge.biblioteca.repositorios.AutorRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jorge
 */
@Service
public class AutorServicio {
    
    @Autowired
    AutorRepositorio autorRepositorio;
    
    @Transactional
    public void crearAutor(String nombre) throws MiException{
        
        validar(nombre);
        
        Autor autor = new Autor();
        
        autor.setNombre(nombre);
        
        autorRepositorio.save(autor);
    }
    
     public List<Autor> listarAutores(){
        
        List<Autor> autores = new ArrayList();
        
        autores = autorRepositorio.findAll();
        
        return autores;
    }
     
     public void modificarAutor(String nombre, String id){
         
         Optional<Autor> respuesta = autorRepositorio.findById(id);
         
         if(respuesta.isPresent()){
             Autor autor = respuesta.get();
             
             autor.setNombre(nombre);
             
             autorRepositorio.save(autor);
         }
         
     }
     
     private void validar(String nombre) throws MiException {
        
        if (nombre.isEmpty() || nombre == null) {
            throw new MiException("el nombre no puede ser nulo o estar vacio");
        }
    }
}
