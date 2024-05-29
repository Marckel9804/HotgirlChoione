package kr.bit.hotgirl.component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;



    @Slf4j
    public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
          log.error(exception.getMessage());
          log.info("로그인 실패");
          System.out.println("로그인 실패");
          response.sendRedirect(request.getContextPath() + "/login?error");
            System.out.println(request.getParameter("userId"));


            // 여기에 추가적인 로그 기록 코드를 작성할 수 있습니다.
        }
    }

