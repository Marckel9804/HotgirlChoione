package kr.bit.hotgirl.repository;


import kr.bit.hotgirl.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}