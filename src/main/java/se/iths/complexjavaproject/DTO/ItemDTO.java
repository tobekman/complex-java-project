package se.iths.complexjavaproject.DTO;
import java.util.Set;

public class ItemDTO {
    private Long id;
    private String name;
    private Set<String> categories;
    private double price;

    public ItemDTO(Long id, String name, Set<String> categories, double price) {
        this.id = id;
        this.name = name;
        this.categories = categories;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getCategories() {
        return categories;
    }

    public void setCategories(Set<String> categories) {
        this.categories = categories;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


}
