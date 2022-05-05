package se.iths.complexjavaproject.entity.DTO.mapper;

import org.springframework.stereotype.Component;
import se.iths.complexjavaproject.entity.DTO.ItemDTO;
import se.iths.complexjavaproject.entity.DTO.OrderDTO;
import se.iths.complexjavaproject.entity.DTO.UserDTO;
import se.iths.complexjavaproject.entity.Category;
import se.iths.complexjavaproject.entity.Item;
import se.iths.complexjavaproject.entity.Order;
import se.iths.complexjavaproject.entity.User;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;


@Component
public class Mapper {
    public <T> Object toDto(T t) {
        if(t instanceof User){
            String name = ((User) t).getFullName();
            Set<Long> orders = null;

            if(((User) t).getOrders() != null){
                    orders = ((User) t)
                    .getOrders()
                    .stream()
                    .map(Order::getId)
                    .collect(toSet());}
            String username = ((User) t).getUsername();
            String email = ((User) t).getEmail();

            String address = null;
            if(((User) t).getAddress() != null){
                address = ((User) t).getAddress().toString();}
            return new UserDTO(name, username, email, address, orders);
        }

        if(t instanceof Order){
            Long id = ((Order) t).getId();;
            Long userId = null;
            if(((Order)t).getUser() != null){
                userId = ((Order)t).getUser().getId();
                }
            double orderPrice = ((Order) t).getOrderPrice();
            String createdAt = String.valueOf(((Order) t).getCreatedAt());


            List<Long> orderItems = ((Order) t).getCart().getItems().stream().map(Item::getId).collect(toList());
            return new OrderDTO(id, orderPrice, createdAt, userId, orderItems);
        }

        if(t instanceof Item){
            Long id = ((Item) t).getId();
            String name = ((Item) t).getName();
            Set<String> categories = null;
            if(((Item) t).getCategories() != null){
                    categories = ((Item) t)
                    .getCategories()
                    .stream()
                    .map(Category::toString)
                    .collect(toSet());}
            double price = ((Item) t).getPrice();
            return new ItemDTO(id, name, categories, price);


        }


        return null;
    }


}