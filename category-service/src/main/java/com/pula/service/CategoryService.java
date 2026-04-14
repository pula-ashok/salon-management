package com.pula.service;

import com.pula.dto.SalonDTO;
import com.pula.model.Category;

import java.util.Set;

public interface CategoryService {

    Category createCategory(Category category, SalonDTO salonDTO);
    Set<Category> getAllCategoriesBySalonId(Long salonId);
    Category getCategoryById(Long id) throws Exception;
    void deleteCategoryById(Long id,Long salonId) throws Exception;
}
