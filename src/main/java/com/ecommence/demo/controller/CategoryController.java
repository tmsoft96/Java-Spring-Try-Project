package com.ecommence.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommence.demo.common.ApiResponse;
import com.ecommence.demo.model.Category;
import com.ecommence.demo.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCategory(@RequestBody Category category) {
        categoryService.createCategory(category);
        return new ResponseEntity<>(new ApiResponse(true, "Create a new Category"), HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public List<Category> listCategory() {
        return categoryService.listCategory();
    }

    @PostMapping("/update/{categoryId}")
    public ResponseEntity<ApiResponse> updateCategory(
            @PathVariable("categoryId") int categoryId,
            @Valid @RequestBody Category category) {
        System.out.println("Category ID => " + categoryId);

        if (!categoryService.findById(categoryId)) {
            return new ResponseEntity<>(new ApiResponse(false, "Category does not exit"), HttpStatus.NOT_FOUND);
        }

        categoryService.updateCategory(categoryId, category);
        return new ResponseEntity<>(new ApiResponse(true, "Category updated successfully"), HttpStatus.OK);
    }
}
