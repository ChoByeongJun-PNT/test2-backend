package egovframework.example.api.dto;

import egovframework.example.domain.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberResponseDto {
    
	private Long memberId;
    private String memberName;
    private String email;
    
    public static MemberResponseDto from(Member member) {
        return MemberResponseDto.builder()
        		.memberId(member.getMemberId())
        		.memberName(member.getMemberName())
        		.email(member.getEmail())
        		.build();
    }
    
}