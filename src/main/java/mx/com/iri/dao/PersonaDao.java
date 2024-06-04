package mx.com.iri.dao;

import mx.com.iri.domain.Persona;
import org.springframework.data.repository.CrudRepository;

public interface PersonaDao extends CrudRepository<Persona, Integer>{

}
