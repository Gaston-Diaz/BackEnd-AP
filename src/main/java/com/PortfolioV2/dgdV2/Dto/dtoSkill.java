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
public class dtoSkill {
    @NotBlank
    private String nombreS;
    @NotBlank
    private int porcentaje;
    
    //constructores

    public dtoSkill() {
    }

    public dtoSkill(String nombreS, int porcentaje) {
        this.nombreS = nombreS;
        this.porcentaje = porcentaje;
    }
    
    //getter y setter

    public String getNombreS() {
        return nombreS;
    }

    public void setNombreS(String nombreS) {
        this.nombreS = nombreS;
    }

    public int getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }
    
    
}
