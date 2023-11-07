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
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.StockRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class StockServiceImplTest {
    @Mock
    StockRepository stockRepository;
    @InjectMocks
    StockServiceImpl stockService;
    Set<Product> productset =new HashSet<>();
    Stock stock = new Stock(1,"l1",productset);
    List<Stock> listStocks = new ArrayList<Stock>() {
        {
            add(new Stock(2, "l2", productset));
            add(new Stock(3, "l3", productset));
        }
    };

    @Test
    void addStock() {
        // Arrange
        Mockito.when(stockRepository.save(Mockito.any(Stock.class))).thenReturn(stock);

        // Act
        Stock savedStock = stockService.addStock(stock);

        // Assert
        assertNotNull(savedStock);
        assertEquals(stock.getIdStock(), savedStock.getIdStock());
        assertEquals(stock.getTitle(), savedStock.getTitle());
        assertEquals(stock.getProducts(), savedStock.getProducts());

        // Verify that the save method was called once
        Mockito.verify(stockRepository, Mockito.times(1)).save(Mockito.any(Stock.class));
    }

    @Test
    void retrieveStock() {
        Mockito.when(stockRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(stock));
        Stock stock1 = stockService.retrieveStock(Long.valueOf(2));
        Assertions.assertNotNull(stock1);
    }

    @Test
    void retrieveAllStock() {
        // Arrange
        Mockito.when(stockRepository.findAll()).thenReturn(listStocks);

        // Act
        List<Stock> retrievedStocks = stockService.retrieveAllStock();

        // Assert
        assertNotNull(retrievedStocks);
        assertEquals(listStocks.size(), retrievedStocks.size());

        // Verify that the findAll method was called once
        Mockito.verify(stockRepository, Mockito.times(1)).findAll();

    }
}
