package springrestdocs.springrestdocsstudy.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import springrestdocs.springrestdocsstudy.member.Member;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class MemberSignUpRequest {

    @Email
    private String email;
    @NotBlank
    private String name;

    public Member toMember() {
        return Member.of(email, name);
    }
}
