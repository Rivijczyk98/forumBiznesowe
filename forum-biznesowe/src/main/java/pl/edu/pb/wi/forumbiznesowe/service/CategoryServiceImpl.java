package pl.edu.pb.wi.forumbiznesowe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pb.wi.forumbiznesowe.dao.CategoryRepository;
import pl.edu.pb.wi.forumbiznesowe.dao.entity.Category;
import pl.edu.pb.wi.forumbiznesowe.pojo.CategoryRequest;
import pl.edu.pb.wi.forumbiznesowe.service.interfaces.CategoryService;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void saveCategory(CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setName(categoryRequest.getName());
        category.setDescription(categoryRequest.getDescription());

        categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long id) {
        Optional<Category> category = categoryRepository.findById(id);

        if(category.isPresent()){
            categoryRepository.delete(category.get());
            System.out.println("Pomyślnie usunięto kategorię");
        }
        else {
            System.out.println("Nie znaleziono kategorii poprzez id");
        }
    }

    @Override
    public void updateCategory(Category category) {
        categoryRepository.save(category);
    }

    public Category findById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.orElse(null);
    }
}
