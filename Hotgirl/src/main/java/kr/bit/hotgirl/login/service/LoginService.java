package kr.bit.hotgirl.login.service;


import kr.bit.hotgirl.login.dto.Member;
import kr.bit.hotgirl.login.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;


    //@return null 이면 로그인 실패
    public Member login(String loginId, String password) {
//        Optional<Member> findMemberOptional = memberRepository.findByLoginId(loginId);
//        Member member = findMemberOptional.get(); // Optional에서 get을 쓰면 member안에 있는게 꺼내져서 나옴, 없으면 null
//        if (member.getPassword().equals(password)) {
//            return member;
//        } else {
//            return null;
//        }

        return memberRepository.findByLoginId(loginId)
                .filter(member -> member.getPassword().equals(password))
                .orElse(null);


    }

}

//로그인의 핵심 비즈니스 로직 => 회원을 조회한 당므에 파라미터로 넘어온 password와 비교해서 같으면 회원을 반환, 다르면 null반환