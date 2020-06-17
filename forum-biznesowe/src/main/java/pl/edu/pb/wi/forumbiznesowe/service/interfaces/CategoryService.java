package pl.edu.pb.wi.forumbiznesowe.service.interfaces;

import pl.edu.pb.wi.forumbiznesowe.dao.entity.Category;
import pl.edu.pb.wi.forumbiznesowe.pojo.CategoryRequest;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCategories();

    void saveCategory(CategoryRequest categoryRequest);
}
