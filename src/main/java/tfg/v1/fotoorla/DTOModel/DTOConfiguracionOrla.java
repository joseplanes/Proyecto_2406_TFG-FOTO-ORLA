package tfg.v1.fotoorla.DTOModel;

import lombok.Data;

@Data
public class DTOConfiguracionOrla {
    private Long id;
    private Long orlaId;
    private Long personaId;
    private String posicion;
}