package cafe.jangboo.member.presentation.dto;

import cafe.jangboo.Money;
import cafe.jangboo.member.domain.MemberEntity;
import cafe.jangboo.order.domain.UseEntity;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class MemberResponseDto {

    private Long id;
    @NotEmpty
    private String name;
    private String companyName;
    private String teamName;
    private String phone;
    private Money balance;

    private List<UseEntity> orderList;

    public MemberResponseDto(MemberEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.companyName = entity.getCompany().getName();
        this.teamName = entity.getCompany().getTeamName();
        this.phone = entity.getPhone();
        this.balance = entity.getBalance();
    }

}
