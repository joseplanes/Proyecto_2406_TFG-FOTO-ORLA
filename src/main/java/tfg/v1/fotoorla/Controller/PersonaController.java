package tfg.v1.fotoorla.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tfg.v1.fotoorla.DTOModel.DTOPersona;
import tfg.v1.fotoorla.Service.PersonaServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/personas")
@CrossOrigin(origins = "http://localhost:4200")
public class PersonaController {

    @Autowired
    private PersonaServiceImpl personaService;

    @PostMapping("/create")
    public ResponseEntity<DTOPersona> createPersona(@RequestBody DTOPersona personaDTO) {
        try {
            DTOPersona savedPersona = personaService.savePersona(personaDTO);
            return ResponseEntity.ok(savedPersona);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<DTOPersona>> getAllPersonas() {
        List<DTOPersona> personas = personaService.getAllPersonas();
        return ResponseEntity.ok(personas);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<DTOPersona> getPersonaById(@PathVariable Long id) {
        DTOPersona persona = personaService.getPersonaById(id);
        if (persona != null) {
            return ResponseEntity.ok(persona);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePersona(@PathVariable Long id) {
        personaService.deletePersona(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/personasPorCurso/{cursoId}")
    public ResponseEntity<List<DTOPersona>> getPersonasByCursoId(@PathVariable Long cursoId) {
        List<DTOPersona> personas = personaService.getPersonaByCursoId(cursoId);
        return ResponseEntity.ok(personas);
    }
}
