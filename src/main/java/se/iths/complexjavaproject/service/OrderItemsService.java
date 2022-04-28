package se.iths.complexjavaproject.service;

import se.iths.complexjavaproject.entity.Item;
import se.iths.complexjavaproject.entity.OrderItems;
import org.springframework.stereotype.Service;
import se.iths.complexjavaproject.repository.OrderItemsRepository;

import javax.persistence.EntityExistsException;
import java.util.Optional;

@Service
public class OrderItemsService {
    private final OrderItemsRepository orderItemsRepository;

    public OrderItemsService(OrderItemsRepository orderItemsRepository) {
        this.orderItemsRepository = orderItemsRepository;
    }

    public OrderItems createOrderItems(OrderItems orderItems) {
        return orderItemsRepository.save(orderItems);
    }


    public Optional<OrderItems> getOrderItemsById(Long id){return orderItemsRepository.findById(id);}

    public void deleteOrderItem(Long id){OrderItems result = orderItemsRepository.findById(id).orElseThrow(EntityExistsException::new);
        orderItemsRepository.deleteById(result.getId());}

    public Iterable<OrderItems> getAllOrderItems(){return orderItemsRepository.findAll();}
}
