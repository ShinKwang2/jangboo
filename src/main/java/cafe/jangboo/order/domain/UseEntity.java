package cafe.jangboo.order.domain;

import cafe.jangboo.BaseEntity;
import cafe.jangboo.Money;
import cafe.jangboo.member.domain.MemberEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@DiscriminatorValue("USE")
public class UseEntity extends OrderEntity {

    public UseEntity(MemberEntity member, Money amount, OrderStatus status) {
        super(member, amount, status);
        member.removeBalance(amount);
    }

    @Override
    public void cancel() {
        super.cancel();
        super.getMember().addBalance(super.getAmount());
    }

}
