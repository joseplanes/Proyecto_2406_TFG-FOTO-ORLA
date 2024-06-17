package tfg.v1.fotoorla.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tfg.v1.fotoorla.Model.Usuario;
import tfg.v1.fotoorla.Repository.IUsuarioRepository;

import java.util.List;

@Service
@Transactional
public class UsuarioServiceImpl {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    public List<Usuario> list() {
        return usuarioRepository.findAll();
    }

    public void save(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario get(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario loadbyUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    public boolean existsByUsername(String username) {
        return usuarioRepository.existsByUsername(username);
    }
}
