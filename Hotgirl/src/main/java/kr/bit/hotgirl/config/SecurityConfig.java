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

@Configuration // 스프링 설정 파일임을 명시하는 어노테이션
@EnableWebSecurity // 웹 보안 기능을 활성화하는 어노테이션
public class SecurityConfig {

    @Autowired // 클래스에 필요한 의존성을 자동으로 주입하는 어노테이션
    private UserRepository userRepository;

    @Bean // 스프링 빈 객체를 생성하는 어노테이션
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        // 특정 경로에 대한 권한 설정. permitAll()이 통과되면 모든 권한을 가진다.
                        .requestMatchers("/", "/main", "/User/register", "/User/login", "/logout","/maps/maptest",
                                "/User/check-duplication","/User/login?error", "/User/login?logout", "/User/", "/User/mypage",
                                "/resources/**","/resources/static/**", "/maps/maptest11", "maps/maptest22").permitAll()
                        .anyRequest().authenticated() // 모든 요청에 대해 인증된 사용자만 접근 가능
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/User/login") // 로그인 페이지 경로 설정
                        .loginProcessingUrl("/login") // 로그인 처리 URL 설정
                        .usernameParameter("userId") // 사용자명 파라미터 설정
                        .passwordParameter("userPw") // 비밀번호 파라미터 설정
                        .defaultSuccessUrl("/", true) // 로그인 성공 시 리다이렉트할 URL 설정
                        .failureUrl("/User/login?error") // 로그인 실패 시 리다이렉트할 URL 설정
                        .failureHandler(customAuthenticationFailureHandler()) // 로그인 실패 시 동작할 핸들러 설정
                        .permitAll() // 모든 권한을 가진 사용자에게 접근 허용
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // 로그아웃 URL 설정
                        .logoutSuccessUrl("/User/login?logout") // 로그아웃 성공 시 리다이렉트할 URL 설정
                        .permitAll() // 모든 권한을 가진 사용자에게 접근 허용
                )
                .csrf(csrf -> csrf
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()) // CSRF 토큰 저장소 설정
                )
                .exceptionHandling(exceptions -> exceptions
                        .accessDeniedPage("/access-denied") // 접근 권한이 없을 때 리다이렉트할 URL 설정
                )
                .authenticationProvider(authenticationProvider()); // 인증 제공자 설정

        return http.build(); // 설정된 HttpSecurity 객체를 반환
    }

    @Bean // 스프링 빈 객체를 생성하는 어노테이션
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService()); // 사용자 정보 서비스 설정
        provider.setPasswordEncoder(passwordEncoder()); // 비밀번호 암호화 설정
        return provider; // 설정된 DaoAuthenticationProvider 객체 반환
    }

    @Bean // 스프링 빈 객체를 생성하는 어노테이션
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService(userRepository); // 사용자 정보 서비스 반환
    }

    @Bean // 스프링 빈 객체를 생성하는 어노테이션
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // BCrypt 암호화 객체 반환
    }

    @Bean // 스프링 빈 객체를 생성하는 어노테이션
    public CustomAuthenticationFailureHandler customAuthenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler(); // 커스텀 인증 실패 핸들러 반환
    }
}
