package se.iths.complexjavaproject.service;


import se.iths.complexjavaproject.entity.Address;
import se.iths.complexjavaproject.entity.Category;
import org.springframework.stereotype.Service;
import se.iths.complexjavaproject.repository.CategoryRepository;

import javax.persistence.EntityExistsException;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }


    public Optional<Category> getCategoryById(Long id){return categoryRepository.findById(id);}

    public void deleteCategory(Long id){Category result = categoryRepository.findById(id).orElseThrow(EntityExistsException::new);
        categoryRepository.deleteById(result.getId());}

    public Iterable<Category> getAllCategories(){return categoryRepository.findAll();}


}

