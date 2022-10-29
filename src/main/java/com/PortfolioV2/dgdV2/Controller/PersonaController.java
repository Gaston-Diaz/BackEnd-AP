/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.PortfolioV2.dgdV2.Controller;

import com.PortfolioV2.dgdV2.Dto.dtoPersona;
import com.PortfolioV2.dgdV2.Entity.Persona;
import com.PortfolioV2.dgdV2.Service.ImpPersonaService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Diego
 */
@RestController
@RequestMapping("/personas")
/*@CrossOrigin(origins = "http://localhost:4200")*/
@CrossOrigin(origins = "https://portfolioap-v3.web.app")
public class PersonaController {
/*    @Autowired IPersonaService ipersonaService;
    
    @GetMapping("personas/traer")
    public List<Persona> getPersona(){
        return ipersonaService.getPersona();
    }
    
    @PostMapping("/personas/crear")
    public String createPersona(@RequestBody Persona persona){
        ipersonaService.savePersona(persona);
        return "La persona fue creada correctamente";
    }
    
    @DeleteMapping("/personas/borrar/{id}")
    public String deletePersona(@PathVariable Long id){
        ipersonaService.deletePersona(id);
        return "La persona fue eliminada correctamente";
    }
    
    @PutMapping("/personas/editar/{id}")
    public Persona editPersona(@PathVariable Long id,
                                @RequestParam("nombre") String nuevoNombre,
                                @RequestParam("apellido") String nuevoApellido,
                                //@RequestParam("img") String nuevoImg
                                @RequestParam("titulo") String nuevoTitulo,                                     
                                @RequestParam("descripcion") String nuevaDescripcion){
        Persona persona = ipersonaService.findPersona(id);
        
        persona.setNombre(nuevoNombre);
        persona.setApellido(nuevoApellido);
        //persona.setImg(nuevoImg);
        persona.setTitulo(nuevoTitulo);
        persona.setDescripcion(nuevaDescripcion);
        
        ipersonaService.savePersona(persona);
        return persona;
    }
    
    @GetMapping("/personas/traer/perfil")
    public Persona findPersona(){
        return ipersonaService.findPersona((long)1);
    }
    /*@GetMapping("/personas/traer/perfil/{id}")
    public Persona findPersona(@PathVariable Long id){
        Persona persona = ipersonaService.findPersona(id);
        return persona;
    }*/
    @Autowired
    ImpPersonaService personaService;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Persona>> list(){
        List<Persona> list = personaService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<Persona> getById(@PathVariable("id")int id){
        if(!personaService.existsById(id)){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        
        Persona persona = personaService.getOne(id).get();
        return new ResponseEntity(persona, HttpStatus.OK);
    }
    
    /*@DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!personaService.existsById(id)){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        personaService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }*/
    
   /* @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoPersona dtopersona){
    /*   if(StringUtils.isBlank(dtoeducacion.getNombreE())){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        if(personaService.existsByNombre(dtopersona.getNombre())){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        
        Persona persona = new Persona(
                dtopersona.getNombre(),dtopersona.getApellido(),dtopersona.getTitulo(),dtopersona.getDescripcion(),dtopersona.getImg(),dtopersona.getProvincia(),dtopersona.getCiudad()
                //resto de los datos
            );
        personaService.save(persona);
        return new ResponseEntity(HttpStatus.OK);
                
    }*/
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoPersona dtopersona){
        if(!personaService.existsById(id)){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        if(personaService.existsByNombre(dtopersona.getNombre()) && personaService.getByNombre(dtopersona.getNombre()).get().getId() != id){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtopersona.getNombre())){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        
        Persona persona = personaService.getOne(id).get();
        
        persona.setNombre(dtopersona.getNombre());
        persona.setApellido(dtopersona.getApellido());
        persona.setTitulo(dtopersona.getTitulo());
        persona.setDescripcion(dtopersona.getDescripcion());
        persona.setImg(dtopersona.getImg());
        persona.setProvincia(dtopersona.getProvincia());
        persona.setCiudad(dtopersona.getCiudad());
        
        personaService.save(persona);
        
        return new ResponseEntity(HttpStatus.OK);
    }
    
}

