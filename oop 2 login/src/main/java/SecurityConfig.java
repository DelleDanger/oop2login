import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // CSRF ist standardmäßig aktiviert, wenn Sie es explizit deaktivieren müssen, tun Sie das hier
                .csrf(AbstractHttpConfigurer::disable)
                // Autorisierungsanforderungen konfigurieren
                .authorizeHttpRequests(authz -> authz
                        .dispatcherTypeMatchers(HttpMethod.valueOf("/login")).permitAll()
                        .anyRequest().authenticated())
                // Formularbasierte Anmeldung konfigurieren
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll())
                // Logout konfigurieren
                .logout(LogoutConfigurer::permitAll);

        return http.build();
    }
}
