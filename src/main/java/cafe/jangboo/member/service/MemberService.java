package cafe.jangboo.member.service;

import cafe.jangboo.member.domain.Company;
import cafe.jangboo.member.domain.MemberEntity;
import cafe.jangboo.member.domain.MemberRepository;
import cafe.jangboo.member.presentation.dto.MemberRequestDto;
import cafe.jangboo.member.presentation.dto.MemberResponseDto;
import cafe.jangboo.member.presentation.dto.MemberUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    /** 회원 등록*/
    @Transactional
    public Long join(MemberRequestDto memberDto) {
        return memberRepository.save(memberDto.toEntity()).getId();
    }

    /** 회원 전체 조회 */
    public List<MemberResponseDto> findAll() {
        return memberRepository.findAllDesc().stream()
                .map(MemberResponseDto::new)
                .collect(Collectors.toList());
    }

    /** 회원 단건 조회 */
    public MemberResponseDto findOne(Long memberId) {
        MemberEntity findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));
        return new MemberResponseDto(findMember);
    }

    /** 회원 수정 */
    @Transactional
    public Long update(Long id, MemberUpdateDto updateDto) {
        MemberEntity findMember = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다. id = " + id));

        findMember.update(updateDto.getName(), new Company(updateDto.getCompanyName(), updateDto.getTeamName()), updateDto.getPhone());

        return id;
    }

    /** 회원 삭제 */
    @Transactional
    public void delete(Long id) {
        MemberEntity findMember = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다. id = " + id));

        memberRepository.delete(findMember);
    }


}
