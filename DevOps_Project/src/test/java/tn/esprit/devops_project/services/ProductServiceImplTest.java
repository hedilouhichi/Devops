package tn.esprit.devops_project.services;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;
import org.mockito.junit.jupiter.MockitoExtension;

import tn.esprit.devops_project.entities.Product;
import tn.esprit.devops_project.entities.ProductCategory;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.ProductRepository;
import tn.esprit.devops_project.repositories.StockRepository;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

import java.util.*;


import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    @Mock
    ProductRepository productRepository;
    @Mock
    StockRepository stockRepository;
    @InjectMocks
    ProductServiceImpl productService;


    Set<Product> productset =new HashSet<>();
    Stock stock = new Stock(1,"l1",productset);
    Product product = new Product(1L,"Atomic Habits",20,20, ProductCategory.BOOKS,stock);
    List<Product> productList = new ArrayList<>() {
        {
            add(new Product(2L,"12 rules of life",15,30,ProductCategory.BOOKS,stock));
            add(new Product(3L,"Jeans",10,10,ProductCategory.CLOTHING, stock));
        }
    };
    @Test
    void addProduct() {
        Stock stock = new Stock();
        when(stockRepository.findById(1L)).thenReturn(Optional.of(stock));
        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product addedProduct = productService.addProduct(product, 1L);

        assertEquals(product, addedProduct);
        verify(stockRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void retrieveProduct() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Product retrievedProduct = productService.retrieveProduct(1L);

        assertEquals(product, retrievedProduct);
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void retreiveAllProduct() {
        List<Product> productList = new ArrayList<>() {
            {
                add(new Product(2L, "12 rules of life", 15, 30, ProductCategory.BOOKS, new Stock(2, "l2", new HashSet<>())));
                add(new Product(3L, "Jeans", 10, 10, ProductCategory.CLOTHING, new Stock(3, "l3", new HashSet<>())));
            }
        };
        productRepository.saveAll(productList);
        // Create a new instance of ProductServiceImpl with the ProductRepository
        ProductServiceImpl productService = new ProductServiceImpl(productRepository, stockRepository);

        // Call the method you want to test
        List<Product> retrievedProducts = productService.retreiveAllProduct();

        // Assert the result
        assertEquals(productList.size(), retrievedProducts.size());
    }

    @Test
    void retrieveProductByCategory() {
        when(productRepository.findByCategory(ProductCategory.BOOKS)).thenReturn(productList);

        List<Product> retrievedProducts = productService.retrieveProductByCategory(ProductCategory.BOOKS);

        assertEquals(productList.size(), retrievedProducts.size());
        verify(productRepository, times(1)).findByCategory(ProductCategory.BOOKS);
    }

    @Test
    void deleteProduct() {
        List<Product> productList = new ArrayList<>() {
            {
                add(new Product(1L, "Atomic Habits", 20, 20, ProductCategory.BOOKS, new Stock(1, "l1", new HashSet<>())));
                add(new Product(2L, "12 rules of life", 15, 30, ProductCategory.BOOKS, new Stock(2, "l2", new HashSet<>())));
            }
        };

        // Set up your ProductRepository with the productList data (or use a mock if you prefer)
        productRepository.saveAll(productList);

        // Create a new instance of ProductServiceImpl with the ProductRepository
        ProductServiceImpl productService = new ProductServiceImpl(productRepository, stockRepository);

        // Call the method you want to test
        productService.deleteProduct(1L);

        // Assert the result
        assertNull(productRepository.findById(1L));
    }

    @Test
    void retreiveProductStock() {

        List<Product> productList = new ArrayList<>() {
            {
                add(new Product(1L, "Atomic Habits", 20, 20, ProductCategory.BOOKS, new Stock(1, "l1", new HashSet<>())));
                add(new Product(2L, "12 rules of life", 15, 30, ProductCategory.BOOKS, new Stock(1, "l1", new HashSet<>())));
            }
        };

        // Set up your ProductRepository with the productList data (or use a mock if you prefer)

        productRepository.saveAll(productList);

        // Create a new instance of ProductServiceImpl with the ProductRepository
        ProductServiceImpl productService = new ProductServiceImpl(productRepository, stockRepository);

        // Call the method you want to test
        List<Product> retrievedProducts = productService.retreiveProductStock(1L);

        // Assert the result
        assertEquals(2, retrievedProducts.size());
    }
}
