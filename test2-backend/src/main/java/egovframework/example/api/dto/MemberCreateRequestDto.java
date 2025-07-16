package egovframework.example.api.dto;

import egovframework.example.domain.Member;
import lombok.Getter;

@Getter
public class MemberCreateRequestDto {
	
    private String memberName;
    private String email;
    
    public Member toEntity() {
        Member member = new Member();
        member.setMemberName(this.memberName);
        member.setEmail(this.email);
        return member;
    }
    
}
