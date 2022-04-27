package repository;

import entity.OrderItems;
import org.springframework.data.repository.CrudRepository;

public interface OrderItemsRepository extends CrudRepository<OrderItems, Long> {
}
