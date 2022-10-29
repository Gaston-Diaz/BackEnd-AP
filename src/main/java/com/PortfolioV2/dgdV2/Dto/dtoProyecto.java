/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.PortfolioV2.dgdV2.Dto;

import javax.validation.constraints.NotBlank;

/**
 *
 * @author Diego
 */
public class dtoProyecto {
    @NotBlank
    private String nombreP;
    @NotBlank
    private String enlace;
    @NotBlank
    private String descripcionP;
    private String fecha;
    
    //constructores

    public dtoProyecto() {
    }

    public dtoProyecto(String nombreP, String enlace, String descripcionP, String fecha) {
        this.nombreP = nombreP;
        this.enlace = enlace;
        this.descripcionP = descripcionP;
        this.fecha = fecha;
    }
    
    //getter y setter

    public String getNombreP() {
        return nombreP;
    }

    public void setNombreP(String nombreP) {
        this.nombreP = nombreP;
    }

    public String getEnlace() {
        return enlace;
    }

    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }

    public String getDescripcionP() {
        return descripcionP;
    }

    public void setDescripcionP(String descripcionP) {
        this.descripcionP = descripcionP;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
}
