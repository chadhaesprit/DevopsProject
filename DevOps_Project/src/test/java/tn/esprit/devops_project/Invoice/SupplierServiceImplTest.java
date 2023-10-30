package tn.esprit.devops_project.Invoice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.repositories.SupplierRepository;
import tn.esprit.devops_project.services.SupplierServiceImpl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

class SupplierServiceImplTest {

    @Mock
    private SupplierRepository supplierRepository;

    @InjectMocks
    private SupplierServiceImpl supplierService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void retrieveAllSuppliers() {
        // Arrange
        List<Supplier> suppliers = Collections.singletonList(new Supplier());
        when(supplierRepository.findAll()).thenReturn(suppliers);

        // Act
        List<Supplier> result = supplierService.retrieveAllSuppliers();

        // Assert
        assertThat(result).hasSize(1);
    }

    @Test
    void addSupplier() {
        // Arrange
        Supplier supplier = new Supplier();
        when(supplierRepository.save(supplier)).thenReturn(supplier);

        // Act
        Supplier result = supplierService.addSupplier(supplier);

        // Assert
        assertThat(result).isNotNull();
    }

    @Test
    void updateSupplier() {
        // Arrange
        Supplier supplier = new Supplier();
        when(supplierRepository.save(supplier)).thenReturn(supplier);

        // Act
        Supplier result = supplierService.updateSupplier(supplier);

        // Assert
        assertThat(result).isNotNull();
    }

    @Test
    void deleteSupplier() {
        // Arrange
        Long supplierId = 1L;

        // Act
        supplierService.deleteSupplier(supplierId);

        // Assert
        verify(supplierRepository, times(1)).deleteById(supplierId);
    }

    @Test
    void retrieveSupplier() {
        // Arrange
        Long supplierId = 1L;
        Supplier supplier = new Supplier();
        when(supplierRepository.findById(supplierId)).thenReturn(Optional.of(supplier));

        // Act
        Supplier result = supplierService.retrieveSupplier(supplierId);

        // Assert
        assertThat(result).isNotNull();
    }
}

