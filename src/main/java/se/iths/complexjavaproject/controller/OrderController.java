package se.iths.complexjavaproject.controller;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;
import se.iths.complexjavaproject.entity.Address;
import se.iths.complexjavaproject.entity.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import se.iths.complexjavaproject.jms.sender.Sender;
import se.iths.complexjavaproject.service.OrderService;

import java.io.FileNotFoundException;
import java.util.Optional;

@RestController
@RequestMapping("order")
public class OrderController {

    private final OrderService orderService;
    private final Sender sender;

    public OrderController(OrderService orderService, Sender sender) {
        this.orderService = orderService;
        this.sender = sender;
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order createdOrder = orderService.createOrder(order);
        sender.sendOrderMessage(order);
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
