package kr.bit.hotgirl.dto; // 패키지 이름

import jakarta.validation.constraints.*; // 검증 어노테이션을 사용하기 위한 임포트
import lombok.*; // Lombok 라이브러리 임포트

@Data // Lombok의 @Data 어노테이션은 @Getter, @Setter, @RequiredArgsConstructor, @ToString, @EqualsAndHashCode을 한꺼번에 설정
@Builder // Lombok의 Builder 패턴 사용을 위한 어노테이션
@AllArgsConstructor // Lombok의 모든 필드를 포함한 생성자를 자동 생성하는 어노테이션
@NoArgsConstructor // Lombok의 기본 생성자를 자동 생성하는 어노테이션
public class UserDTO { // UserDTO 클래스 선언
    @NotBlank(message = "아이디는 필수 입력 값입니다.") // 아이디 필드는 비어있을 수 없음을 명시하는 검증 어노테이션
    @Size(min = 4, max = 20, message = "아이디는 4~20자 사이여야 합니다.") // 아이디 필드의 길이는 4~20자 사이여야 함을 명시하는 검증 어노테이션
    private String userId; // 아이디 필드

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.") // 비밀번호 필드는 비어있을 수 없음을 명시하는 검증 어노테이션
    @Size(min = 8, max = 20, message = "비밀번호는 8~20자 사이여야 합니다.") // 비밀번호 필드의 길이는 8~20자 사이여야 함을 명시하는 검증 어노테이션
    private String userPw; // 비밀번호 필드

    @NotBlank(message = "이름은 필수 입력 값입니다.") // 이름 필드는 비어있을 수 없음을 명시하는 검증 어노테이션
    private String userName; // 이름 필드

    private String userAddr; // 주소 필드

    @NotNull(message = "나이는 필수 입력 값입니다.") // 나이 필드는 Null이 될 수 없음을 명시하는 검증 어노테이션
    @Min(value = 0, message = "나이는 0 이상이어야 합니다.") // 나이 필드의 값은 0 이상이어야 함을 명시하는 검증 어노테이션
    private Integer userAge; // 나이 필드

    @NotBlank(message = "성별은 필수 입력 값입니다.") // 성별 필드는 비어있을 수 없음을 명시하는 검증 어노테이션
    private String userGender; // 성별 필드
}
