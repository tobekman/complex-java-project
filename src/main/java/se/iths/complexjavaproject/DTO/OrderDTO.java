package se.iths.complexjavaproject.DTO;
import se.iths.complexjavaproject.entity.User;

import java.util.List;
import java.util.Set;

public class OrderDTO {

    private Long id;
    private double orderPrice;
    private String createdAt;
    private Long userId;
    private List<Long> orderItems;

    public OrderDTO(Long id, double orderPrice, String createdAt, Long userId, List<Long> orderItems) {
        this.id = id;
        this.orderPrice = orderPrice;
        this.createdAt = createdAt;
        this.userId = userId;
        this.orderItems = orderItems;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Long> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<Long> orderItems) {
        this.orderItems = orderItems;
    }
}
