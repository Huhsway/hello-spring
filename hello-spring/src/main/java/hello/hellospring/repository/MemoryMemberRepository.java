package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; // sequence는 0,1,2... 이런 키 값을 생성해준다.

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // NULL 값일 경우를 대비해서 Optional.ofNullable로 감싸서 반환해주면 클라이언트에서 처리할 수 있음
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() // stream은 for문 loop 돌리는거라고 생각하자
                .filter(member -> member.getName().equals(name)) // filter를 통해서 같은 경우로 필터링
                .findAny(); // 찾으면 반환 findAny는 하나라도 찾는거임 만약 끝까지 찾아도 없으면 Optional로 감싸서 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }

}
