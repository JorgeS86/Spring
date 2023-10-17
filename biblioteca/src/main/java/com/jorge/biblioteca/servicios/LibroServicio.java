/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jorge.biblioteca.servicios;

import com.jorge.biblioteca.entidades.Autor;
import com.jorge.biblioteca.entidades.Editorial;
import com.jorge.biblioteca.entidades.Libro;
import com.jorge.biblioteca.excepciones.MiException;
import com.jorge.biblioteca.repositorios.AutorRepositorio;
import com.jorge.biblioteca.repositorios.EditorialRepositorio;
import com.jorge.biblioteca.repositorios.LibroRepositorio;
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
public class LibroServicio {

    @Autowired
    private LibroRepositorio libroRepositorio;
    @Autowired
    private AutorRepositorio autorRepositorio;
    @Autowired
    private EditorialRepositorio editorialRepositorio;

    @Transactional
    public void crearLibro(Long isbn, String titulo, Integer ejemplares, String idAutor, String idEditorial) throws MiException {

        validar(isbn, titulo, idAutor, idEditorial, ejemplares);

        Autor autor = autorRepositorio.findById(idAutor).get();

        Editorial editorial = editorialRepositorio.findById(idEditorial).get();

        Libro libro = new Libro();
        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setEjemplares(ejemplares);
        libro.setAlta(new Date());

        libro.setAutor(autor);
        libro.setEditorial(editorial);
        libroRepositorio.save(libro);
    }

    public List<Libro> listarLibros() {

        List<Libro> libros = new ArrayList();

        libros = libroRepositorio.findAll();

        return libros;
    }

    public void modificarLibro(Long isbn, String titulo, String idAutor, String idEditorial, Integer ejemplares) throws MiException {

        validar(isbn, titulo, idAutor, idEditorial, ejemplares);

        Optional<Libro> respuesta = libroRepositorio.findById(isbn);
        Optional<Autor> respuestaAutor = autorRepositorio.findById(idAutor);
        Optional<Editorial> respuestaEditorial = editorialRepositorio.findById(idEditorial);

        Autor autor = new Autor();
        Editorial editorial = new Editorial();

        if (respuestaAutor.isPresent()) {

            autor = respuestaAutor.get();
        }

        if (respuestaEditorial.isPresent()) {

            editorial = respuestaEditorial.get();
        }

        if (respuesta.isPresent()) {
            Libro libro = respuesta.get();

            libro.setTitulo(titulo);

            libro.setAutor(autor);

            libro.setEditorial(editorial);

            libro.setEjemplares(ejemplares);

            libroRepositorio.save(libro);
        }
    }

    private void validar(Long isbn, String titulo, String idAutor, String idEditorial, Integer ejemplares) throws MiException {
        if (isbn == null) {
            throw new MiException("El isbn no puede ser nulo");
        }

        if (titulo.isEmpty() || titulo == null) {
            throw new MiException("El título no puede ser nulo o estar vacío");
        }
        if (ejemplares == null) {
            throw new MiException("Los ejemplares no pueden ser nulos");
        }

        if (idAutor.isEmpty() || idAutor == null) {
            throw new MiException("El idAutor no puede ser nulo o estar vacío");
        }
        if (idEditorial.isEmpty() || idEditorial == null) {
            throw new MiException("El idEditorial no puede ser nulo o estar vacío");
        }
    }

}
