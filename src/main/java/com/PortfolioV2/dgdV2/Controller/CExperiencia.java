/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.PortfolioV2.dgdV2.Controller;

import com.PortfolioV2.dgdV2.Dto.dtoExperiencia;
import com.PortfolioV2.dgdV2.Entity.Experiencia;
import com.PortfolioV2.dgdV2.Service.SExperiencia;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Diego
 */
@RestController
@RequestMapping("explab")
/*@CrossOrigin(origins = "http://localhost:4200")*/
@CrossOrigin(origins = "https://portfolioap-v3.web.app")
public class CExperiencia {
    @Autowired
    SExperiencia sExperiencia;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Experiencia>> list(){
        List<Experiencia> list = sExperiencia.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoExperiencia dtoexp){      
    /*    if(StringUtils.isBlank(dtoexp.getNombreE()))
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(dtoexp.getDescripcionE()))
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        if(sExperiencia.existsByNombreE(dtoexp.getNombreE()))
            return new ResponseEntity(HttpStatus.BAD_REQUEST);*/
        
        Experiencia experiencia = new Experiencia(dtoexp.getNombreE(), dtoexp.getDescripcionE(), dtoexp.getFechaIni(), dtoexp.getFechaFin());
        sExperiencia.save(experiencia);
        
        return new ResponseEntity(HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoExperiencia dtoExp){
        if(!sExperiencia.existsById(id))
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        
        if (StringUtils.isBlank(dtoExp.getNombreE()))
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        
        Experiencia experiencia = sExperiencia.getOne(id).get();
        experiencia.setNombreE(dtoExp.getNombreE());
        experiencia.setDescripcionE(dtoExp.getDescripcionE());
        experiencia.setFechaIni(dtoExp.getFechaIni());
        experiencia.setFechaFin(dtoExp.getFechaFin());
        
        sExperiencia.save(experiencia);
        return new ResponseEntity(HttpStatus.OK);
    }
   
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!sExperiencia.existsById(id))
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        
        sExperiencia.delete(id);
        
        return new ResponseEntity(HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Experiencia> getById(@PathVariable("id") int id){
        if(!sExperiencia.existsById(id))
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        Experiencia experiencia = sExperiencia.getOne(id).get();
        return new ResponseEntity(experiencia, HttpStatus.OK);
    }
}
