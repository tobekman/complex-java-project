package service;

import entity.Order;
import entity.User;
import org.springframework.stereotype.Service;
import repository.OrderRepository;
import repository.UserRepository;

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
