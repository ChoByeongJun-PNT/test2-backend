package egovframework.example.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.example.api.dto.MemberCreateRequestDto;
import egovframework.example.api.dto.MemberResponseDto;
import egovframework.example.api.dto.MemberUpdateRequestDto;
import egovframework.example.domain.Member;
import egovframework.example.domain.MemberRepository;

@Service
//@RequiredArgsConstructor
public class MemberService {
    
    private final MemberRepository memberRepository;
    
    public MemberService(MemberRepository memberRepository) {
    	this.memberRepository = memberRepository;
    }
    
    // 회원 저장
    @Transactional
    public Long saveMember(MemberCreateRequestDto requestDto) {
        validateDuplicateEmail(requestDto.getEmail());
        Member member = requestDto.toEntity();
        memberRepository.save(member);
        return member.getMemberId();
    }
    
    // 이메일 중복 검증
    private void validateDuplicateEmail(String email) {
        memberRepository.findByEmail(email)
            .ifPresent(m -> {
                throw new IllegalStateException("이미 존재하는 이메일입니다.");
            });
    }
    
    // ID 기반 회원 조회
    @Transactional(readOnly = true)
    public MemberResponseDto findMember(Long memberId) {
        Member member = memberRepository.findById(memberId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        return MemberResponseDto.from(member);
    }
    
    // 모든 회원 조회
    @Transactional(readOnly = true)
    public List<MemberResponseDto> findAllMembers() {
    	return memberRepository.findAll().stream()
                .map(MemberResponseDto::from)
                .collect(Collectors.toList());
    }
    
    // 이름 기반 회원 조회
    @Transactional(readOnly = true)
    public List<MemberResponseDto> findMembersByName(String memberName) {
        return memberRepository.findByMemberName(memberName).stream()
                .map(MemberResponseDto::from)
                .collect(Collectors.toList());
    }
    
    // 회원 정보 수정
    @Transactional
    public void updateMember(Long memberId, MemberUpdateRequestDto requestDto) {
        Member member = memberRepository.findById(memberId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        member.setMemberName(requestDto.getMemberName());
        member.setEmail(requestDto.getEmail());
    }
    
    // 회원 삭제
    @Transactional
    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);
    }
    
}