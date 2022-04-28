package se.iths.complexjavaproject.service;


import se.iths.complexjavaproject.entity.Category;
import org.springframework.stereotype.Service;
import se.iths.complexjavaproject.repository.CategoryRepository;

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

