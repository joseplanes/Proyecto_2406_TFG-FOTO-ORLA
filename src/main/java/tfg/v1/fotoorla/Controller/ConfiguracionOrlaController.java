package tfg.v1.fotoorla.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tfg.v1.fotoorla.DTOModel.DTOConfiguracionOrla;
import tfg.v1.fotoorla.Service.ConfiguracionOrlaServiceImpl;

import java.util.List;
@RestController
@RequestMapping("/configuracionesOrla")
@CrossOrigin(origins = "http://localhost:4200")
public class ConfiguracionOrlaController {

    @Autowired
    private ConfiguracionOrlaServiceImpl configuracionOrlaService;

    @PostMapping("/create")
    public ResponseEntity<DTOConfiguracionOrla> createConfiguracionOrla(@RequestBody DTOConfiguracionOrla configuracionOrlaDTO) {
        DTOConfiguracionOrla savedConfiguracionOrlaDTO = configuracionOrlaService.saveConfiguracionOrla(configuracionOrlaDTO);
        return ResponseEntity.ok(savedConfiguracionOrlaDTO);
    }

    @GetMapping("/list")
    public ResponseEntity<List<DTOConfiguracionOrla>> getAllConfiguracionesOrla() {
        List<DTOConfiguracionOrla> configuracionesOrlaDTO = configuracionOrlaService.getAllConfiguracionesOrla();
        return ResponseEntity.ok(configuracionesOrlaDTO);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<DTOConfiguracionOrla> getConfiguracionOrlaById(@PathVariable Long id) {
        DTOConfiguracionOrla configuracionOrlaDTO = configuracionOrlaService.getConfiguracionOrlaById(id);
        return configuracionOrlaDTO != null ? ResponseEntity.ok(configuracionOrlaDTO) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteConfiguracionOrla(@PathVariable Long id) {
        configuracionOrlaService.deleteConfiguracionOrla(id);
        return ResponseEntity.ok().build();
    }
}