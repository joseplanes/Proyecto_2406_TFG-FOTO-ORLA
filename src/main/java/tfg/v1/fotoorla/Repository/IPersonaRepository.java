package tfg.v1.fotoorla.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tfg.v1.fotoorla.Model.Persona;

import java.util.List;

@Repository
public interface IPersonaRepository extends JpaRepository<Persona, Long> {
    List<Persona> findByCursoId(Long cursoId);
}
