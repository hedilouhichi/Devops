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
@SpringBootTest
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
    // @Test
    void retrieveAllSuppliers() {
        // Arrange
        Mockito.when(supplierRepository.findAll()).thenReturn(listSuppliers);

        // Act
        List<Supplier> retrievedSuppliers = supplierService.retrieveAllSuppliers();

        // Assert
        assertNotNull(retrievedSuppliers);
        assertEquals(listSuppliers.size(), retrievedSuppliers.size());

        // Verify that the findAll method was called once
        Mockito.verify(supplierRepository, Mockito.times(1)).findAll();
    }

    @Test
    void addSupplier() {
        // Arrange
        Mockito.when(supplierRepository.save(Mockito.any(Supplier.class))).thenReturn(supplier);

        // Act
        Supplier savedSupplier = supplierService.addSupplier(supplier);

        // Assert
        assertNotNull(savedSupplier);
        assertEquals(supplier.getIdSupplier(), savedSupplier.getIdSupplier());
        assertEquals(supplier.getCode(), savedSupplier.getCode());
        assertEquals(supplier.getLabel(), savedSupplier.getLabel());
        assertEquals(supplier.getSupplierCategory(), savedSupplier.getSupplierCategory());
        assertEquals(supplier.getInvoices(), savedSupplier.getInvoices());
        assertEquals(supplier.getActivitySectors(), savedSupplier.getActivitySectors());

        // Verify that the save method was called once
        Mockito.verify(supplierRepository, Mockito.times(1)).save(Mockito.any(Supplier.class));
    }

    @Test
    void updateSupplier() {
        // Arrange
        Supplier updatedSupplier = new Supplier(1L,"updatedcode","updatedlabel1", SupplierCategory.CONVENTIONNE,invoiceset,activitySectorset);
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
}
