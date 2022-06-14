package cafe.jangboo.member.presentation.dto;

import cafe.jangboo.member.domain.Company;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Data
public class MemberUpdateDto {

    private Long Id;
    @NotEmpty
    private String name;
    private String companyName;
    private String teamName;
    private String phone;

    @Builder
    public MemberUpdateDto(String name, String companyName, String teamName, String phone) {
        this.name = name;
        this.companyName = companyName;
        this.teamName = teamName;
        this.phone = phone;
    }
}
