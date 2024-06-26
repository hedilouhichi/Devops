package tn.esprit.devops_project.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.devops_project.entities.Product;
import tn.esprit.devops_project.entities.ProductCategory;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.ProductRepository;
import tn.esprit.devops_project.repositories.StockRepository;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
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
    List<Product> productList = new ArrayList<Product>() {
        {
            add(new Product(2L,"12 rules of life",15,30,ProductCategory.BOOKS,stock));
            add(new Product(3L,"Jeans",10,10,ProductCategory.CLOTHING, stock));
        }
    };
    ///////////////////////JUNIT//////////////////////////////////////////
    @Test
    void addProduct() {
        Stock stock = new Stock(1, "l1", new HashSet<>());
        stockRepository.save(stock);

        Product product = new Product(1L, "Atomic Habits", 20, 20, ProductCategory.BOOKS, stock);
        Product addedProduct = productService.addProduct(product, 1L);

        assertEquals(product, addedProduct);
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
        when(productRepository.findAll()).thenReturn(productList);

        List<Product> retrievedProducts = productService.retreiveAllProduct();

        assertEquals(productList.size(), retrievedProducts.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void retrieveProductByCategory() {
        when(productRepository.findByCategory(ProductCategory.BOOKS)).thenReturn(productList);

        List<Product> retrievedProducts = productService.retrieveProductByCategory(ProductCategory.BOOKS);

        assertEquals(productList.size(), retrievedProducts.size());
        verify(productRepository, times(1)).findByCategory(ProductCategory.BOOKS);
    }
    ///////////////////////////JUNIT////////////////////////////////////////////
    @Test
    void deleteProduct() {
        productService.deleteProduct(1L);
        assertTrue(true);
    }

    @Test
    void retreiveProductStock() {
        when(productRepository.findByStockIdStock(1L)).thenReturn(productList);

        List<Product> retrievedProducts = productService.retreiveProductStock(1L);

        assertEquals(productList.size(), retrievedProducts.size());
        verify(productRepository, times(1)).findByStockIdStock(1L);
    }
}
