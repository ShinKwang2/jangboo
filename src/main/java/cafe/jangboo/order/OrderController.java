package cafe.jangboo.order;

import cafe.jangboo.Money;
import cafe.jangboo.member.domain.MemberEntity;
import cafe.jangboo.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping("/members")
@Controller
public class OrderController {

    private final MemberService memberService;
    private final OrderService orderService;


    @GetMapping("/{memberId}/orders")
    public String orderList(@PathVariable Long memberId, Model model) {
        MemberEntity findMember = memberService.findOne(memberId);
        List<OrderResponseDto> orderResponseDtoList = findMember.getOrders().stream()
                .map(OrderResponseDto::new)
                .collect(Collectors.toList());

        model.addAttribute("orders", orderResponseDtoList);
        model.addAttribute("order", new OrderRequestDto());
        model.addAttribute("memberId", memberId);
        model.addAttribute("memberBalance", findMember.getBalance().getValue());

        return "orders/orderList";
    }

    @PostMapping("/{memberId}/orders/save")
    public String createSaveOrder(@PathVariable Long memberId,
                              @ModelAttribute OrderRequestDto requestDto) {

        Long orderId = orderService.savePoint(memberId, new Money(requestDto.getAmount()));

        return "redirect:/members/{memberId}/orders";
    }

    @PostMapping("/{memberId}/orders/use")
    public String createUseOrder(@PathVariable Long memberId,
                              @ModelAttribute OrderRequestDto requestDto) {

        Long orderId = orderService.usePoint(memberId, new Money(requestDto.getAmount()));

        return "redirect:/members/{memberId}/orders";
    }
}
