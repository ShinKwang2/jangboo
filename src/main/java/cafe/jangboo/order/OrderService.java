package cafe.jangboo.order;

import cafe.jangboo.Money;
import cafe.jangboo.member.domain.MemberEntity;
import cafe.jangboo.member.domain.MemberRepository;
import cafe.jangboo.order.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;

    /** 포인트 사용 */
    @Transactional
    public Long usePoint(Long memberId, Money amount) {

        //엔티티 조회
        MemberEntity findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));

        UseEntity order = new UseEntity(findMember, amount, OrderStatus.ORDERED);

        return orderRepository.save(order).getId();
    }

    /** 포인트 적립 */
    @Transactional
    public Long savePoint(Long memberId, Money amount) {

        //엔티티 조회
        MemberEntity findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));

        PointEntity order = new PointEntity(findMember, amount, OrderStatus.ORDERED);

        return orderRepository.save(order).getId();
    }

    /** 주문 취소 */
    @Transactional
    public void cancelOrder(Long orderId) {
        OrderEntity order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("주문을 찾을 수 없습니다."));

        if (order instanceof UseEntity) {
            UseEntity userOrder = (UseEntity) order;
            userOrder.cancel();
        }
        else {
            PointEntity pointOrder = (PointEntity) order;
            pointOrder.cancel();
        }
    }

    public OrderEntity findOne(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("주문을 찾을 수 없습니다."));
    }
}
