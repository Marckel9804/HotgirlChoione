package kr.bit.hotgirl.service;

import kr.bit.hotgirl.entity.User;
import kr.bit.hotgirl.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException { // userId로 변경
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다. userId: " + userId));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUserId())
                .password(user.getUserPw())
                .roles("USER") // 사용자에게 "USER" 권한 부여
                .build();
    }
}
