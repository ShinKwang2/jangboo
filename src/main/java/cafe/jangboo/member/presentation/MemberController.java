package cafe.jangboo.member.presentation;

import cafe.jangboo.member.domain.Company;
import cafe.jangboo.member.domain.MemberEntity;
import cafe.jangboo.member.presentation.dto.MemberRequestDto;
import cafe.jangboo.member.presentation.dto.MemberResponseDto;
import cafe.jangboo.member.presentation.dto.MemberUpdateDto;
import cafe.jangboo.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/members")
@Controller
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public String memberList(Model model) {
        List<MemberResponseDto> members = memberService.findAll();
        model.addAttribute("members", members);

        return "members/memberList";
    }

    @GetMapping("/{memberId}")
    public String member(@PathVariable Long memberId, Model model) {
        MemberResponseDto responseDto = memberService.findOne(memberId);

        log.info("responseDto={}",responseDto);

        model.addAttribute("member", responseDto);
        return "members/member";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("member", new MemberRequestDto());
        return "members/createMemberForm";
    }

    /** PRG POST - REDIRECT - GET */
    @PostMapping("/new")
    public String create(@Valid @ModelAttribute MemberRequestDto requestDto, BindingResult result, RedirectAttributes redirectAttributes, Model model) {

        if (result.hasErrors()) {
            return "members/createMemberForm";
        }

        Long memberId = memberService.join(requestDto);
        redirectAttributes.addAttribute("memberId", memberId);
        redirectAttributes.addAttribute("created", true);
        return "redirect:/members/{memberId}";
    }

    @GetMapping("/{memberId}/edit")
    public String editForm(@PathVariable Long memberId, Model model) {
        MemberResponseDto responseDto = memberService.findOne(memberId);

        model.addAttribute("member", responseDto);
        return "members/memberEdit";
    }

    @PostMapping("/{memberId}/edit")
    public String edit(@ModelAttribute MemberUpdateDto memberUpdateDto, BindingResult result,
                       @PathVariable Long memberId, RedirectAttributes redirectAttributes) {
        Long updateMemberId = memberService.update(memberId, memberUpdateDto);

        redirectAttributes.addAttribute("memberId", updateMemberId);
        redirectAttributes.addAttribute("modified", true);
        return "redirect:/members/{memberId}";
    }
}
