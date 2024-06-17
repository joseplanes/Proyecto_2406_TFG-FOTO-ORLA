package tfg.v1.fotoorla.AutoMapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tfg.v1.fotoorla.DTOModel.DTOConfiguracionOrla;
import tfg.v1.fotoorla.Model.ConfiguracionOrla;

@Mapper(componentModel = "spring")
public interface ConfiguracionOrlaMapper {

    @Mapping(source = "orla.id", target = "orlaId")
    @Mapping(source = "persona.id", target = "personaId")
    DTOConfiguracionOrla configuracionOrlaToDTO(ConfiguracionOrla configuracionOrla);

    ConfiguracionOrla dtoToConfiguracionOrla(DTOConfiguracionOrla configuracionOrlaDTO);
}
