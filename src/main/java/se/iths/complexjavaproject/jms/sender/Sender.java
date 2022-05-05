package se.iths.complexjavaproject.jms.sender;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import se.iths.complexjavaproject.entity.Item;
import se.iths.complexjavaproject.entity.Order;
import se.iths.complexjavaproject.jms.model.MessageCustomer;
import se.iths.complexjavaproject.jms.model.MessageItem;
import se.iths.complexjavaproject.jms.model.OrderMessage;
import se.iths.complexjavaproject.jms.config.JmsConfig;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class Sender {

    private final JmsTemplate jmsTemplate;

    public Sender(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendOrderMessage(Order order) {

        MessageCustomer customer = new MessageCustomer();

        if(order.getUser() != null) {
            customer.setName(order.getUser().getFullName());
            customer.setStreet(order.getUser().getAddress().getStreet());
            customer.setPostalCode(order.getUser().getAddress().getPostalCode());
            customer.setCity(order.getUser().getAddress().getCity());
        }

        List<MessageItem> items = new ArrayList<>();
        List<Item> itemsInOrder = order.getCart().getItems();
        if(itemsInOrder != null) {
            for (Item item : itemsInOrder) {
                MessageItem messageItem = new MessageItem(item.getName(), item.getPrice());
                items.add(messageItem);
            }
        }


        OrderMessage orderMessage = new OrderMessage(UUID.randomUUID(), LocalDateTime.now(), items, customer);
        jmsTemplate.convertAndSend(JmsConfig.ORDER_QUEUE, orderMessage);

    }

}
