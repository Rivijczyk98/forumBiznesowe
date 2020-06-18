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

@Service
public class CategoryServiceImpl implements CategoryService {

    Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    private final CategoryRepository categoryRepository;

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
}
