package kr.bit.hotgirl.service;

import kr.bit.hotgirl.dto.UserDTO;
import kr.bit.hotgirl.entity.User;
import kr.bit.hotgirl.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder; // 비밀번호 인코더

    public UserDTO registerUser(UserDTO userDTO) {
        if (userRepository.existsById(userDTO.getUserId())) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }

        String encodedPassword = passwordEncoder.encode(userDTO.getUserPw());

        User user = User.builder()
                .userId(userDTO.getUserId())
                .userPw(encodedPassword) // 암호화된 비밀번호 저장
                .userName(userDTO.getUserName())
                .userAddr(userDTO.getUserAddr())
                .userAge(userDTO.getUserAge())
                .userGender(userDTO.getUserGender())
                .build();

        try {
            User savedUser = userRepository.save(user);
            return UserDTO.builder()
                    .userId(savedUser.getUserId())
                    .build();
        } catch (DataIntegrityViolationException e) { // 데이터 무결성 예외 처리 (예: 중복된 아이디)
            throw new IllegalArgumentException("회원가입에 실패했습니다. 다시 시도해주세요.");
        }
    }
}
