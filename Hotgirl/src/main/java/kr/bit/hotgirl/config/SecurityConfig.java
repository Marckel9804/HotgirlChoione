package kr.bit.hotgirl.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/", "/main", "/User/register"
                               , "maps/maptest", "/maps/maptest11", "/maps/maptest22"
                                ,"/User/loginProcess",  "/User/login", "/User/user/check-duplication",
                                "/User/login?error", "/User/login?logout", "/User/", "/User/mypage",
                                "/User/userEdit", "/User/userEdit?error", "/User/userEdit?success",
                                "/User/userDelete", "/User/userDelete?error", "/User/userDelete?success",
                                "/User/mypage", "resources/**", "/css/**", "/js/**", "/images/**", "/fonts/**"
                        ).permitAll()
                        .anyRequest().authenticated())
                .formLogin((form) -> form
                        .loginPage("/User/login")
                        .loginProcessingUrl("/User/loginProcess") // login-process
                        .usernameParameter("userId")
                        .passwordParameter("userPw")
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/User/login?error")
                        .permitAll()
                )
                .logout((logout) -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/User/login?logout")
                        .permitAll()
                )
//               .csrf((csrf) -> csrf.disable()); // CSRF 설정 추가)
                .csrf((csrf) -> csrf // CSRF 설정 추가
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                )
                .exceptionHandling((exceptions) -> exceptions
                        .accessDeniedPage("/access-denied")
                );
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SimpleUrlAuthenticationFailureHandler authenticationFailureHandler() {
        return new SimpleUrlAuthenticationFailureHandler("/login?error"); // 로그인 실패 시 에러 파라미터 추가
    }
}
