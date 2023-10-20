package tn.esprit.devops_project.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.devops_project.entities.Invoice;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.repositories.OperatorRepository;
import tn.esprit.devops_project.services.Iservices.IOperatorService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class OperatorServiceImplTest {


//    @Autowired
//    IOperatorService iOperatorService;

    @Mock
    OperatorRepository operatorRepository;
    @InjectMocks
    OperatorServiceImpl operatorService;

    Operator operator=new Operator(1L ,"bouba","rebai","bob",new HashSet<Invoice>());
    List<Operator> operatorList= new ArrayList<Operator>(){
        {add(new Operator(2L,"amen","lh","am",new HashSet<Invoice>()));
            add(new Operator(3L,"hedi","louh","lou",new HashSet<Invoice>()));
        }

    };
    @Test
    void retrieveAllOperators() {
        // Arrange
        Mockito.when(operatorRepository.findAll()).thenReturn(operatorList);

        // Act
        List<Operator> retrievedStocks = operatorService.retrieveAllOperators();

        // Assert
        assertNotNull(retrievedStocks);
        assertEquals(operatorList.size(), retrievedStocks.size());

        // Verify that the findAll method was called once
        Mockito.verify(operatorRepository, Mockito.times(1)).findAll();


    }

    @Test
    void addOperator() {
        Mockito.when(operatorRepository.save(Mockito.any(Operator.class))).thenReturn(operator);

        // Act
        Operator savedOperator = operatorService.addOperator(operator);

        // Assert
        assertNotNull(savedOperator);
        assertEquals(operator.getIdOperateur(), savedOperator.getIdOperateur());
        assertEquals(operator.getFname(), savedOperator.getFname());
        assertEquals(operator.getLname(), savedOperator.getLname());
        assertEquals(operator.getPassword(), savedOperator.getPassword());
        assertEquals(operator.getInvoices(), savedOperator.getInvoices());




    }

    @Test
    void deleteOperator() {
        Long operatorId = 3L; // The ID of the operator to delete
        Operator operator=operatorRepository.retrieveOperator(operatorId);
        operatorService.deleteOperator(opertorID);
        Mockito.verify(operatorRepository).delete(operator);

     }

    @Test
    void updateOperator() {
        // Arrange
        Operator updatedOperator = new Operator(1L ,"updatedFname","updatedLname","updatedPassword", new HashSet<Invoice>());
        Mockito.when(operatorRepository.save(Mockito.any(Operator.class))).thenReturn(updatedOperator);

        // Act
        Operator result = operatorService.updateOperator(updatedOperator);

        // Assert
        assertNotNull(result);
        assertEquals(updatedOperator.getIdOperateur(), result.getIdOperateur());
        assertEquals(updatedOperator.getFname(), result.getFname());
        assertEquals(updatedOperator.getLname(), result.getLname());
        assertEquals(updatedOperator.getPassword(), result.getPassword());
        assertEquals(updatedOperator.getInvoices(), result.getInvoices());

    }

    @Test
    void retrieveOperator() {
        Mockito.when(operatorRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(operator));
        Operator stock1 = operatorService.retrieveOperator(Long.valueOf(2L));
        Assertions.assertNotNull(stock1);
    }
}
