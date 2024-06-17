package tfg.v1.fotoorla.Security;//package tfg.v1.fotoorla.Security;//package tfg.v1.fotoorla.Security;//package tfg.v1.fotoorla.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity //habilita la seguridad web en la aplicacion de Spring Boot y proporciona la configuracion de seguridad por defecto
public class SecurityConfig {
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;


    @Autowired
    public SecurityConfig(JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint){
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    }
    @Bean
    public JwtAuthenticationEntryPoint jwtAunthenticationEntryPoint() {
        return new JwtAuthenticationEntryPoint();
    }

    //se encargara de verificar si el token es valido y si el usuario tiene los permisos necesarios para acceder a la solicitud
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    //encriptamos todas las contraseña de los usuarios
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //este bean incorporara el filtro de seguridad de json web token que creamos en la clase JwtAuthenticationFilter
    @Bean
    JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    //Establecerá una cadena de filtros de seguridad en nuestra aplicacion
    //Es aquí donde determinaremos los permisos de acceso a las rutas de nuestra aplicacion segun el rol del usuario
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http
                .csrf(csrf -> csrf.disable())
                .exceptionHandling(handling -> handling.authenticationEntryPoint(jwtAuthenticationEntryPoint))
                .sessionManagement(sesionmng -> sesionmng.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeRequests(authorize -> authorize.
                        requestMatchers("configuracionesOrla/**").permitAll().
                        requestMatchers("cursos/**").permitAll().
                        requestMatchers("orlas/**").permitAll().
                        requestMatchers("personas/**").permitAll().
                        requestMatchers("api/usuarios/**").permitAll().
                        requestMatchers("api/**").authenticated()
                        .anyRequest().authenticated());

        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }





}