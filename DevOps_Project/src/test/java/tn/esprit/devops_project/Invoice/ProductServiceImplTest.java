package tn.esprit.devops_project.Invoice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.devops_project.entities.Product;
import tn.esprit.devops_project.entities.ProductCategory;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.ProductRepository;
import tn.esprit.devops_project.repositories.StockRepository;
import tn.esprit.devops_project.services.ProductServiceImpl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addProduct() {
        // Arrange
        Long idStock = 1L;
        Stock stock = new Stock();
        Product product = new Product();
        when(stockRepository.findById(idStock)).thenReturn(Optional.of(stock));
        when(productRepository.save(product)).thenReturn(product);

        // Act
        Product result = productService.addProduct(product, idStock);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getStock()).isEqualTo(stock);
    }

    @Test
    void retrieveProduct() {
        // Arrange
        Long id = 1L;
        Product product = new Product();
        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        // Act
        Product result = productService.retrieveProduct(id);

        // Assert
        assertThat(result).isNotNull();
    }

    @Test
    void retreiveAllProduct() {
        // Arrange
        List<Product> products = Collections.singletonList(new Product());
        when(productRepository.findAll()).thenReturn(products);

        // Act
        List<Product> result = productService.retreiveAllProduct();

        // Assert
        assertThat(result).hasSize(1);
    }

    @Test
    void retrieveProductByCategory() {
        // Arrange
        ProductCategory category = ProductCategory.ELECTRONICS;
        List<Product> products = Collections.singletonList(new Product());
        when(productRepository.findByCategory(category)).thenReturn(products);

        // Act
        List<Product> result = productService.retrieveProductByCategory(category);

        // Assert
        assertThat(result).hasSize(1);
    }

    @Test
    void deleteProduct() {
        // Arrange
        Long id = 1L;

        // Act
        productService.deleteProduct(id);

        // Assert
        verify(productRepository, times(1)).deleteById(id);
    }

    @Test
    void retreiveProductStock() {
        // Arrange
        Long idStock = 1L;
        List<Product> products = Collections.singletonList(new Product());
        when(productRepository.findByStockIdStock(idStock)).thenReturn(products);

        // Act
        List<Product> result = productService.retreiveProductStock(idStock);

        // Assert
        assertThat(result).hasSize(1);
    }
}

