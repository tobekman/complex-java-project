package se.iths.complexjavaproject.controller;

import org.springframework.web.bind.annotation.*;
import se.iths.complexjavaproject.entity.Address;
import se.iths.complexjavaproject.entity.OrderItems;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import se.iths.complexjavaproject.service.OrderItemsService;

import java.io.FileNotFoundException;
import java.util.Optional;

@RestController
@RequestMapping("orderitems")
public class OrderItemsController {

    private final OrderItemsService orderOrderItemsService;

    public OrderItemsController(OrderItemsService orderOrderItemsService) {
        this.orderOrderItemsService = orderOrderItemsService;
    }

    @PostMapping
    public ResponseEntity<OrderItems> createOrderItems(@RequestBody OrderItems orderOrderItemss) {
        OrderItems createdOrderItems = orderOrderItemsService.createOrderItems(orderOrderItemss);
        return new ResponseEntity<>(createdOrderItems, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<OrderItems>> getAddressById(@PathVariable Long id){
        Optional<OrderItems> foundOrderItems = orderOrderItemsService.getOrderItemsById(id);
        return new ResponseEntity<>(foundOrderItems, HttpStatus.FOUND);

    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAddressById(@PathVariable Long id) throws FileNotFoundException {
        OrderItems foundOrderItem = orderOrderItemsService.getOrderItemsById(id).orElseThrow(FileNotFoundException::new);
        orderOrderItemsService.deleteOrderItem(foundOrderItem.getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<Iterable<OrderItems>> findAllUsers() {
        Iterable<OrderItems> allOrderItems = orderOrderItemsService.getAllOrderItems();
        return new ResponseEntity<>(allOrderItems, HttpStatus.OK);
    }
}
