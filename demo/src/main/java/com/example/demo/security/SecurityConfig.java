package security;

import repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserRepository userRepository;

    public SecurityConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Password encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Combined UserDetailsService (DB + in-memory fallback)
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        // In-memory test user
        UserDetails inMemoryUser = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin123"))
                .roles("ADMIN")
                .build();

        InMemoryUserDetailsManager inMemoryManager = new InMemoryUserDetailsManager(inMemoryUser);

        return username -> {
            // First try DB
            return userRepository.findByUsername(username)
                    .map(user -> User.withUsername(user.getUsername())
                            .password(user.getPassword())
                            .roles("USER")
                            .build())
                    // If not in DB, fallback to in-memory
                    .orElse(inMemoryManager.loadUserByUsername(username));
        };
    }

    // Authentication Manager
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    // Security rules
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/orders/**", "/api/offers/**").authenticated()
                        .anyRequest().permitAll()
                )
                .formLogin(form -> form.permitAll())
                .httpBasic(httpBasic -> {});

        return http.build();
    }
}




