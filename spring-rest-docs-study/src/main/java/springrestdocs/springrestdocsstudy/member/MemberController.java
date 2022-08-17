package springrestdocs.springrestdocsstudy.member;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import springrestdocs.springrestdocsstudy.member.dto.MemberModificationRequest;
import springrestdocs.springrestdocsstudy.member.dto.MemberResponse;
import springrestdocs.springrestdocsstudy.member.dto.MemberSignUpRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/{memberId}")
    public MemberResponse getMember(@PathVariable("memberId") Long memberId) {
        Member findMember = memberRepository.findById(memberId).orElseThrow();
        return new MemberResponse(findMember);
    }

    @PostMapping
    public void createMember(@RequestBody MemberSignUpRequest dto) {
        memberRepository.save(dto.toMember());
    }

    @PostMapping("/{memberId}")
    public void modify(
            @RequestBody MemberModificationRequest dto,
            @PathVariable("memberId") Long memberId
    ) {
        Member findMember = memberRepository.findById(memberId).orElseThrow();

        findMember.changeName(dto.getName());
        memberRepository.save(findMember);
    }

    @GetMapping
    public Page<MemberResponse> getMembers(
            @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return memberRepository.findAll(pageable).map(MemberResponse::new);
    }

}
