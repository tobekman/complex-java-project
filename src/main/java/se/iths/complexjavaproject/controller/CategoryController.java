package se.iths.complexjavaproject.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import se.iths.complexjavaproject.entity.Category;
import se.iths.complexjavaproject.entity.DTO.ItemDTO;
import se.iths.complexjavaproject.entity.DTO.mapper.Mapper;
import se.iths.complexjavaproject.entity.Item;
import se.iths.complexjavaproject.exception.EntityNotFoundException;
import se.iths.complexjavaproject.service.CategoryService;
import se.iths.complexjavaproject.service.ItemService;

import java.io.FileNotFoundException;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;
    private final ItemService itemService;
    private final Mapper mapper;

    public CategoryController(CategoryService categoryService, ItemService itemService, Mapper mapper) {
        this.categoryService = categoryService;
        this.itemService = itemService;
        this.mapper = mapper;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("")
    public ResponseEntity<Category> createCategory(@RequestBody Category category){
        Category createdCategory = categoryService.createCategory(category);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Category>> getCategoryById(@PathVariable Long id){
        Optional<Category> foundCategory = categoryService.getCategoryById(id);
        if(foundCategory.isEmpty()) {
            throw new EntityNotFoundException("Category with id: " + id + " is not found in database.");
        }
        return new ResponseEntity<>(foundCategory, HttpStatus.FOUND);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable Long id) throws FileNotFoundException {
        Category foundCategory = categoryService.getCategoryById(id).orElseThrow(() -> new EntityNotFoundException("Category with id: " + id + " is not found in database."));
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
        categoryService.getCategoryById(category.getId()).orElseThrow(() -> new EntityNotFoundException("Category is not found in database."));
        categoryService.createCategory(category);
        return new ResponseEntity<>(category,HttpStatus.FOUND);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/add")
    public ResponseEntity<ItemDTO> addCategoryToItem(@RequestParam Long categoryId, @RequestParam Long itemId) {
        Optional<Category> foundCategory = categoryService.getCategoryById(categoryId);
        Optional<Item> foundItem = itemService.getItemById(itemId);

        if (foundCategory.isPresent() && foundItem.isPresent()) {
            Category category = foundCategory.get();
            Item item = foundItem.get();
            item.addCategory(category);
            itemService.createItem(item);
            ItemDTO itemDTO = (ItemDTO) mapper.toDto(item);
            return new ResponseEntity<>(itemDTO,HttpStatus.OK);
        } else {
            throw new EntityNotFoundException("No categories or items found");
        }
    }
}
