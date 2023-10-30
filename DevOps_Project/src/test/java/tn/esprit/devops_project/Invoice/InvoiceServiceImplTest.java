package tn.esprit.devops_project.Invoice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.devops_project.entities.Invoice;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.repositories.InvoiceDetailRepository;
import tn.esprit.devops_project.repositories.InvoiceRepository;
import tn.esprit.devops_project.repositories.OperatorRepository;
import tn.esprit.devops_project.repositories.SupplierRepository;
import tn.esprit.devops_project.services.InvoiceServiceImpl;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

class InvoiceServiceImplTest {

    @Mock
    private InvoiceRepository invoiceRepository;

    @Mock
    private OperatorRepository operatorRepository;

    @Mock
    private InvoiceDetailRepository invoiceDetailRepository;

    @Mock
    private SupplierRepository supplierRepository;

    @InjectMocks
    private InvoiceServiceImpl invoiceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void retrieveAllInvoices() {
        // Arrange
        List<Invoice> invoices = Arrays.asList(new Invoice(), new Invoice());
        when(invoiceRepository.findAll()).thenReturn(invoices);

        // Act
        List<Invoice> result = invoiceService.retrieveAllInvoices();

        // Assert
        assertThat(result).hasSize(2);
    }

    @Test
    void cancelInvoice() {
        // Arrange
        Long invoiceId = 1L;
        Invoice invoice = new Invoice();
        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(invoice));

        // Act
        invoiceService.cancelInvoice(invoiceId);

        // Assert
        assertThat(invoice.getArchived()).isTrue();
        verify(invoiceRepository, times(1)).save(invoice);
    }

    @Test
    void retrieveInvoice() {
        // Arrange
        Long invoiceId = 1L;
        Invoice invoice = new Invoice();
        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(invoice));

        // Act
        Invoice result = invoiceService.retrieveInvoice(invoiceId);

        // Assert
        assertThat(result).isEqualTo(invoice);
    }

   // @Test
    void getInvoicesBySupplier() {
        // Arrange
        Long idSupplier = 1L;
        Supplier supplier = new Supplier();
        Invoice invoice = new Invoice();
        supplier.setInvoices((Set<Invoice>) Collections.singletonList(invoice));
        when(supplierRepository.findById(idSupplier)).thenReturn(Optional.of(supplier));

        // Act
        List<Invoice> result = invoiceService.getInvoicesBySupplier(idSupplier);

        // Assert
        assertThat(result).hasSize(1);
        assertThat(result.get(0)).isEqualTo(invoice);
    }

    //@Test
    void assignOperatorToInvoice() {
        // Arrange
        Long idOperator = 1L;
        Long idInvoice = 2L;
        Operator operator = new Operator();
        Invoice invoice = new Invoice();
        when(operatorRepository.findById(idOperator)).thenReturn(Optional.of(operator));
        when(invoiceRepository.findById(idInvoice)).thenReturn(Optional.of(invoice));

        // Act
        invoiceService.assignOperatorToInvoice(idOperator, idInvoice);

        // Assert
        assertThat(operator.getInvoices()).contains(invoice);
        verify(operatorRepository, times(1)).save(operator);
    }

    @Test
    void getTotalAmountInvoiceBetweenDates() {
        // Arrange
        Date startDate = new Date();
        Date endDate = new Date();
        float expectedAmount = 100.0f;
        when(invoiceRepository.getTotalAmountInvoiceBetweenDates(startDate, endDate)).thenReturn(expectedAmount);

        // Act
        float result = invoiceService.getTotalAmountInvoiceBetweenDates(startDate, endDate);

        // Assert
        assertThat(result).isEqualTo(expectedAmount);
    }
}
