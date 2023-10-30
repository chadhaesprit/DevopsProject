package tn.esprit.devops_project.Invoice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.StockRepository;
import tn.esprit.devops_project.services.StockServiceImpl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

class StockServiceImplTest {

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private StockServiceImpl stockService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addStock() {
        // Arrange
        Stock stock = new Stock();
        when(stockRepository.save(stock)).thenReturn(stock);

        // Act
        Stock result = stockService.addStock(stock);

        // Assert
        assertThat(result).isNotNull();
    }

    @Test
    void retrieveStock() {
        // Arrange
        Long id = 1L;
        Stock stock = new Stock();
        when(stockRepository.findById(id)).thenReturn(Optional.of(stock));

        // Act
        Stock result = stockService.retrieveStock(id);

        // Assert
        assertThat(result).isNotNull();
    }

    @Test
    void retrieveAllStock() {
        // Arrange
        List<Stock> stocks = Collections.singletonList(new Stock());
        when(stockRepository.findAll()).thenReturn(stocks);

        // Act
        List<Stock> result = stockService.retrieveAllStock();

        // Assert
        assertThat(result).hasSize(1);
    }
}
