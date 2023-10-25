package tn.esprit.devops_project.services;

import org.junit.Before;
import org.testng.annotations.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.repositories.SupplierRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class SupplierServiceImplTest {

    @InjectMocks
    private SupplierServiceImpl supplierService;

    @Mock
    private SupplierRepository supplierRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRetrieveAllSuppliers() {
        // Créez une liste factice de fournisseurs
        List<Supplier> supplierList = new ArrayList<>();
        supplierList.add(new Supplier());
        supplierList.add(new Supplier());

        when(supplierRepository.findAll()).thenReturn(supplierList);

        List<Supplier> result = supplierService.retrieveAllSuppliers();

        assertEquals(2, result.size());
    }

    @Test
    public void testAddSupplier() {
        Supplier supplier = new Supplier();

        when(supplierRepository.save(supplier)).thenReturn(supplier);

        Supplier result = supplierService.addSupplier(supplier);

        assertNotNull(result);
    }

    @Test
    public void testUpdateSupplier() {
        Supplier supplier = new Supplier();

        when(supplierRepository.save(supplier)).thenReturn(supplier);

        Supplier result = supplierService.updateSupplier(supplier);

        assertNotNull(result);
    }

    @Test
    public void testDeleteSupplier() {
        Long supplierId = 1L;

        supplierService.deleteSupplier(supplierId);

        verify(supplierRepository).deleteById(supplierId);
    }

    @Test
    public void testRetrieveSupplier() {
        Long supplierId = 1L;
        Supplier supplier = new Supplier();
        when(supplierRepository.findById(supplierId)).thenReturn(Optional.of(supplier));

        Supplier result = supplierService.retrieveSupplier(supplierId);

        assertNotNull(result);
    }
}
