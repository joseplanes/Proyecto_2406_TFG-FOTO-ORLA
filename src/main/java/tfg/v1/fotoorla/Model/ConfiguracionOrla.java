package tfg.v1.fotoorla.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "ConfiguracionOrla")
public class ConfiguracionOrla {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "orla_id")
    private Orla orla;

    @ManyToOne
    @JoinColumn(name = "persona_id")
    private Persona persona;

    private String posicion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Orla getOrla() {
        return orla;
    }

    public void setOrla(Orla orla) {
        this.orla = orla;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }
}
