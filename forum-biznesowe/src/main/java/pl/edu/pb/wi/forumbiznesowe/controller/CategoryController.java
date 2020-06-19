package pl.edu.pb.wi.forumbiznesowe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pb.wi.forumbiznesowe.dao.entity.Category;
import pl.edu.pb.wi.forumbiznesowe.pojo.CategoryRequest;
import pl.edu.pb.wi.forumbiznesowe.service.interfaces.CategoryService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categoryList = categoryService.getAllCategories();
        return ResponseEntity.ok(categoryList);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> createCategory(@RequestBody CategoryRequest categoryRequest) {
        categoryService.saveCategory(categoryRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public void deleteCategory(@RequestParam Long id) {
        categoryService.deleteCategory(id);
    }

    @PutMapping("/edit")
    public void updateCategory(@RequestBody Category category){
        categoryService.updateCategory(category);
    }

    @GetMapping("/find")
    public Category findCategoryByID(@RequestParam Long id) {
        return categoryService.findById(id);
    }
}
