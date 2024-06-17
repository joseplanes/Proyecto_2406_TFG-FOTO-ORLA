package tfg.v1.fotoorla.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tfg.v1.fotoorla.AutoMapper.PersonaMapper;
import tfg.v1.fotoorla.DTOModel.DTOPersona;
import tfg.v1.fotoorla.Model.Curso;
import tfg.v1.fotoorla.Model.Persona;
import tfg.v1.fotoorla.Repository.ICursoRepository;
import tfg.v1.fotoorla.Repository.IPersonaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PersonaServiceImpl {

    @Autowired
    private IPersonaRepository personaRepository;

    @Autowired
    private ICursoRepository cursoRepository;

    @Autowired
    private PersonaMapper personaMapper;

    @Transactional
    public DTOPersona savePersona(DTOPersona personaDTO) {
        Persona persona = personaMapper.personaDTOToPersona(personaDTO);

        // Asignar el curso a la persona, si existe
        if (personaDTO.getCursoId() != null) {
            Curso curso = cursoRepository.findById(personaDTO.getCursoId()).orElse(null);
            if (curso != null) {
                persona.setCurso(curso);
            } else {
                // Manejar el caso donde el curso no existe
                throw new IllegalArgumentException("Curso no encontrado");
            }
        }

        Persona savedPersona = personaRepository.save(persona);
        return personaMapper.personaToPersonaDTO(savedPersona);
    }

    public List<DTOPersona> getAllPersonas() {
        List<Persona> personas = personaRepository.findAll();
        return personas.stream()
                .map(personaMapper::personaToPersonaDTO)
                .collect(Collectors.toList());
    }

    public DTOPersona getPersonaById(Long id) {
        Persona persona = personaRepository.findById(id).orElse(null);
        if (persona != null) {
            return personaMapper.personaToPersonaDTO(persona);
        }
        return null;
    }

    public void deletePersona(Long id) {
        personaRepository.deleteById(id);
    }

    public List<DTOPersona> getPersonaByCursoId(Long cursoId) {
        List<Persona> personas = personaRepository.findByCursoId(cursoId);
        return personas.stream()
                .map(personaMapper::personaToPersonaDTO)
                .collect(Collectors.toList());
    }
}
