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
        model.addAttribute("data", "hello!!!!!!!!!!!!");
        return "hello"; //템플릿 밑에 hello.html에 있는 것을 찾아서 data를 렌더링
        }
        // 컨트롤러에 리턴값으로 문자를 반환하면 뷰 리졸버(viewResolver)가 화면을 찾아서 처리한다.

        @GetMapping("hello-mvc")          // 외부에서 파라미터를 받을때 Requestparam으로 받음 ,이름을 url파람으로 바꿈, model에 담음 
        public String helloMvc(@RequestParam("name") String name, Model model){
                model.addAttribute("name", name); //파라미터에서 넘겨온 name을 넘김
                return "hello-template"; //템플릿html로 넘어감

                //localhost:8080/hello-mvc?name=spring!!!!!!이라고 하면  name에 spring!!!!!!이 들어가서 나옴
        }

        //api방식
        @GetMapping("hello-string")
        @ResponseBody //http에서 통신 프로토콜에 body부분에 return 데이터를 직접 넣어 주겠다.
        public String helloString(@RequestParam("name") String name){
                return "hello"+name; // "hello spring"
        }


        @GetMapping("hello-api")
        @ResponseBody
        public Hello helloApi(@RequestParam("name") String name){
                Hello hello = new Hello();
                hello.setName(name);
                return hello;
        }
        static class Hello{
                private String name;
                public String getName() {
                        return name;
                }
                public void setName(String name) {
                        this.name = name;
                }
        }
        /*
        @ResponseBody를 사용
        http의 body에 문자 내용을 직접 반환
        viewResolver대신에 http MessageConverter가 동작
        기본 문자 처리 StringHttpMessageConverter
        기본 객체 MappingJackson2HttpMessageConverter
        byte 처리 등등 기타 여러 httpMessageConverter가 기본으로 등록되어 있음
        * */


}
