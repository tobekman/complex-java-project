package se.iths.complexjavaproject.controller;

import org.springframework.web.bind.annotation.*;
import se.iths.complexjavaproject.entity.Cart;
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

@RestController
@RequestMapping("order")
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;
    private final Sender sender;

    public OrderController(OrderService orderService, UserService userService, Sender sender) {
        this.orderService = orderService;
        this.userService = userService;
        this.sender = sender;
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody List<Item> items, @RequestParam Long userId) {
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
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Order>> getAddressById(@PathVariable Long id){
        Optional<Order> foundOrder = orderService.getOrderById(id);
        return new ResponseEntity<>(foundOrder, HttpStatus.FOUND);

    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAddressById(@PathVariable Long id) throws FileNotFoundException {
        Order foundOrder = orderService.getOrderById(id).orElseThrow(FileNotFoundException::new);
        orderService.deleteOrder(foundOrder.getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<Iterable<Order>> findAllUsers() {
        Iterable<Order> allOrders = orderService.getAllOrders();
        return new ResponseEntity<>(allOrders, HttpStatus.OK);
    }
}
