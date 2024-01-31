package com.zim0101.jparelation.controller;

import com.zim0101.jparelation.model.Category;
import com.zim0101.jparelation.model.Product;
import com.zim0101.jparelation.service.CategoryService;
import com.zim0101.jparelation.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products/{id}/edit")
public class ProductEditController {
    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductEditController(ProductService productService,
                                 CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @ModelAttribute
    public Product addProductToModel(@PathVariable Integer id) {
        return productService.findById(id);
    }

    @ModelAttribute
    public List<Category> addCategoryListToModel() {
        return categoryService.findAll();
    }

    @GetMapping
    public String renderEditForm() {
        return "product/edit";
    }

    @PutMapping
    public String submitEditForm(@Valid @ModelAttribute Product product, BindingResult result) {
        if (result.hasErrors()) {
            return "product/edit";
        }

        productService.save(product);

        return "redirect:/products";
    }
}