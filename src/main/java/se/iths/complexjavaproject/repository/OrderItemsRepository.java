package se.iths.complexjavaproject.repository;

import se.iths.complexjavaproject.entity.OrderItems;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemsRepository extends CrudRepository<OrderItems, Long> {
}