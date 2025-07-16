package egovframework.example.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    // 이메일로 회원 찾기
    Optional<Member> findByEmail(String email);
    
    // 이름으로 회원 찾기
    List<Member> findByMemberName(String memberName);
    
    // 이메일 도메인으로 회원 찾기 (예: test.com)
    List<Member> findByEmailEndingWith(String domain);
    
}