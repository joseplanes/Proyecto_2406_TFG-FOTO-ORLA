package tfg.v1.fotoorla.Controller;

import org.apache.coyote.Request;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import tfg.v1.fotoorla.Security.CustomAuthorizationManager;

import java.util.function.Supplier;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomAuthorizationManager<Request> customAuthorizationManager;



    @Test
    public void testSecureEndpointWithAuthorization() throws Exception {
        // Simulamos que la verificación siempre es exitosa
        Mockito.doNothing().when(customAuthorizationManager).verify((Supplier) any(), any());

        mockMvc.perform(get("/api/auth/secure"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().string("Información segura accesible"));
    }

    @Test
    public void testSecureEndpointWithoutAuthorization() throws Exception {
        // Simulamos que la verificación falla con una excepción de acceso denegado
        Mockito.doThrow(new AccessDeniedException("Acceso Denegado")).when(customAuthorizationManager).verify((Supplier) any(), any());

        mockMvc.perform(get("/api/auth/secure"))
                .andExpect(status().isForbidden()  );
    }
}
