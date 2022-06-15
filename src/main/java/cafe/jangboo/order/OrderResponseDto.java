package cafe.jangboo.order;

import cafe.jangboo.order.domain.OrderEntity;
import cafe.jangboo.order.domain.UseEntity;
import cafe.jangboo.order.domain.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderResponseDto {

    private Long id;
    private Integer useAmount;
    private Integer pointAmount;
    private LocalDateTime orderDate;
    private OrderStatus status;

    public OrderResponseDto(OrderEntity order) {
        this.id = order.getId();

        if (order instanceof UseEntity) {
            this.useAmount = order.getAmount().getValue();
        }
        else {
            this.pointAmount = order.getAmount().getValue();
        }

        this.orderDate = order.getCreatedDate();
        this.status = order.getStatus();
    }
}
