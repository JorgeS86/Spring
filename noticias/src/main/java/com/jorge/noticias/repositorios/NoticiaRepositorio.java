/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jorge.noticias.repositorios;

import com.jorge.noticias.entidades.Noticia;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jorge
 */
@Repository
public interface NoticiaRepositorio extends JpaRepository<Noticia, Long>{
   
    @Query("SELECT n FROM Noticia n WHERE n.titulo = :titulo" )
    public List<Noticia> buscarNoticiaPorTitulo(@Param("titulo") String titulo);
    
    @Query("SELECT n FROM Noticia n ORDER BY n.fecha DESC" )
    public List<Noticia> buscarNoticiaPorFecha();
    
    @Query("SELECT n FROM Noticia n WHERE n.titulo LIKE %:palabra%")
    public List<Noticia> buscarNoticiaPorPalabra(@Param("palabra")String palabra);
}
