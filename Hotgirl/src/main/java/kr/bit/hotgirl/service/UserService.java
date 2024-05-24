package kr.bit.hotgirl.service;

import kr.bit.hotgirl.dto.UserDTO; // UserDTO 클래스를 사용하기 위한 임포트
import kr.bit.hotgirl.entity.User; // User Entity를 사용하기 위한 임포트
import kr.bit.hotgirl.repository.UserRepository; // UserRepository 인터페이스를 사용하기 위한 임포트
import lombok.RequiredArgsConstructor; // 필요한 생성자를 자동 생성하기 위한 Lombok 어노테이션
import org.springframework.dao.DataIntegrityViolationException; // 데이터 무결성 예외 처리를 위한 임포트
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; // 비밀번호 암호화를 위한 임포트
import org.springframework.stereotype.Service; // Service 클래스임을 명시하는 어노테이션

@Service // Service 클래스임을 명시하는 어노테이션
@RequiredArgsConstructor // 필요한 생성자를 자동 생성하기 위한 Lombok 어노테이션
public class UserService { // UserService 클래스 선언

    private final UserRepository userRepository; // UserRepository 인터페이스를 사용하기 위한 선언
    private final BCryptPasswordEncoder passwordEncoder; // 비밀번호 인코더 선언

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
            User savedUser = userRepository.save(user); // User 객체 저장
            // UserDTO 객체 생성. builder()를 통해 필드를 설정하고 build()를 통해 UserDTO 객체를 생성한다.
            return UserDTO.builder()
                    .userId(savedUser.getUserId())
                    .build();
        } catch (DataIntegrityViolationException e) { // 데이터 무결성 예외 처리 (예: 중복된 아이디)
            throw new IllegalArgumentException("회원가입에 실패했습니다. 다시 시도해주세요.");
        }
    }

    public boolean checkDuplicateUserId(String userId) {
        return userRepository.existsById(userId);
    }

}

