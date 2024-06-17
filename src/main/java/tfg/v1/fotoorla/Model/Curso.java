package tfg.v1.fotoorla.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Curso")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombreCurso;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "curso")
    @JsonIgnoreProperties("curso")
    private List<Persona> personas;

    @OneToMany(mappedBy = "curso")
    @JsonIgnoreProperties("curso")
    private List<Orla> orlas;

    // Método para obtener solo los nombres de las personas
    public List<String> getPersonasNombres() {
        return personas.stream()
                .map(Persona::getNombre)
                .collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }

    public List<Orla> getOrlas() {
        return orlas;
    }

    public void setOrlas(List<Orla> orlas) {
        this.orlas = orlas;
    }
}
