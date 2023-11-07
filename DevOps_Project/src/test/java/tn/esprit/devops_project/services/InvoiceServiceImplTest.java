package tn.esprit.devops_project.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.entities.Invoice;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.repositories.InvoiceDetailRepository;
import tn.esprit.devops_project.repositories.InvoiceRepository;
import tn.esprit.devops_project.repositories.OperatorRepository;
import tn.esprit.devops_project.repositories.SupplierRepository;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class InvoiceServiceImplTest {



    @InjectMocks
    private InvoiceServiceImpl invoiceService;

    @Mock
    private InvoiceRepository invoiceRepository;

    @Mock
    private OperatorRepository operatorRepository;

    @Mock
    private InvoiceDetailRepository invoiceDetailRepository;

    @Mock
    private SupplierRepository supplierRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    @Before
    public void setUp() {
        invoiceRepository = mock(InvoiceRepository.class);
        supplierRepository = mock(SupplierRepository.class);
    }

    @Test
    public void testRetrieveAllInvoices() {

        List<Invoice> expectedInvoices = new ArrayList<>();
        when(invoiceRepository.findAll()).thenReturn(expectedInvoices);

        List<Invoice> invoices = invoiceService.retrieveAllInvoices();

        assertEquals(expectedInvoices, invoices);
    }

    @Test
    public void testCancelInvoice() {
        long invoiceId = 1L;
        Invoice mockInvoice = new Invoice();
        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(mockInvoice));

        invoiceService.cancelInvoice(invoiceId);

        assertTrue(mockInvoice.getArchived());
        verify(invoiceRepository).save(mockInvoice);
    }

    @Test
    public void testRetrieveInvoice() {
        long invoiceId = 1L;
        Invoice expectedInvoice = new Invoice();
        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(expectedInvoice));


        Invoice invoice = invoiceService.retrieveInvoice(invoiceId);

        assertEquals(expectedInvoice, invoice);
    }



    /*@Test
    public void testGetInvoicesBySupplier() {
        // Arrange
        long supplierId = 1L;
        Supplier mockSupplier = new Supplier();
        List<Invoice> expectedInvoices = new ArrayList<>();
        mockSupplier.setInvoices(expectedInvoices);
        when(supplierRepository.findById(supplierId)).thenReturn(Optional.of(mockSupplier));

        // Act
        List<Invoice> invoices = invoiceService.getInvoicesBySupplier(supplierId);

        // Assert
        assertEquals(expectedInvoices, invoices);
    }

    @Test
    public void testAssignOperatorToInvoice() {
        // Arrange
        long operatorId = 1L;
        long invoiceId = 2L;
        Operator mockOperator = new Operator();
        Invoice mockInvoice = new Invoice();

        when(operatorRepository.findById(operatorId)).thenReturn(Optional.of(mockOperator));
        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(mockInvoice));

        // Act
        invoiceService.assignOperatorToInvoice(operatorId, invoiceId);

        // Assert
        assertTrue(mockOperator.getInvoices().contains(mockInvoice));
        verify(operatorRepository).save(mockOperator);
    }
    */

    @Test
    public void testGetTotalAmountInvoiceBetweenDates() {
        // Arrange
        Date startDate = new Date();
        Date endDate = new Date();
        float expectedAmount = 100.0f;
        when(invoiceRepository.getTotalAmountInvoiceBetweenDates(startDate, endDate)).thenReturn(expectedAmount);

        // Act
        float totalAmount = invoiceService.getTotalAmountInvoiceBetweenDates(startDate, endDate);

        // Assert
        assertEquals(expectedAmount, totalAmount, 0.001);
    }
}
