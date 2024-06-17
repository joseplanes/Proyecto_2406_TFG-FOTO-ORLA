package tfg.v1.fotoorla.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;

import java.util.Collection;
import java.util.function.Supplier;

@EnableWebSecurity
public class CustomAuthorizationManager<HttpServletRequest> implements AuthorizationManager {

    private final AuthorizationManager<HttpServletRequest> authorizationManager;

    @Autowired
    public CustomAuthorizationManager(AuthorizationManager access) {
        this.authorizationManager = access;
    }
    public CustomAuthorizationManager(){
        //que no sea null
        this.authorizationManager = new CustomAuthorizationManager<>(); ;
    }


    @Override
    public AuthorizationDecision check(Supplier authenticationSupplier, Object object) {
        Authentication authentication = (Authentication) authenticationSupplier.get();
        // Solo verifica si el objeto Authentication no es nulo y está autenticado.
        boolean granted = authentication != null && authentication.isAuthenticated();
        return new AuthorizationDecision(granted);    }
}

