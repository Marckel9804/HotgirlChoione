package kr.bit.hotgirl.login.repository;

import kr.bit.hotgirl.login.dto.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
public class MemberRepository {

    private static Map<Long, Member> hotgirl = new HashMap<>(); // static 사용
    private static long sequence = 0L; // static 사용

    public Member save(Member member) {
        member.setId(++sequence);
        log.info("save: member={}", member);
        hotgirl.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return hotgirl.get(id);
    }

    public Optional<Member> findByLoginId(String loginId) {
//        List<Member> all = findAll();
//        for (Member member : all) {
//            if (member.getLoginId().equals(loginId)) {
//                return Optional.of(member);
//            }
//        }
//        return Optional.empty();
//    }

        return findAll().stream() // list를 stream 으로 바꿈
                .filter(member -> member.getLoginId().equals(loginId)) // 필터로 조건 형성
                .findFirst(); // 먼저 나오는애 반환시키기
    }

    public List<Member> findAll() {
        return new ArrayList<>(hotgirl.values());
    }

public void clearHotgirl() {
        hotgirl.clear();
}
}