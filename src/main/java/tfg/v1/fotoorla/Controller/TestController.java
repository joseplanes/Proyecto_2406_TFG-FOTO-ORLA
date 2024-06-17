package tfg.v1.fotoorla.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tfg.v1.fotoorla.Security.JwtGenerator;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class TestController {

    private final JwtGenerator jwtGenerator;



    public TestController(JwtGenerator jwtGenerator) {
        this.jwtGenerator = jwtGenerator;
    }

    @GetMapping("/generate-token")
    public ResponseEntity<String> generateToken(Authentication authentication) {
        String token = jwtGenerator.generarToken(authentication);
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }
    @GetMapping("/public")
    public ResponseEntity<String> publicEndpoint() {
        return ResponseEntity.ok("This is a public endpoint");
    }

    @GetMapping("/protected")
    public ResponseEntity<String> protectedEndpoint() {
        return ResponseEntity.ok("This is a protected endpoint");
    }



}
