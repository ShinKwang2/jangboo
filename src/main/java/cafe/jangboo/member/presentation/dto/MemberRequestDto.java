package cafe.jangboo.member.presentation.dto;

import cafe.jangboo.Money;
import cafe.jangboo.member.domain.Company;
import cafe.jangboo.member.domain.MemberEntity;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class MemberRequestDto {

    @NotEmpty(message = "회원 이름은 필수 입니다.")
    private String name;
    private String companyName;
    private String teamName;
    private String phone;
    @NotNull(message = "잔액 입력은 필수 입니다.")
    private int balance;

    public MemberRequestDto() {
    }

    @Builder
    public MemberRequestDto(String name, String companyName, String teamName, String phone, int balance) {
        this.name = name;
        this.companyName = companyName;
        this.teamName = teamName;
        this.phone = phone;
        this.balance = balance;
    }




    public MemberEntity toEntity() {
        return MemberEntity.builder()
                .name(name)
                .company(new Company(companyName, teamName))
                .phone(phone)
                .balance(new Money(balance))
                .build();
    }
}
