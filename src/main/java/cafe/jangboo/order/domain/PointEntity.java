package cafe.jangboo.order.domain;

import cafe.jangboo.BaseEntity;
import cafe.jangboo.Money;
import cafe.jangboo.member.domain.MemberEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@DiscriminatorValue("POINT")
@Getter
public class PointEntity extends OrderEntity {

    public PointEntity(MemberEntity member, Money amount, OrderStatus status) {
        super(member, amount, status);
        member.addBalance(amount);
    }

    @Override
    public void cancel() {
        super.cancel();
        super.getMember().removeBalance(super.getAmount());
    }
}
