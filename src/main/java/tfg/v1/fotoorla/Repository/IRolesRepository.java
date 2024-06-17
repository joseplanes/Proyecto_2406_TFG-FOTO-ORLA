package tfg.v1.fotoorla.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import tfg.v1.fotoorla.Model.Roles;

@EnableJpaRepositories
@Repository
public interface IRolesRepository extends JpaRepository<Roles, Long>
{
    Roles findByRole(String role);

}
