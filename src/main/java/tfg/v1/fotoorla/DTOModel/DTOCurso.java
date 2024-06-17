package tfg.v1.fotoorla.DTOModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOCurso {
    private Long id;
    private String nombreCurso;
    private Long usuarioId; // Id del usuario asociado al curso
    private List<Long> personasIds; // Lista de IDs de personas asociadas al curso
    private List<Long> orlasIds; // Lista de IDs de orlas asociadas al curso
}
