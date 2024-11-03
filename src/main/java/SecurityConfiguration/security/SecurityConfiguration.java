package SecurityConfiguration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desabilita CSRF
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Define o gerenciamento de sessão como stateless
                )
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers("/").permitAll() // Permite acesso sem autenticação
                                .anyRequest().authenticated() // Requer autenticação para qualquer outra requisição
                );

        return http.build(); // Retorna a configuração construída
    }
}
