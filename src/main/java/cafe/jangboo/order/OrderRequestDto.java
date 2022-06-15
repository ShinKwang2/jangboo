package cafe.jangboo.order;

import cafe.jangboo.order.domain.UseEntity;
import lombok.Data;

@Data
public class OrderRequestDto {

    private Long id;

    private int amount;

    public OrderRequestDto() {
    }

    public OrderRequestDto(UseEntity order) {
        this.id = order.getId();
        this.amount = order.getAmount().getValue();
    }
}
