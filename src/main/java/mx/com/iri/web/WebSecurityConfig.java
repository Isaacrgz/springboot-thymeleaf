package mx.com.iri.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
            .authorizeHttpRequests((requests) -> requests
            .requestMatchers("/editar/**", "/agregar/**", "/eliminar").hasRole("ADMIN")
            .requestMatchers("/").hasAnyRole("USER", "ADMIN")
            .anyRequest().authenticated()
            )
            .formLogin((form) -> form
            .loginPage("/login")
            .permitAll()
            )
            .exceptionHandling((exceptionHandling) -> exceptionHandling
                    .accessDeniedPage("/errores/403")
            )
            .logout((logout) -> logout.permitAll());

    return http.build();
  }

  @Bean
  public UserDetailsService userDetailsService() {
    UserBuilder users = User.withDefaultPasswordEncoder();
    UserDetails user = users
            .username("user")
            .password("pass")
            .roles("USER")
            .build();
    UserDetails admin = users
            .username("admin")
            .password("pass")
            .roles("USER", "ADMIN")
            .build();

    return new InMemoryUserDetailsManager(user, admin);
  }
}
