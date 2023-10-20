package tn.esprit.devops_project.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.devops_project.entities.Product;
import tn.esprit.devops_project.entities.ProductCategory;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.ProductRepository;
import tn.esprit.devops_project.repositories.StockRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    @Mock
    ProductRepository productRepository;
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
        Mockito.when(stockRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(stock));

        Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);

        Product addedProduct = productService.addProduct(product, 1L);
        Assertions.assertNotNull(addedProduct);
    }

    @Test
    void retrieveProduct() {
        Mockito.when(productRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(product));
        Product product1 = productService.retrieveProduct(1L);
        Assertions.assertNotNull(product1);
    }

    @Test
    void retreiveAllProduct() {
        Mockito.when(productRepository.findAll()).thenReturn(new ArrayList<Product>());
        List<Product> productList1 = productService.retreiveAllProduct();
        Assertions.assertNotNull(productList1);
    }

    @Test
    void retrieveProductByCategory() {
        Mockito.when(productRepository.findByCategory(productCategory)).thenReturn(new ArrayList<Product>() );
        List<Product> productList2 = productService.retrieveProductByCategory(ProductCategory.BOOKS);
        Assertions.assertNotNull(productList2);
    }

    @Test
    void deleteProduct() {
        Mockito.doNothing().when(productRepository).deleteById(Mockito.anyLong());
        assertDoesNotThrow(() -> productService.deleteProduct(1L));
        Mockito.verify(productRepository, Mockito.times(1)).deleteById(1L);

    }

    @Test
    void retreiveProductStock() {
        Mockito.when(productRepository.findByStockIdStock(Mockito.anyLong())).thenReturn(productList);
        List<Product> products = productService.retreiveProductStock(1L);
        Assertions.assertNotNull(products);
        Assertions.assertEquals(productList.size(), products.size());
    }
}