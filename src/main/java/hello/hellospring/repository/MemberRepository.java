package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); //회원 저장소에 저장
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);

    List<Member> findAll(); //모든 회원 리싀트 반환


}
