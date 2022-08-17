package springrestdocs.springrestdocsstudy.member.dto;

import lombok.Data;
import springrestdocs.springrestdocsstudy.member.Member;

@Data
public class MemberResponse {
    private Long id;
    private String email;
    private String name;

    public MemberResponse(final Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.name = member.getName();
    }
}
