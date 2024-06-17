package tfg.v1.fotoorla.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tfg.v1.fotoorla.DTOModel.DTOResponse;
import tfg.v1.fotoorla.Model.Curso;

@EnableJpaRepositories
@Repository
public interface ICursoRepository extends JpaRepository<Curso, Long> {

    @Query("SELECT c.nombreCurso FROM Curso c WHERE c.id = :id")
    DTOResponse getNombrePorId(@Param("id") Long id);
}