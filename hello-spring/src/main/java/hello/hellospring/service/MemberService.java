package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional // jpa를 쓰려면 항상 @Transactional 어노테이션이 있어야한다. 데이터를 저장하거나 변경할 때 항상 필요
//@Service // @Service를 쓰면 스프링 컨테이너에 멤버 서비스를 등록해줌 참고로 Service안에 Component 어노테이션이 있음
public class MemberService {

    private final MemberRepository memberRepository;

//    @Autowired // 스프링 컨테이너에 있는 멤버 리포지토리를 넣어줌
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    // 회원가입
    public Long join(Member member){
        validateDuplicateMember(member); // 같은 이름이 있는 중복 회원X
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                        .ifPresent(m -> { // ifPresent는 result에 값이 있으면 이라는 말 Optional을 한번 감싸면 이렇게 if문 안쓰고 ifPresent 이런걸 쓸 수 있다.
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });
    }

    // 전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}
