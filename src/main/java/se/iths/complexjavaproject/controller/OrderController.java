package se.iths.complexjavaproject.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import se.iths.complexjavaproject.entity.Cart;
import se.iths.complexjavaproject.entity.DTO.OrderDTO;
import se.iths.complexjavaproject.entity.DTO.mapper.Mapper;
import se.iths.complexjavaproject.entity.Item;
import se.iths.complexjavaproject.entity.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import se.iths.complexjavaproject.entity.User;
import se.iths.complexjavaproject.exception.UserNotFoundException;
import se.iths.complexjavaproject.jms.sender.Sender;
import se.iths.complexjavaproject.service.OrderService;
import se.iths.complexjavaproject.service.UserService;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("order")
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;
    private final Sender sender;
    private final Mapper mapper;

    public OrderController(OrderService orderService, UserService userService, Sender sender, Mapper mapper) {
        this.orderService = orderService;
        this.userService = userService;
        this.sender = sender;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody List<Item> items, @RequestParam Long userId) {
        Optional<User> user = userService.getUserById(userId);

        if (user.isEmpty()) {
            throw new UserNotFoundException("User with id: " + userId + " is not found in database.");
        }

        Order createdOrder = new Order();
        Cart newCart = new Cart();
        double orderPrice = 0;

        for (Item item : items) {
            orderPrice+= item.getPrice();
        }

        createdOrder.setUser(user.get());
        createdOrder.setOrderPrice(orderPrice);
        items.forEach(newCart::addItem);
        newCart.setOrder(createdOrder);
        createdOrder.setCart(newCart);


        orderService.createOrder(createdOrder);
        sender.sendOrderMessage(createdOrder);
        OrderDTO orderDTO = (OrderDTO) mapper.toDto(createdOrder);
        return new ResponseEntity<>(orderDTO, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id){
        if (orderService.getOrderById(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            OrderDTO orderDTO = (OrderDTO) mapper.toDto(orderService.getOrderById(id));
            return new ResponseEntity<>(orderDTO, HttpStatus.FOUND);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteOrderById(@PathVariable Long id) throws FileNotFoundException {
        Order foundOrder = orderService.getOrderById(id).orElseThrow(FileNotFoundException::new);
        orderService.deleteOrder(foundOrder.getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<OrderDTO>> findAllOrders() {
        List allOrders = StreamSupport.stream(orderService.getAllOrders().spliterator(), false)
                .map(mapper::toDto)
                .collect(toList());
        return new ResponseEntity<>(allOrders, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<OrderDTO> updateOrder(@RequestBody Order order){
        if(orderService.getOrderById(order.getId()).isPresent()){
            return new ResponseEntity<>(HttpStatus.IM_USED);
        }
        else
        {
            orderService.createOrder(order);
            OrderDTO orderDTO = (OrderDTO) mapper.toDto(order);
            return new ResponseEntity<>(orderDTO,HttpStatus.CREATED);
        }


    }
}
