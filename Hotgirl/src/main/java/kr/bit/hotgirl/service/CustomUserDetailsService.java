package kr.bit.hotgirl.service;

import kr.bit.hotgirl.entity.User;
import kr.bit.hotgirl.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service // 서비스 레이어, 비즈니스 로직을 처리함
@RequiredArgsConstructor // final이나 @NonNull 필드 값만 파라미터로 받는 생성자를 만듬
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository; // 레포지토리 레이어, DB와 상호작용

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException { // 사용자를 얻는 메소드, userId로 변경
        User user = userRepository.findById(userId) // userId로 사용자를 찾음
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다. userId: " + userId)); // 사용자를 찾지 못하면 예외 발생

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUserId()) // 사용자명 설정
                .password(user.getUserPw()) // 비밀번호 설정
                .roles("USER") // 사용자에게 "USER" 권한 부여
                .build(); // UserDetails 객체 생성
    }
}
