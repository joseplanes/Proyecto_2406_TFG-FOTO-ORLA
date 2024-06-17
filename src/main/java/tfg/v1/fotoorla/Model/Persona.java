    package tfg.v1.fotoorla.Model;

    import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
    import jakarta.persistence.*;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    import java.util.List;

    @Data
    @NoArgsConstructor
    @Entity
    @Table(name = "Persona")
    public class Persona {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String nombre;
        private String apellidos;
        private String imagen;
        private String tipo;

        @ManyToOne
        @JoinColumn(name = "curso_id")
        @JsonIgnoreProperties("personas")
        private Curso curso;

        @OneToMany(mappedBy = "persona")
        private List<ConfiguracionOrla> configuracionesOrla;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getApellidos() {
            return apellidos;
        }

        public void setApellidos(String apellidos) {
            this.apellidos = apellidos;
        }

        public String getImagen() {
            return imagen;
        }

        public void setImagen(String imagen) {
            this.imagen = imagen;
        }

        public String getTipo() {
            return tipo;
        }

        public void setTipo(String tipo) {
            this.tipo = tipo;
        }

        public Curso getCurso() {
            return curso;
        }

        public void setCurso(Curso curso) {
            this.curso = curso;
        }

        public List<ConfiguracionOrla> getConfiguracionesOrla() {
            return configuracionesOrla;
        }

        public void setConfiguracionesOrla(List<ConfiguracionOrla> configuracionesOrla) {
            this.configuracionesOrla = configuracionesOrla;
        }
    }
