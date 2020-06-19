package pl.edu.pb.wi.forumbiznesowe.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        logger.info("Zwrócono listę kategorii");
        return categoryRepository.findAll();
    }

    @Override
    public void saveCategory(CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setName(categoryRequest.getName());
        category.setDescription(categoryRequest.getDescription());

        logger.info("Dodano kategorię");
        categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long id) {
        Optional<Category> category = categoryRepository.findById(id);

        if (category.isPresent()) {
            categoryRepository.delete(category.get());
            System.out.println("Pomyślnie usunięto kategorię");
        } else {
            System.out.println("Nie znaleziono kategorii poprzez id");
        }
    }

    @Override
    public void updateCategory(Category category) {
        if (find(category.getId()).isPresent()) {
            Category p = find(category.getId()).get();
            p.setDescription(category.getDescription());
            p.setName(category.getName());
            categoryRepository.save(p);
        }
    }

    @Override
    public Optional<Category> find(Long id) {
        logger.info("Zwrócono kategorię");
        return categoryRepository.findById(id);
    }

    public Category findById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.orElse(null);
    }
}
