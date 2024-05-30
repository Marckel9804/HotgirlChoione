package kr.bit.hotgirl.component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

@Slf4j // Lombok의 로그 관련 어노테이션. 이 어노테이션을 통해 로그를 기록할 수 있다.
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler { // 인증 실패 시 동작할 핸들러 클래스

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.error(exception.getMessage()); // 에러 메시지 기록
        log.info("로그인 실패"); // 로그인 실패 정보 기록
        System.out.println("로그인 실패"); // 콘솔에 로그인 실패 출력
        response.sendRedirect(request.getContextPath() + "/login?error"); // 로그인 실패 시, 로그인 페이지로 리다이렉트
        System.out.println(request.getParameter("userId")); // 콘솔에 로그인 시도한 사용자 ID 출력

        // 여기에 추가적인 로그 기록 코드를 작성할 수 있습니다.
    }
}

