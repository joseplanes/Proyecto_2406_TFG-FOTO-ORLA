package tfg.v1.fotoorla.DTOModel;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOOrla {
    private Long id;
    private String nombre;
    private Date fechaCreacion;
    private Long cursoId;
    private Long usuarioId;
    private List<Long> personasIds;
}
