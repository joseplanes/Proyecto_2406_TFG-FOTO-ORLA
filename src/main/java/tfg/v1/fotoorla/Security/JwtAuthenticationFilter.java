package tfg.v1.fotoorla.Security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

//la funcion de este filtro es interceptar las peticiones y validar el token y si es valido se autentica al usuario en la solicitud
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtGenerator jwtGenerator;


    //Metodo para validar el token y autenticar al usuario
    private String getTokenRequest(HttpServletRequest request) {
        final String requestTokenHeader = request.getHeader("Authorization");
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            return requestTokenHeader.substring(7);
        }
        return null;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = getTokenRequest(request);
        if (token != null && this.jwtGenerator.validarToken(token)){ //si el token es valido
            String username = this.jwtGenerator.obtenerUsernameDeJwt(token); //obtenemos el username del token
            UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(username); //obtenemos los datos del usuario
            List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList(); //obtenemos los roles del usuario
            if(roles.contains("USER") || roles.contains("ADMIN")){//si el usuario tiene el rol de usuario o administrador
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, //autenticamos al usuario
                        null, userDetails.getAuthorities()); //con sus roles
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); //retomamos los detalles de la solicitud
                SecurityContextHolder.getContext().setAuthentication(authenticationToken); //autenticamos al usuario
            }

        }
        filterChain.doFilter(request, response); //continuamos con la solicitud
    }
}
