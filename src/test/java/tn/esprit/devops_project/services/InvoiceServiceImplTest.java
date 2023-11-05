package tn.esprit.devops_project.services;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.devops_project.entities.Invoice;
import tn.esprit.devops_project.repositories.InvoiceRepository;
import tn.esprit.devops_project.services.Iservices.IInvoiceService;

import java.util.List;

import static org.junit.Assert.*;

//@SpringBootTest
//@ExtendWith(SpringExtension.class)
public class InvoiceServiceImplTest {
    //@Autowired
    //IInvoiceService iinvoiceService;

    @Test
    public void retrieveAllInvoices() {
       //List<Invoice> invoiceList = iinvoiceService.retrieveAllInvoices();
       //Assertions.assertEquals(0,invoiceList.size());

    }
}