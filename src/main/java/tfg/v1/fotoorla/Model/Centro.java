//package tfg.v1.fotoorla.Model;
//
//import jakarta.persistence.*;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.util.List;
//
//@Entity
//@Table(name = "Centro")
//@Data // Esta anotación de Lombok genera automáticamente getters, setters, toString, equals, y hashCode
//@NoArgsConstructor // Crea un constructor sin argumentos
//public class Centro {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long centro_id;
//
//    private String nombre;
//
//    private String direccion;
//
//    @OneToMany(mappedBy = "centro")
//    private List<Persona> personas;
//
//    public Long getCentro_id() {
//        return centro_id;
//    }
//
//    public void setCentro_id(Long centro_id) {
//        this.centro_id = centro_id;
//    }
//
//    public String getNombre() {
//        return nombre;
//    }
//
//    public void setNombre(String nombre) {
//        this.nombre = nombre;
//    }
//
//    public String getDireccion() {
//        return direccion;
//    }
//
//    public void setDireccion(String direccion) {
//        this.direccion = direccion;
//    }
//
//    public List<Persona> getPersonas() {
//        return personas;
//    }
//
//    public void setPersonas(List<Persona> personas) {
//        this.personas = personas;
//    }
//}