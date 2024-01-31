package com.zim0101.jparelation.controller;

import com.zim0101.jparelation.model.Product;
import com.zim0101.jparelation.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductListController {

    private final ProductService productService;

    public ProductListController(ProductService productService) {
        this.productService = productService;
    }

    @ModelAttribute
    public List<Product> addProductListToModel() {
        return productService.findAll();
    }

    @GetMapping
    public String list() {
        return "product/list";
    }
}