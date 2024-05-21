package kr.bit.hotgirl.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    @Size(min = 4, max = 20, message = "아이디는 4~20자 사이여야 합니다.")
    private String userId;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Size(min = 8, max = 20, message = "비밀번호는 8~20자 사이여야 합니다.")
    private String userPw;

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String userName;

    private String userAddr;

    @NotNull(message = "나이는 필수 입력 값입니다.")
    @Min(value = 0, message = "나이는 0 이상이어야 합니다.")
    private Integer userAge; // int -> Integer

    @NotBlank(message = "성별은 필수 입력 값입니다.")
    private String userGender;
}