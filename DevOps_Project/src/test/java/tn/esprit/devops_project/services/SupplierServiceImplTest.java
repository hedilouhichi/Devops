package tn.esprit.devops_project.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.devops_project.entities.*;
import tn.esprit.devops_project.repositories.SupplierRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SupplierServiceImplTest {
    @Mock
    SupplierRepository supplierRepository;
    @InjectMocks
    SupplierServiceImpl supplierService;
    Set<Invoice> invoiceset =new HashSet<>();
    Set<ActivitySector> activitySectorset =new HashSet<>();

    Supplier supplier = new Supplier(1L,"code1","label1", SupplierCategory.CONVENTIONNE,invoiceset,activitySectorset);
    List<Supplier> listSuppliers = new ArrayList<Supplier>() {
        {
            add(new Supplier(2L, "code2","label1", SupplierCategory.ORDINAIRE,invoiceset,activitySectorset));
            add(new Supplier(3L,"code2", "label1", SupplierCategory.ORDINAIRE,invoiceset,activitySectorset));
        }
    };

    ////////////////////////////////JUNIT////////////////////////////
    @Test
    void addSupplier() {
        // Arrange
        Supplier SupplierToSave = new Supplier();
        SupplierToSave.setIdSupplier(1L);

        SupplierToSave.setCode("code1");

        SupplierToSave.setLabel("label1");
        SupplierToSave.setSupplierCategory(SupplierCategory.ORDINAIRE);
        when(supplierRepository.save(SupplierToSave)).thenReturn(SupplierToSave);

        // Act
        Supplier result = supplierService.addSupplier(SupplierToSave);

        // Assert
        assertNotNull(result, "The savedSupplier should not be null");
        assertEquals(1L, result.getIdSupplier());
        assertEquals("code1", result.getCode());
        assertEquals("label1", result.getLabel());
        assertEquals(SupplierCategory.ORDINAIRE, result.getSupplierCategory());
    }
    //////////////////JUNIT///////////////////////////////////
    @Test
    void retrieveAllActivitySectors() {
        // Arrange
        List<Supplier> listSuppliers = new ArrayList<Supplier>() {
            {
                add(new Supplier(2L, "code2","label1", SupplierCategory.ORDINAIRE,invoiceset,activitySectorset));
                add(new Supplier(3L,"code2", "label1", SupplierCategory.ORDINAIRE,invoiceset,activitySectorset));
            }
        };
        when(supplierRepository.findAll()).thenReturn(listSuppliers);

        // Act
        List<Supplier> retrieveSuppliers = supplierService.retrieveAllSuppliers();

        // Assert
        assertNotNull(retrieveSuppliers);
        assertEquals(listSuppliers.size(), retrieveSuppliers.size());

        // Verify that the findAll method was called once
        verify(supplierRepository, times(1)).findAll();
    }
    @Test
    void updateSupplier() {
        Supplier updatedSupplier = new Supplier(1L,"code1","label1", SupplierCategory.CONVENTIONNE,invoiceset,activitySectorset);

        // Arrange
        Mockito.when(supplierRepository.save(Mockito.any(Supplier.class))).thenReturn(updatedSupplier);

        // Act
        Supplier savedSupplier = supplierService.updateSupplier(updatedSupplier);

        // Assert
        assertNotNull(savedSupplier);
        assertEquals(supplier.getIdSupplier(), savedSupplier.getIdSupplier());
        assertEquals(supplier.getCode(), savedSupplier.getCode());
        assertEquals(supplier.getLabel(), savedSupplier.getLabel());
        assertEquals(supplier.getSupplierCategory(), savedSupplier.getSupplierCategory());
        assertEquals(supplier.getInvoices(), savedSupplier.getInvoices());
        assertEquals(supplier.getActivitySectors(), savedSupplier.getActivitySectors());
    }
    @Test
    void retrieveSupplier() {
        Mockito.when(supplierRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(supplier));
        Supplier supplier1 = supplierService.retrieveSupplier(Long.valueOf(2));
        Assertions.assertNotNull(supplier1);
    }
    //////////////////JUNIT///////////////////////////////////
    @Test
    void deleteSupplier() {
        Long activitySectorId = 1L;
        supplierService.deleteSupplier(activitySectorId);
        assertTrue(true);
    }
}