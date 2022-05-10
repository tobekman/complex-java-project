package se.iths.complexjavaproject.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import se.iths.complexjavaproject.entity.Category;
import se.iths.complexjavaproject.service.CategoryService;

import java.io.FileNotFoundException;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){this.categoryService = categoryService;}

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("")
    public ResponseEntity<Category> createCategory(@RequestBody Category category){
        Category createdCategory = categoryService.createCategory(category);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Category>> getCategoryById(@PathVariable Long id){
        Optional<Category> foundCategory = categoryService.getCategoryById(id);
        return new ResponseEntity<>(foundCategory, HttpStatus.FOUND);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable Long id) throws FileNotFoundException {
        Category foundCategory = categoryService.getCategoryById(id).orElseThrow(FileNotFoundException::new);
        categoryService.deleteCategory(foundCategory.getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<Iterable<Category>> findAllCategories() {
        Iterable<Category> allCategories = categoryService.getAllCategories();
        return new ResponseEntity<>(allCategories, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Category> updateCategory(@RequestBody Category category) throws FileNotFoundException {
        categoryService.getCategoryById(category.getId()).orElseThrow(FileNotFoundException::new);
        categoryService.createCategory(category);
        return new ResponseEntity<>(category,HttpStatus.FOUND);

    }
}
