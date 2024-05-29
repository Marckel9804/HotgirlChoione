package kr.bit.hotgirl.config;

import kr.bit.hotgirl.component.CustomAuthenticationFailureHandler;
import kr.bit.hotgirl.repository.UserRepository;
import kr.bit.hotgirl.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/main", "/User/register", "/User/login", "/logout","/maps/maptest",
                                "/User/check-duplication","/User/login?error", "/User/login?logout", "/User/", "/User/mypage",
                                "/resources/**","/resources/static/**", "/maps/maptest11", "maps/maptest22").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/User/login")
                        .loginProcessingUrl("/login")
                        .usernameParameter("userId")
                        .passwordParameter("userPw")
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/User/login?error")
                        .failureHandler(customAuthenticationFailureHandler())
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/User/login?logout")
                        .permitAll()
                )
                .csrf(csrf -> csrf
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                )
                .exceptionHandling(exceptions -> exceptions
                        .accessDeniedPage("/access-denied")
                )
                .authenticationProvider(authenticationProvider());

        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService()); // UserDetailsService 설정
        provider.setPasswordEncoder(passwordEncoder()); // PasswordEncoder 설정
        return provider;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService(userRepository);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CustomAuthenticationFailureHandler customAuthenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }
}
