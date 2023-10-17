/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jorge.biblioteca.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author jorge
 */
@Controller
@RequestMapping("/")
public class PortalControlador { //localhost:8080/
    
    @GetMapping("/")
    public String index(){ //localhost:8080/
        
        return "index.html";
    }
}
