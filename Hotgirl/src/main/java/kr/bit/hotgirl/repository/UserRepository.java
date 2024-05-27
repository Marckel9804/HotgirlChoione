package kr.bit.hotgirl.repository;


import kr.bit.hotgirl.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    // 아이디로 사용자 정보 조회 (Optional 사용)
    Optional<User> findById(String userId);

    // 아이디 중복 확인
    boolean existsById(String userId);
}