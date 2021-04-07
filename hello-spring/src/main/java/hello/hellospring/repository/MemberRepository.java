package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id); // Optional은 findById나 findByName을 해서 만약 없는 값이면 NULL을 반환할텐데 이 경우 Optional로 감싸서 반환하면 안전하다
    Optional<Member> findByName(String name);
    List<Member> findAll(); // 모든 회원 리스트 반환
}
