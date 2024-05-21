package kr.bit.hotgirl.entity; // package 이름은 프로젝트 구조에 맞게 수정해야 합니다.

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user")
@Getter @Setter
@Builder // 빌더 패턴을 사용하려면 @Builder 어노테이션을 추가해야 합니다.
@NoArgsConstructor @AllArgsConstructor // 롬복의 @NoArgsConstructor, @AllArgsConstructor 어노테이션을 추가하여 생성자를 자동 생성합니다.
public class User {

    @Id
    @Column(name = "user_id", length = 50) // userId 컬럼 길이 설정
    private String userId; // String 타입으로 변경

    @Column(name = "user_pw", nullable = false, length = 50)
    private String userPw;

    @Column(name = "user_name", nullable = false, length = 50)
    private String userName;

    @Column(name = "user_addr", length = 200)
    private String userAddr;

    @Column(name = "user_age", nullable = false)
    private int userAge;

    @Column(name = "user_gender", length = 20)
    private String userGender;
}
