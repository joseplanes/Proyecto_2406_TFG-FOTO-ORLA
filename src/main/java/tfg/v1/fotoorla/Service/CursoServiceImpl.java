package tfg.v1.fotoorla.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tfg.v1.fotoorla.AutoMapper.CursoMapper;
import tfg.v1.fotoorla.DTOModel.DTOCurso;
import tfg.v1.fotoorla.DTOModel.DTOCursoCreacion;
import tfg.v1.fotoorla.DTOModel.DTOResponse;
import tfg.v1.fotoorla.Model.Curso;
import tfg.v1.fotoorla.Repository.ICursoRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CursoServiceImpl {

    @Autowired
    private ICursoRepository cursoRepository;

    @Autowired
    private CursoMapper cursoMapper;

    public DTOResponse getNombreCursoPorId(Long id) {
        return cursoRepository.getNombrePorId(id);
    }

    public DTOCurso saveCurso(DTOCursoCreacion cursoDTO) {
        // Convertir DTOCursoCreacion a DTOCurso
        DTOCurso curso = cursoMapper.cursoToCursoCreacionDTO(cursoDTO);

        // Convertir DTOCurso a Curso y guardar en la base de datos
        Curso savedCurso = cursoRepository.save(cursoMapper.cursoDTOToCurso(curso));

        // Convertir Curso guardado de nuevo a DTOCurso y retornar
        return cursoMapper.cursoToCursoDTO(savedCurso);
    }


    public List<DTOCurso> getAllCursos() {
        return cursoRepository.findAll().stream()
                .map(cursoMapper::cursoToCursoDTO)
                .collect(Collectors.toList());
    }

    public DTOCurso getCursoById(Long id) {
        Curso curso = cursoRepository.findById(id).orElse(null);
        if (curso == null) {
            return null;
        }
        return cursoMapper.cursoToCursoDTO(curso);
    }

    public void deleteCurso(Long id) {
        cursoRepository.deleteById(id);
    }
}
