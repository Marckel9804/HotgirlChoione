package kr.bit.hotgirl.login.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class Member {

    //DB에서 저장되어 관리되는 id (구분하기 위함)
    private Long id;

    @NotEmpty
    private String loginId;  //실제 사용자가 입력하는 id
    @NotEmpty
    private String name;
    @NotEmpty
    private String password;


}
