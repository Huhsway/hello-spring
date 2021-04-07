package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();


    // 회원가ㅣㅂ
    public Long join(Member member){
        // 같은 이름이 있는 중복 회원X
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m -> { // ifPresent는 result에 값이 있으면 이라는 말말
           throw new IllegalStateException("이미 존재하는 회원입니다.")
        })

        memberRepository.save(member);
        return member.getId();
    }

}
