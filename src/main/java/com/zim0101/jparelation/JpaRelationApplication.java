package com.zim0101.jparelation;

import com.zim0101.jparelation.model.Category;
import com.zim0101.jparelation.model.Product;
import com.zim0101.jparelation.repository.CategoryRepository;
import com.zim0101.jparelation.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Random;

@SpringBootApplication
public class JpaRelationApplication implements CommandLineRunner {

	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;

    public JpaRelationApplication(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public static void main(String[] args) {
		SpringApplication.run(JpaRelationApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Random random = new Random();

		for (int i = 1; i <= 5; i++) {
			Category category = new Category();
			category.setName("Category "+i);
			categoryRepository.save(category);
		}

		List<Category> categories = categoryRepository.findAll();
		for (int i = 1; i <= 5; i++) {
			Category category = categories.get(random.nextInt(categories.size()));

			Product product = new Product();
			product.setName("Product "+i);
			product.setCategory(category);
			productRepository.save(product);
		}
	}
}
