package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hihi!!");
        return "hello"; // templates에 있는 hello.html의 이름 hello에 리턴하겠으니 위에 렌더링 하라는 뜻
    }

    @GetMapping("hello-mvc") // html로 넘기는 방식
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string") // api로 넘기는 방식식
   @ResponseBody // http 프로토콜 body 부분에 직접 데이터를 넣어 주겠다
    public String helloString(@RequestParam("name") String name){
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody // ResponseBody는 JSON으로 반환이 디폴트
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello; // 객체를 반환하면 JSON으로 반환된다 HttpMessageConverter의 JsonConvert를 통해서 객체가 Json 형식으로 반환 된다.
    }

    static class Hello { // static class로로 만들면 클래 안에 또 클래스 쓸 수 있다.
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
