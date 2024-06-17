package tfg.v1.fotoorla.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtGenerator {
    //Metodo para generar el token por medio de la autehnticacion
    public String generarToken(Authentication authentication) {
            String username = authentication.getName();
            Date tiempoActual = new Date();
            Date expiracionToken = new Date(tiempoActual.getTime() + ConstantesSeguridad.JWT_EXPIRATION_TOKEN);

        //generar token
        String token = Jwts.builder()
                .setSubject(username) //nombre de usuario
                .setIssuedAt(tiempoActual) //fecha de creacion del token
                .setExpiration(expiracionToken) //fecha de expiracion del token
                .signWith(SignatureAlgorithm.HS512, ConstantesSeguridad.JWT_SECRET)
                //firma el token con la clave secreta que solo el servidor conoce
                .compact(); //construye el token

        return token;
    }

    //metodo para extrar username a partir de un token
    public String obtenerUsernameDeJwt(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(ConstantesSeguridad.JWT_SECRET) //clave secreta
                .parseClaimsJws(token) //confirma que el token es valido y no ha sido modificado
                .getBody(); //obtiene el cuerpo del token

        return claims.getSubject(); //obtiene el username del token
    }


    //metodo para validar el token
    public boolean validarToken(String token) {
        try {
            Jwts.parser().setSigningKey(ConstantesSeguridad.JWT_SECRET).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            throw new AuthenticationCredentialsNotFoundException("Token invalido" + e.getStackTrace());
        }
    }

}
