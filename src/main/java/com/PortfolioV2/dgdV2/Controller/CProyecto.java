/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.PortfolioV2.dgdV2.Controller;

import com.PortfolioV2.dgdV2.Dto.dtoProyecto;
import com.PortfolioV2.dgdV2.Entity.Proyecto;
import com.PortfolioV2.dgdV2.Service.SProyecto;
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
@RequestMapping("/proyecto")
@CrossOrigin(origins = "https://portfolioap-v3.web.app")
public class CProyecto {
    @Autowired
    SProyecto sProyecto;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Proyecto>> list(){
        List<Proyecto> list = sProyecto.list();
        return new ResponseEntity(list,HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Proyecto> getById(@PathVariable("id") int id){
        if(!sProyecto.existsById(id)){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        Proyecto skill = sProyecto.getOne(id).get();
        return new ResponseEntity(skill,HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Proyecto> delete(@PathVariable("id") int id){
        if(!sProyecto.existsById(id)){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        sProyecto.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<Proyecto> create(@RequestBody dtoProyecto dtoproyecto){
    /*    if(StringUtils.isBlank(dtoskill.getNombreS())){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        if(sSkill.existsByNombreS(dtoskill.getNombreS())){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }*/
        Proyecto proyecto = new Proyecto(
                            dtoproyecto.getNombreP(),
                            dtoproyecto.getEnlace(),
                            dtoproyecto.getDescripcionP(),
                            dtoproyecto.getFecha());
        sProyecto.save(proyecto);
        return new ResponseEntity(HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<Proyecto> update(@PathVariable("id")int id,@RequestBody dtoProyecto dtoproyecto){
        if(!sProyecto.existsById(id)){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        if(sProyecto.existsByNombreP(dtoproyecto.getNombreP())&& sProyecto.getByNombreP(dtoproyecto.getNombreP()).get().getId() != id){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(dtoproyecto.getNombreP())){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        Proyecto proyecto = sProyecto.getOne(id).get();
        proyecto.setNombreP(dtoproyecto.getNombreP());
        proyecto.setEnlace(dtoproyecto.getEnlace());
        proyecto.setDescripcionP(dtoproyecto.getDescripcionP());
        proyecto.setFecha(dtoproyecto.getFecha());
        
        sProyecto.save(proyecto);
        
        return new ResponseEntity(HttpStatus.OK);
    }
    
}
