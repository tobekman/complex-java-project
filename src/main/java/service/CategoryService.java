package service;


import entity.Category;
import entity.Item;
import org.springframework.stereotype.Service;
import repository.CategoryRepository;

@Service

public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

}

