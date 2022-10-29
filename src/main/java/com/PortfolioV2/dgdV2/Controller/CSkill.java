/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.PortfolioV2.dgdV2.Controller;

import com.PortfolioV2.dgdV2.Dto.dtoSkill;
import com.PortfolioV2.dgdV2.Entity.Skill;
import com.PortfolioV2.dgdV2.Service.Sskill;
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
@RequestMapping("/skill")
/*@CrossOrigin(origins = "http://localhost:4200")*/
@CrossOrigin(origins = "https://portfolioap-v3.web.app")
public class CSkill {
    @Autowired
    Sskill sSkill;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Skill>> list(){
        List<Skill> list = sSkill.list();
        return new ResponseEntity(list,HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Skill> getById(@PathVariable("id") int id){
        if(!sSkill.existsById(id)){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        Skill skill = sSkill.getOne(id).get();
        return new ResponseEntity(skill,HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Skill> delete(@PathVariable("id") int id){
        if(!sSkill.existsById(id)){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        sSkill.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<Skill> create(@RequestBody dtoSkill dtoskill){
    /*    if(StringUtils.isBlank(dtoskill.getNombreS())){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        if(sSkill.existsByNombreS(dtoskill.getNombreS())){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }*/
        Skill skill = new Skill(
                        dtoskill.getNombreS(),
                        dtoskill.getPorcentaje());
        sSkill.save(skill);
        return new ResponseEntity(HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<Skill> update(@PathVariable("id")int id,@RequestBody dtoSkill dtoskill){
        if(!sSkill.existsById(id)){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        if(sSkill.existsByNombreS(dtoskill.getNombreS())&& sSkill.getByNombreS(dtoskill.getNombreS()).get().getId() != id){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(dtoskill.getNombreS())){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        Skill skill = sSkill.getOne(id).get();
        skill.setNombreS(dtoskill.getNombreS());
        skill.setPorcentaje(dtoskill.getPorcentaje());
        
        sSkill.save(skill);
        
        return new ResponseEntity(HttpStatus.OK);
    }
}
