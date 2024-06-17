package tfg.v1.fotoorla.AutoMapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import tfg.v1.fotoorla.DTOModel.DTOPersona;
import tfg.v1.fotoorla.Model.ConfiguracionOrla;
import tfg.v1.fotoorla.Model.Persona;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PersonaMapper {

    @Mapping(source = "curso.id", target = "cursoId")
    @Mapping(source = "configuracionesOrla", target = "orlasIds", qualifiedByName = "configuracionesOrlaToIds")
    DTOPersona personaToPersonaDTO(Persona persona);

    Persona personaDTOToPersona(DTOPersona personaDTO);

    @Named("configuracionesOrlaToIds")
    default List<Long> configuracionesOrlaToIds(List<ConfiguracionOrla> configuracionesOrla) {
        return configuracionesOrla.stream()
                .map(ConfiguracionOrla::getId)
                .collect(Collectors.toList());
    }
}
