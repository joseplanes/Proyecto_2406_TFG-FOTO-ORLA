package tfg.v1.fotoorla.DTOModel;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//esta clase será la que nos devolvera el token y tipo de este token
@Data
@Getter
@Setter
public class DTOAuthRespuesta {
    private String accessToken;
    private String tokenType = "Bearer";

    public DTOAuthRespuesta(String accessToken) {
        this.accessToken = accessToken;
    }
}
