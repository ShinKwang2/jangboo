package cafe.jangboo.member.domain;

import cafe.jangboo.BaseEntity;
import cafe.jangboo.Money;
import cafe.jangboo.order.domain.OrderEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED) //JPA를 위해
@Getter
@Table(name = "members")
@Entity
public class MemberEntity extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long Id;

    @Column(name = "member_name")
    private String name;

    @Column(name = "company_name")
    private Company company;

    @Column(name = "phone_number")
    private String phone;

    @Embedded
    private Money balance;  //잔액

    @OneToMany(mappedBy = "member")
    private List<OrderEntity> orders = new ArrayList<>();

    @Builder
    public MemberEntity(String name, Company company, String phone, Money balance) {
        this.name = name;
        this.company = company;
        this.phone = phone;
        this.balance = balance;
    }

    /**
     * 여기선 Money를 수정하지 않는다.
     * 다만 이름을 잘못 적었거나, 회사를 옮기거나, 혹은 전화번호를 수정하기 위한 메소드이다.
     */
    public void updateMember(String name, Company company, String phone) {
        this.name = name;
        this.company = company;
        this.phone = phone;
    }

    //== 비즈니스 로직 ==//
    /** Money balance 증가 */
    public void addBalance(Money money) {
        this.balance = new Money(this.balance.getValue() + money.getValue());
    }

    public void removeBalance(Money money) {
        this.balance = new Money(this.balance.getValue() - money.getValue());
    }
}
