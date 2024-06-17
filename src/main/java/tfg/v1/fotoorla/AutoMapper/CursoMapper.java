package tfg.v1.fotoorla.AutoMapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import tfg.v1.fotoorla.DTOModel.DTOCurso;
import tfg.v1.fotoorla.DTOModel.DTOCursoCreacion;
import tfg.v1.fotoorla.Model.Curso;
import tfg.v1.fotoorla.Model.Persona;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CursoMapper {

    @Mapping(source = "usuario.id_usuario", target = "usuarioId")
    @Mapping(source = "personas", target = "personasIds", qualifiedByName = "personasToIds")
    DTOCurso cursoToCursoDTO(Curso curso);

    Curso cursoDTOToCurso(DTOCurso cursoDTO);

    @Named("personasToIds")
    default List<Long> personasToIds(List<Persona> personas) {
        return personas.stream()
                .map(Persona::getId)
                .collect(Collectors.toList());
    }


    DTOCurso cursoToCursoCreacionDTO(DTOCursoCreacion DTOCursoCreacion);
}
