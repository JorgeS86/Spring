/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jorge.noticias.servicios;

import com.jorge.noticias.entidades.Noticia;
import com.jorge.noticias.excepciones.MiException;
import com.jorge.noticias.repositorios.NoticiaRepositorio;
import java.util.ArrayList;
import java.util.Date;
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
public class NoticiaServicio {

    @Autowired
    private NoticiaRepositorio noticiaRepositorio;

    @Transactional
    public void crearNoticia(Long id, String titulo, String cuerpo) throws MiException {

        validar(id, titulo, cuerpo, Boolean.FALSE);

        Noticia noticia = new Noticia();

        noticia.setTitulo(titulo);
        noticia.setCuerpo(cuerpo);
        noticia.setAlta(true);
        noticia.setFecha(new Date());

        noticiaRepositorio.save(noticia);
    }

    public List<Noticia> listarNoticias() {
        List<Noticia> noticias = new ArrayList();
        noticias = noticiaRepositorio.findAll();
//      noticias = noticiaRepositorio.buscarNoticiaPorFecha();
//      noticias = noticiaRepositorio.buscarNoticiaPorPalabra();
        return noticias;
    }

    @Transactional
    public void modificarNoticia(Long id, String titulo, String cuerpo, Boolean alta) throws MiException {

        validar(id, titulo, cuerpo, alta);

        Optional<Noticia> respuesta = noticiaRepositorio.findById(id);

        if (respuesta.isPresent()) {
            Noticia noticia = respuesta.get();
            noticia.setTitulo(titulo);
            noticia.setCuerpo(cuerpo);
            noticia.setAlta(alta);
            noticia.setFecha(new Date());
        }
    }
    
    public void eliminarNoticia(Long id) throws MiException{
         validarId(id);
         
         noticiaRepositorio.deleteById(id);
        
    }
    
    public Noticia getOne(Long id){
        return noticiaRepositorio.getOne(id);
    }

    private void validar(Long id, String titulo, String cuerpo, Boolean alta) throws MiException {
        if (id == null) {
            throw new MiException("El ID no puede ser Nulo");
        }

        if (titulo.isEmpty() || titulo == null) {
            throw new MiException("El Titulo no puede ser Nulo o estar Vacio");
        }
    }
    
     private void validarId(Long id) throws MiException {
        if (id == null) {
            throw new MiException("El ID no puede ser Nulo");
        }
     }
}
