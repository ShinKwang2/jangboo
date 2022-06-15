package cafe.jangboo;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@NoArgsConstructor
@Embeddable
public class Money {
    @Column(name = "money")
    private Integer value;

    public Money(Integer value) {
        this.value = value;
    }

    public Money add(Money money) {
        return new Money(this.value + money.getValue());
    }

    public Money minus(Money money) {
        return new Money(this.value - money.getValue());
    }
}
