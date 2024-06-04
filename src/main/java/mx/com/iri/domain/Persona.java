package mx.com.iri.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name = "persona")
public class Persona implements Serializable {
  
  private static final long serialVersionUID = 1L;
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idpersona")
  private int idPersona;

  @NotEmpty
  private String nombre;
  
  @NotEmpty
  private String apellido;
  
  @NotEmpty
  @Email
  private String email;
  
  private String telefono;
  
}
