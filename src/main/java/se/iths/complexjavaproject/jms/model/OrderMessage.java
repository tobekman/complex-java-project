package se.iths.complexjavaproject.jms.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class OrderMessage {

    private UUID id;
    @JsonFormat(pattern = "yyyy-MM-dd HH-mm:ss")
    private LocalDateTime localDateTime;
    private List<MessageItem> items;
    private MessageCustomer customer;

    public OrderMessage(UUID id, LocalDateTime localDateTime, List<MessageItem> items, MessageCustomer customer) {
        this.id = id;
        this.localDateTime = localDateTime;
        this.items = items;
        this.customer = customer;
    }

    public UUID getId() {
        return id;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public List<MessageItem> getItems() {
        return items;
    }

    public MessageCustomer getCustomer() {
        return customer;
    }
}
