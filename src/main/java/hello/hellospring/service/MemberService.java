package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository; //외부에서 넣어주도록 바꾸기
    }

    /*
    * 회원가입
    * */
    
    public Long join(Member member){
        //같은 이름이 있는 중복 회원X
        validateDuplicateMember(member);//중복회원 검증
        memberRepository.save(member);
        return member.getId();

    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()) //옵셔널을 감싸면 옵셔널 안에 member가 있는거
            .ifPresent(m -> {
               throw new IllegalStateException("이미 존재하는 회원입니다.");
           }); // ㅇㅣ부분 드래그 한다음 c+a+s+T 해서 7번 메서드 추출하면 -> 메서드로 따로 뺄 수 있음
    }
    
    /*
    *전체 회원조회
    * */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
