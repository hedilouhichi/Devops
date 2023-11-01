package tn.esprit.devops_project.services;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.devops_project.entities.Invoice;
import tn.esprit.devops_project.services.Iservices.IInvoiceService;

import java.util.List;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class InvoiceServiceImplTest {
    @Autowired
    IInvoiceService iinvoiceService;
    @Test
    @Order(0)
    void retrieveAllInvoices() {

        List<Invoice> invoiceDetails = iinvoiceService.retrieveAllInvoices();
        Assertions.assertEquals( 0,invoiceDetails.size());
    }

    @Test
    void cancelInvoice() {
    }





}