package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach //메서드가 끝날때마다 어떤 동작을 하는 것 , savr 가 끝나면 동작, findById가 끝나면 동작...
    public void afterEach(){
        repository.clearStore(); 
    }
    //테스트는 서로 의존관계 없이 설계가 되어야 한다.
    //afterEach를 만든 이유는 
    //테스트가 돌 때 개개인적으로 테스트 했을때는 잘 되었었는데 클래스 전체로 Test 실행했을때 findByName 메서트가 실패가 뜸
    // 이유는 findAll()가 먼저 실행이 되면서 객체에 spring1, spring2라는게 저장이 이미 되어 있어서 findBYName을 실행했을때 에러 발생
    // 테스트 한개 돌고나서 비워주는 작업
    //테스트가 돌아가는게 내가 원하는대로 돌아가지 않음.
    

    @Test
    public void save(){

        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        Member result = repository.findById(member.getId()).get();

        assertThat(member).isEqualTo(result);

    }


     @Test
    public void findByName(){
         Member member1 = new Member();
         member1.setName("spring1");
         repository.save(member1);


        Member member2 = new Member();
        member2.setName("spring2");
         repository.save(member2);

         Member result = repository.findByName("spring1").get();
         assertThat(result).isEqualTo(member1);

    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
