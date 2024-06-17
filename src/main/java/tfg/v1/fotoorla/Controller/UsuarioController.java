package tfg.v1.fotoorla.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import tfg.v1.fotoorla.DTOModel.DTOAuthRespuesta;
import tfg.v1.fotoorla.DTOModel.DTOLogin;
import tfg.v1.fotoorla.DTOModel.DTORegistro;
import tfg.v1.fotoorla.Model.Roles;
import tfg.v1.fotoorla.Model.Usuario;
import tfg.v1.fotoorla.Repository.IRolesRepository;
import tfg.v1.fotoorla.Repository.IUsuarioRepository;
import tfg.v1.fotoorla.Security.JwtGenerator;
import tfg.v1.fotoorla.Service.UsuarioServiceImpl;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {
    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IRolesRepository rolesRepository;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private JwtGenerator jwtGenerador;


    @RequestMapping("/list")
    public List<Usuario> list() {
        return usuarioService.list();
    }

    @RequestMapping("/save")
    public void save(Usuario usuario) {
        usuarioService.save(usuario);
    }

    @RequestMapping("/delete")
    public void delete(Long id) {
        usuarioService.delete(id);
    }

    @RequestMapping("/get")
    public Usuario get(Long id) {
        return usuarioService.get(id);
    }

    @RequestMapping("/loadbyUsername")
    public Usuario loadbyUsername(String username) {
        return usuarioService.loadbyUsername(username);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registrar(@RequestBody DTORegistro dtoRegistro) {
        if (usuarioService.existsByUsername(dtoRegistro.getUsername())) {
            return new ResponseEntity<>("el usuario ya existe, intenta con otro", HttpStatus.BAD_REQUEST);
        }
        Usuario usuarios = new Usuario();
        usuarios.setUsername(dtoRegistro.getUsername());
        usuarios.setPassword(passwordEncoder.encode(dtoRegistro.getPassword()));
        Roles roles = rolesRepository.findByRole("USER");
        usuarios.setRoles(Collections.singletonList(roles));
        usuarioService.save(usuarios);
        return new ResponseEntity<>("Registro de usuario exitoso", HttpStatus.OK);
    }

    @PostMapping("/registerAdm")
    public ResponseEntity registerAdm(@RequestBody DTORegistro dtoRegistro){
        if(usuarioService.existsByUsername(dtoRegistro.getUsername())){
            return ResponseEntity.badRequest().body("Error: UUusuario existe, elige otro!");
        }
        Usuario usuarios = new Usuario();

        usuarios.setUsername(dtoRegistro.getUsername());
        usuarios.setPassword(passwordEncoder.encode(dtoRegistro.getPassword()));
        Roles roles = rolesRepository.findByRole("ADMIN");
        usuarios.setRoles(Collections.singletonList(roles));
        usuarioService.save(usuarios);
        return ResponseEntity.ok("Usuario registrado correctamente");
    }

    @PostMapping("login")
    public ResponseEntity<DTOAuthRespuesta> login(@RequestBody DTOLogin dtoLogin){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                dtoLogin.getUsername(), dtoLogin.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerador.generarToken(authentication);
        return new ResponseEntity<>(new DTOAuthRespuesta(token), HttpStatus.OK);
    }

}
