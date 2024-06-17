package tfg.v1.fotoorla.Repository;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import tfg.v1.fotoorla.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
@EnableJpaRepositories
@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
    <Optional>Usuario findByUsername(String username);
    Boolean existsByUsername(String username);
}
