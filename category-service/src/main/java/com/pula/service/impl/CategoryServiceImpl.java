package com.pula.service.impl;

import com.pula.dto.SalonDTO;
import com.pula.model.Category;
import com.pula.repository.CategoryRepository;
import com.pula.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category createCategory(Category category, SalonDTO salonDTO) {
        Category category1 = new Category();
        category1.setName(category.getName());
        category1.setImage(category.getImage());
        category1.setSalonId(salonDTO.getId());

        return categoryRepository.save(category1);
    }

    @Override
    public Set<Category> getAllCategoriesBySalonId(Long salonId) {
        return categoryRepository.getCategoriesBySalonId(salonId);
    }

    @Override
    public Category getCategoryById(Long id) throws Exception {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isEmpty()){
            throw new Exception("Category is not found with given id "+id);
        }
        return category.get();
    }

    @Override
    public void deleteCategoryById(Long id,Long salonId) throws Exception {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isEmpty()){
            throw new Exception("Category is not found with given id "+id);
        }
        if(!category.get().getSalonId().equals(salonId)){
            throw new Exception("You don't have permission to delete this category with id "+id);
        }
        categoryRepository.deleteById(id);
    }
}
