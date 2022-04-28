package se.iths.complexjavaproject.service;

import se.iths.complexjavaproject.entity.Order;
import org.springframework.stereotype.Service;
import se.iths.complexjavaproject.repository.OrderRepository;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository)
    {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }
}
