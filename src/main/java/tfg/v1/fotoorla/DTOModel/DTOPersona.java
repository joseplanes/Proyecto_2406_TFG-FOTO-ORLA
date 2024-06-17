package tfg.v1.fotoorla.DTOModel;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class DTOPersona {
    private Long id;
    private String nombre;
    private String apellidos;
    private String imagen;
    private String tipo;
    private Long cursoId;
    private List<Long> orlasIds; // Lista de IDs de orlas asociadas a la persona
}
