package kr.bit.hotgirl.entity; // 패키지 이름. 프로젝트의 구조에 따라 변경해야 함.

import jakarta.persistence.*; // JPA 기능을 사용하기 위한 임포트
import jakarta.validation.constraints.*; // 검증 어노테이션을 사용하기 위한 임포트
import lombok.*; // Lombok 라이브러리. Getter, Setter, Builder 등을 자동 생성하기 위한 임포트

@Entity // JPA Entity임을 선언하는 어노테이션
@Table(name = "user") // DB 테이블 이름을 명시하는 어노테이션
@Getter @Setter // Lombok의 Getter와 Setter를 자동 생성하는 어노테이션
@Builder // Lombok의 Builder 패턴 사용을 위한 어노테이션
@NoArgsConstructor @AllArgsConstructor // Lombok의 기본 생성자와 모든 필드를 포함한 생성자를 자동 생성하는 어노테이션
public class User {

    @Id // 기본키임을 선언하는 어노테이션
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 생성 전략을 명시하는 어노테이션
    @Column(name = "user_idx") // 컬럼 이름을 명시하는 어노테이션
    private Long userIdx;

    @Column(name = "user_id", length = 50, unique = true, nullable = false) // 사용자 ID. 고유하며, Null이 될 수 없음.
    private String userId;

    @Column(name = "user_pw", length = 100, nullable = false) // 사용자 비밀번호. Null이 될 수 없으며, 암호화를 고려하여 길이를 늘림.
    private String userPw;

    @Column(name = "user_name", length = 50, nullable = false) // 사용자 이름. Null이 될 수 없음.
    private String userName;

    @Column(name = "user_addr", length = 200) // 사용자 주소.
    private String userAddr;

    @Column(name = "user_age", nullable = false) // 사용자 나이. Null이 될 수 없음.
    private Integer userAge;

    @Column(name = "user_gender", length = 20) // 사용자 성별.
    private String userGender;

}
