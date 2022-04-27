package service;

import entity.Order;
import entity.OrderItems;
import org.springframework.stereotype.Service;
import repository.OrderItemsRepository;

@Service
public class OrderItemsService {
    private final OrderItemsRepository orderItemsRepository;

    public OrderItemsService(OrderItemsRepository orderItemsRepository) {
        this.orderItemsRepository = orderItemsRepository;
    }

    public OrderItems createOrderItems(OrderItems orderItems) {
        return orderItemsRepository.save(orderItems);
    }
}
