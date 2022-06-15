package cafe.jangboo.order;

import cafe.jangboo.Money;
import cafe.jangboo.member.domain.Company;
import cafe.jangboo.member.domain.MemberEntity;
import cafe.jangboo.member.domain.MemberRepository;
import cafe.jangboo.member.service.MemberService;
import cafe.jangboo.order.domain.OrderEntity;
import cafe.jangboo.order.domain.OrderRepository;
import cafe.jangboo.order.domain.PointEntity;
import cafe.jangboo.order.domain.UseEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void 적립() throws Exception {
        //given
        MemberEntity member = createMember();
        Long joinMemberId = memberService.join(member);

        //when
        Long saveOrderId = orderService.savePoint(joinMemberId, new Money(5000));
        MemberEntity findMember = memberService.findOne(joinMemberId);
        //then
        assertThat(findMember.getBalance().getValue()).isEqualTo(105000);
    }

    @Test
    void 사용() throws Exception {
        //given
        MemberEntity member = createMember();
        Long joinMemberId = memberService.join(member);

        //when
        Long useOrderId = orderService.usePoint(joinMemberId, new Money(10000));
        MemberEntity findMember = memberService.findOne(joinMemberId);
        //then
        assertThat(findMember.getBalance().getValue()).isEqualTo(90000);
    }

    @Test
    void 적립_취소() throws Exception {
        //given
        MemberEntity member = createMember();
        Long joinMemberId = memberService.join(member);
        Long saveOrderId = orderService.savePoint(joinMemberId, new Money(50000));

        //when
        orderService.cancelOrder(saveOrderId);

        //then
        MemberEntity findMember = memberService.findOne(joinMemberId);
        assertThat(findMember.getBalance().getValue()).isEqualTo(100000);
    }

    @Test
    void 사용_취소() throws Exception {
        //given
        MemberEntity member = createMember();
        Long joinMemberId = memberService.join(member);
        Long useOrderId = orderService.usePoint(joinMemberId, new Money(50000));

        //when
        orderService.cancelOrder(useOrderId);

        //then
        MemberEntity findMember = memberService.findOne(joinMemberId);
        assertThat(findMember.getBalance().getValue()).isEqualTo(100000);
    }



    @Test
    void 오더찾기() throws Exception {
        //given
        MemberEntity member = createMember();
        Long joinMemberId = memberService.join(member);

        //when
        Long saveOrderId = orderService.savePoint(joinMemberId, new Money(50000));
        Long useOrderId = orderService.usePoint(joinMemberId, new Money(70000));

        OrderEntity findOrder = orderService.findOne(saveOrderId);

        orderService.cancelOrder(saveOrderId);

        //then
        MemberEntity findMember = memberService.findOne(joinMemberId);
        assertThat(findMember.getBalance().getValue()).isEqualTo(30000);
    }

    private MemberEntity createMember() {
        return MemberEntity.builder()
                .name("member")
                .company(new Company("company", "team"))
                .phone("01011111111")
                .balance(new Money(100000))
                .build();
    }
}