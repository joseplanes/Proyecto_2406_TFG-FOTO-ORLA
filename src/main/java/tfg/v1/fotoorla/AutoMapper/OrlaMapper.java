package tfg.v1.fotoorla.AutoMapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tfg.v1.fotoorla.DTOModel.DTOOrla;
import tfg.v1.fotoorla.Model.Orla;

@Mapper(componentModel = "spring")
public interface OrlaMapper {

    @Mapping(source = "curso.id", target = "cursoId")
    @Mapping(source = "usuario.id_usuario", target = "usuarioId")
    DTOOrla orlaToOrlaDTO(Orla orla);

    Orla orlaDTOToOrla(DTOOrla orlaDTO);
}