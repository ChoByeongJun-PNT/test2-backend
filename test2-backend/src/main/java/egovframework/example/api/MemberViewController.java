package egovframework.example.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import egovframework.example.api.dto.MemberCreateRequestDto;
import egovframework.example.api.dto.MemberResponseDto;
import egovframework.example.api.dto.MemberUpdateRequestDto;
import egovframework.example.service.MemberService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberViewController {
    
    private final MemberService memberService;
    
    @PostMapping("")
    public ResponseEntity<Long> createMember(@RequestBody MemberCreateRequestDto requestDto) {
    	Long memberId = memberService.saveMember(requestDto);
        return ResponseEntity.ok(memberId);
    }
    
    @GetMapping("/{memberId}")
    public ResponseEntity<MemberResponseDto> getMember(@PathVariable Long memberId) {
    	MemberResponseDto responseDto = memberService.findMember(memberId);
        return ResponseEntity.ok(responseDto);
    }
    
    @GetMapping("")
    public ResponseEntity<List<MemberResponseDto>> getAllMembers() {
        List<MemberResponseDto> members = memberService.findAllMembers();
        return ResponseEntity.ok(members);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<MemberResponseDto>> getMembersByName(@RequestParam String name) {
        List<MemberResponseDto> members = memberService.findMembersByName(name);
        return ResponseEntity.ok(members);
    }
    
    @PutMapping("/{memberId}")
    public ResponseEntity<Void> updateMember(
            @PathVariable Long memberId,
            @RequestBody MemberUpdateRequestDto requestDto) {
        memberService.updateMember(memberId, requestDto);
        return ResponseEntity.ok().build();
    }
    
    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long memberId) {
        memberService.deleteMember(memberId);
        return ResponseEntity.ok().build();
    }
    
}
