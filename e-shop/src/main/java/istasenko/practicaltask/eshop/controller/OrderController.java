package istasenko.practicaltask.eshop.controller;

import istasenko.practicaltask.eshop.converter.order.OrderConverter;
import istasenko.practicaltask.eshop.dto.OrderDto;
import istasenko.practicaltask.eshop.model.Order;
import istasenko.practicaltask.eshop.model.security.CustomUserDetails;
import istasenko.practicaltask.eshop.service.order.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Set;

import static org.springframework.http.HttpHeaders.LOCATION;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@RestController
@RequestMapping(value = "/orders")
@Validated
public class OrderController {

    private final OrderService orderService;
    private final OrderConverter orderConverter;

    public OrderController(OrderService orderService, OrderConverter orderConverter) {
        this.orderService = orderService;
        this.orderConverter = orderConverter;
    }


    @PostMapping(value = "/baskets/{basketId}")
    @ResponseBody
    public ResponseEntity<OrderDto> set(@AuthenticationPrincipal CustomUserDetails userDetails,
                                        @PathVariable("basketId") Long basketId)  {
        Order  order = orderService.setOrder(userDetails, basketId);
        String currentUri = ServletUriComponentsBuilder.fromCurrentContextPath().toUriString();
        String savedOrderLocation = currentUri + "/" + order.getId();

        return ResponseEntity
                .status(CREATED)
                .header(LOCATION, savedOrderLocation)
                .contentType(APPLICATION_JSON)
                .body(orderConverter.fromOrder(order));
    }

    @GetMapping()
    @ResponseBody
    public ResponseEntity<Set<OrderDto>> get(@AuthenticationPrincipal CustomUserDetails userDetails) {
        Set<Order> orders = orderService.getAllOrders(userDetails);
        return ResponseEntity
                .status(OK)
                .contentType(APPLICATION_JSON)
                .body(orderConverter.fromOrderSet(orders));
    }

}
