package kr.bit.hotgirl.service;

import kr.bit.hotgirl.dto.UserDTO; // UserDTO 클래스를 사용하기 위한 임포트
import kr.bit.hotgirl.entity.User; // User Entity를 사용하기 위한 임포트
import kr.bit.hotgirl.repository.UserRepository; // UserRepository 인터페이스를 사용하기 위한 임포트
import lombok.RequiredArgsConstructor; // 필요한 생성자를 자동 생성하기 위한 Lombok 어노테이션
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException; // 데이터 무결성 예외 처리를 위한 임포트
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; // 비밀번호 암호화를 위한 임포트
import org.springframework.stereotype.Service; // Service 클래스임을 명시하는 어노테이션

import java.util.Optional;

@Slf4j
@Service // Service 클래스임을 명시하는 어노테이션
@RequiredArgsConstructor // 필요한 생성자를 자동 생성하기 위한 Lombok 어노테이션
public class UserService { // UserService 클래스 선언

    private final UserRepository userRepository; // UserRepository 인터페이스를 사용하기 위한 선언
    private final BCryptPasswordEncoder passwordEncoder; // 비밀번호 인코더 선언

    public UserDTO loginUser(String userId, String userPw) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            log.error("존재하지 않는 아이디입니다. userId: {}", userId);
            throw new UsernameNotFoundException("존재하지 않는 아이디입니다.");
        }

        User user = userOptional.get();
        if (!passwordEncoder.matches(userPw, user.getUserPw())) {
            log.error("비밀번호가 일치하지 않습니다. userId: {}", userId);
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
        }

        return UserDTO.builder()
                .userId(user.getUserId())
                .userIdx(user.getUserIdx())
                .userName(user.getUserName())
                .userAddr(user.getUserAddr())
                .userAge(user.getUserAge())
                .userGender(user.getUserGender())
                .build();
    }

    // 사용자 등록 기능을 하는 메소드
    public UserDTO registerUser(UserDTO userDTO) {
        if (userRepository.existsById(userDTO.getUserId())) { // 아이디 중복 검사
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }

        String encodedPassword = passwordEncoder.encode(userDTO.getUserPw()); // 비밀번호 암호화

        // User 객체 생성. builder()를 통해 각 필드를 설정하고 build()를 통해 User 객체를 생성한다.
        User user = User.builder()
                .userId(userDTO.getUserId())
                .userPw(encodedPassword) // 암호화된 비밀번호 저장
                .userName(userDTO.getUserName())
                .userAddr(userDTO.getUserAddr())
                .userAge(userDTO.getUserAge())
                .userGender(userDTO.getUserGender())
                .build();

        try {
            User savedUser = userRepository.save(user); // User 객체 저장 후 반환된 savedUser 사용
            return UserDTO.builder()
                    .userIdx(savedUser.getUserIdx()) // userIdx 값 설정
                    .userId(savedUser.getUserId())
                    .userName(savedUser.getUserName())
                    .userAddr(savedUser.getUserAddr())
                    .userAge(savedUser.getUserAge())
                    .userGender(savedUser.getUserGender())
                    .build();
        } catch (DataIntegrityViolationException e) { // 데이터 무결성 예외 처리 (예: 중복된 아이디)
            throw new IllegalArgumentException("회원가입에 실패했습니다. 다시 시도해주세요.");
        }
    }

    public boolean checkDuplicateUserId(String userId) {
        return userRepository.existsById(userId);
    }

    public UserDTO getUserById(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다."));
        return UserDTO.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .userAddr(user.getUserAddr())
                .userAge(user.getUserAge())
                .userGender(user.getUserGender())
                .build();
    }

    public void updateUser(UserDTO userDTO) {
        User user = userRepository.findById(userDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다."));

        if (userDTO.getUserPw() != null && !userDTO.getUserPw().isEmpty()) {
            String encodedPassword = passwordEncoder.encode(userDTO.getUserPw());
            user.setUserPw(encodedPassword);
        }

        user.setUserName(userDTO.getUserName());
        user.setUserAge(userDTO.getUserAge());
        user.setUserGender(userDTO.getUserGender());
        user.setUserAddr(userDTO.getUserAddr());

        userRepository.save(user);
    }

    public void withdrawalUser(String userId) {
        userRepository.deleteById(userId);
    }
}

