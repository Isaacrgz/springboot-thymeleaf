package mx.com.iri.web;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import mx.com.iri.domain.Persona;
import mx.com.iri.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class InicioController {

  @Autowired
  private PersonaService personaService;

  @GetMapping("/")
  public String inicio(Model model) {
    var personas = personaService.listarPersona();

    log.info("Ejecutando el controlador Spring MCV");
    model.addAttribute("personas", personas);

    return "index";
  }
  
  @GetMapping("/agregar")
  public String agregar(Persona persona) {
    return "modificar";
  }
  
  @PostMapping("/guardar")
  public String guardar(@Valid Persona persona, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "modificar";
    }
    personaService.guardar(persona);
    return "redirect:/";
  }
  
  @GetMapping("/editar/{idPersona}")
  public String editar(Persona persona, Model model) {
    persona = personaService.encontrarPersona(persona);
    model.addAttribute("persona", persona);
    return "modificar";
  }
  
  @GetMapping("/eliminar")
  public String eliminar(Persona persona) {
    personaService.eliminar(persona);
    return "redirect:/";
  }
}
