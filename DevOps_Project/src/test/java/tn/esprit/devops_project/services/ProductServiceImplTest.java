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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    @Mock
    ProductRepository productRepository;
    @Mock
    StockRepository stockRepository;
    @InjectMocks
    ProductServiceImpl productService;
    
    ProductCategory productCategory;
    Product product = new Product(1L,"Atomic Habits",20,20, ProductCategory.BOOKS,new Stock());
    List<Product> productList = new ArrayList<Product>() {
        {
            add(new Product(2L,"12 rules of life",15,30,ProductCategory.BOOKS,new Stock()));
            add(new Product(3L,"Jeans",10,10,ProductCategory.CLOTHING, new Stock()));
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

    @Test
    void deleteProduct() {
        productService.deleteProduct(1L);
        verify(productRepository, times(1)).deleteById(1L);
    }

    @Test
    void retreiveProductStock() {
        when(productRepository.findByStockIdStock(1L)).thenReturn(productList);

        List<Product> retrievedProducts = productService.retreiveProductStock(1L);

        assertEquals(productList.size(), retrievedProducts.size());
        verify(productRepository, times(1)).findByStockIdStock(1L);
    }
}
