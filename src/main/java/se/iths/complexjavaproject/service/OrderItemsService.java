package se.iths.complexjavaproject.service;

import se.iths.complexjavaproject.entity.OrderItems;
import org.springframework.stereotype.Service;
import se.iths.complexjavaproject.repository.OrderItemsRepository;

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
