package egovframework.example.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "member")
@Getter
@Setter
public class Member {
    
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;
    
    @Column(name = "member_name", nullable = false)
    private String memberName;
    
    @Column(nullable = false)
    private String email;
    
}