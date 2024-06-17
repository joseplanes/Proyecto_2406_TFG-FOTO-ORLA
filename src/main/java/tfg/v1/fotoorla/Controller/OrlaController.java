package tfg.v1.fotoorla.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tfg.v1.fotoorla.DTOModel.DTOOrla;
import tfg.v1.fotoorla.Model.Orla;
import tfg.v1.fotoorla.Service.OrlaServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/orlas")
@CrossOrigin(origins = "http://localhost:4200")
public class OrlaController {

    @Autowired
    private OrlaServiceImpl orlaService;

    @PostMapping("/create")
    public ResponseEntity<DTOOrla> createOrla(@RequestBody DTOOrla orlaDTO) {
        DTOOrla savedOrla = orlaService.saveOrla(orlaDTO);
        return ResponseEntity.ok(savedOrla);
    }

    @GetMapping("/list")
    public ResponseEntity<List<DTOOrla>> getAllOrlas() {
        List<DTOOrla> orlas = orlaService.getAllOrlas();
        return ResponseEntity.ok(orlas);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<DTOOrla> getOrlaById(@PathVariable Long id) {
        DTOOrla orla = orlaService.getOrlaById(id);
        if (orla != null) {
            return ResponseEntity.ok(orla);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteOrla(@PathVariable Long id) {
        orlaService.deleteOrla(id);
        return ResponseEntity.ok().build();
    }
}