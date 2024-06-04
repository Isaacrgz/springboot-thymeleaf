package mx.com.iri.service;

import java.util.List;
import mx.com.iri.domain.Persona;

public interface PersonaService {

  public List<Persona> listarPersona();
  
  public void guardar(Persona persona);
  
  public void eliminar(Persona persona);
  
  public Persona encontrarPersona(Persona persona);
}
