package springrestdocs.springrestdocsstudy.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class MemberModificationRequest {

    @NotBlank
    private String name;

}
