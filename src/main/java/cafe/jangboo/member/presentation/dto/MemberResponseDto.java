package cafe.jangboo.member.presentation.dto;

import cafe.jangboo.Money;
import cafe.jangboo.member.domain.Company;
import cafe.jangboo.member.domain.MemberEntity;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@Data
public class MemberResponseDto {

    private Long id;
    @NotEmpty
    private String name;
    private String companyName;
    private String teamName;
    private String phone;
    private Money balance;

    public MemberResponseDto(MemberUpdateDto updateDto) {
        this.name = updateDto.getName();
        this.companyName = updateDto.getCompanyName();
        this.teamName = updateDto.getTeamName();
        this.phone = updateDto.getPhone();
    }

    public MemberResponseDto(MemberEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.companyName = entity.getCompany().getName();
        this.teamName = entity.getCompany().getTeamName();
        this.phone = entity.getPhone();
        this.balance = entity.getBalance();
    }

}
