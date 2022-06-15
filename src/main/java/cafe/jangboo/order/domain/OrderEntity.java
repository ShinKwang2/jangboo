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
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn
@Table(name = "orders")
@Getter
public abstract class OrderEntity extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity member;    //주문 회원

    private Money amount;

    private OrderStatus status;

    //== 연관관계 편의 메서드 ==//
    public void settingMember(MemberEntity member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public OrderEntity(MemberEntity member, Money amount, OrderStatus status) {
        settingMember(member);
        this.amount = amount;
        this.status = status;
    }

    public void cancel() {
        changeStatus();
    }

    private void changeStatus() {
        if (this.status == OrderStatus.ORDERED) {
            this.status = OrderStatus.CANCELED;
        }
    }
}
