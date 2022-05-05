package se.iths.complexjavaproject.entity.DTO;

import se.iths.complexjavaproject.entity.Address;
import se.iths.complexjavaproject.entity.Order;

import java.util.List;
import java.util.Set;

public class UserDTO {

    private String fullname;
    private String username;
    private String email;
    private String address;
    private Set<Long> orders;


    public UserDTO(String fullname, String username, String email, String address, Set<Long> orders) {
        this.fullname = fullname;
        this.username = username;
        this.email = email;
        this.address = address;
        this.orders = orders;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Long> getOrders() {
        return orders;
    }

    public void setOrders(Set<Long> orders) {
        this.orders = orders;
    }
}
