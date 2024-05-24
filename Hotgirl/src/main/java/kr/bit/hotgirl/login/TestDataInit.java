package kr.bit.hotgirl.login;


import jakarta.annotation.PostConstruct;
import kr.bit.hotgirl.login.dto.Member;
import kr.bit.hotgirl.login.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final MemberRepository memberRepository;

    @PostConstruct
    public void init() {

        Member member = new Member();
        member.setLoginId("test");
        member.setPassword("test!");
        member.setName("테스트임미다");
        memberRepository.save(member);

    }
}
