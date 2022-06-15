package cafe.jangboo.member.service;

import cafe.jangboo.Money;
import cafe.jangboo.member.domain.Company;
import cafe.jangboo.member.domain.MemberEntity;
import cafe.jangboo.member.domain.MemberRepository;
import cafe.jangboo.member.presentation.dto.MemberRequestDto;
import cafe.jangboo.member.presentation.dto.MemberResponseDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    void 회원가입_조회() throws Exception {
        //given
        MemberEntity member = MemberEntity.builder()
                .name("member")
                .company(new Company("company", "team"))
                .phone("01011111111")
                .balance(new Money(100000))
                .build();

        //when
        Long joinedMemberId = memberService.join(member);
        MemberEntity findMember = memberService.findOne(joinedMemberId);

        //then
        assertThat(findMember).isEqualTo(member);

    }
}