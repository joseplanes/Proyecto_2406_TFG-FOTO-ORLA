package tfg.v1.fotoorla.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tfg.v1.fotoorla.DTOModel.DTOCurso;
import tfg.v1.fotoorla.AutoMapper.CursoMapper;
import tfg.v1.fotoorla.DTOModel.DTOCursoCreacion;
import tfg.v1.fotoorla.DTOModel.DTOResponse;
import tfg.v1.fotoorla.Service.CursoServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cursos")
@CrossOrigin(origins = "http://localhost:4200")
public class    CursoController {

    @Autowired
    private CursoServiceImpl cursoService;

    @PostMapping("/getNombrePorId/{id}")
    public ResponseEntity<DTOResponse> getNombreCursoPorId(@PathVariable Long id) {
        DTOResponse nombreCurso = cursoService.getNombreCursoPorId(id);
        return ResponseEntity.ok(nombreCurso);
    }

    @PostMapping("/create")
    public ResponseEntity<DTOCurso> createCurso(@RequestBody DTOCursoCreacion cursoDTO) {
        DTOCurso savedCursoDTO = cursoService.saveCurso(cursoDTO);
        return ResponseEntity.ok(savedCursoDTO);
    }
    @GetMapping("/list")
    public ResponseEntity<List<DTOCurso>> getAllCursos() {
        List<DTOCurso> cursosDTO = cursoService.getAllCursos();
        return ResponseEntity.ok(cursosDTO);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<DTOCurso> getCursoById(@PathVariable Long id) {
        DTOCurso cursoDTO = cursoService.getCursoById(id);
        return cursoDTO != null ? ResponseEntity.ok(cursoDTO) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCurso(@PathVariable Long id) {
        cursoService.deleteCurso(id);
        return ResponseEntity.ok().build();
    }
}
