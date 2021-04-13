package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller // 컨트롤러 어노테이션을 쓰면 스프링 컨테이너가 컨트롤러 객체를 생성해서 넣어두고 관리함 -> 빈이 관리됨
public class MemberController {

//    private final MemberService memberService = new MemberService(); // 이렇게 생성을 필요할때 쓰는게 아니라 MemberService 내에는 별로 내용이 없으니까 스프링 컨테이너에 등록하면 하나만 등록되고 공용으로 쓰는게 낫다.

    private final MemberService memberService;

    @Autowired // Autowired가 있으면 스프링이 컨테이너의 멤버 서비스를 가져다 연결 시켜줌 이게바로 DI임 생성자 방식의 의존관계 주입임, setter 방식의 주입은 public으로 되있어서 누가 바꿀 수 있기 때문에 권장 x
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

}
