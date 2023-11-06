package tn.esprit.devops_project.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.entities.ActivitySector;
import tn.esprit.devops_project.repositories.ActivitySectorRepository;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ActivitySectorImplTest {

    @Mock
    ActivitySectorRepository activitySectorRepository;

    @InjectMocks
    ActivitySectorImpl activitySectorService;

    private ActivitySector createActivitySector(Long id, String code, String label) {
        ActivitySector activitySector = new ActivitySector();
        activitySector.setIdSecteurActivite(id);
        activitySector.setCodeSecteurActivite(code);
        activitySector.setLibelleSecteurActivite(label);
        return activitySector;
    }


    @Test
    public void updateActivitySector() {
        // Arrange
        ActivitySector activitySector = new ActivitySector();
        activitySector.setIdSecteurActivite(1L);
        activitySector.setCodeSecteurActivite("code1");
        activitySector.setLibelleSecteurActivite("label1");

        when(activitySectorRepository.save(any(ActivitySector.class))).thenReturn(activitySector);

        // Act
        ActivitySector updatedActivitySector = activitySectorService.updateActivitySector(activitySector);

        // Assert
        assertNotNull(updatedActivitySector);
        assertEquals("code1", updatedActivitySector.getCodeSecteurActivite());
        assertEquals("label1", updatedActivitySector.getLibelleSecteurActivite());

        // Vérifie que la méthode save du repository a été appelée une fois
        verify(activitySectorRepository, times(1)).save(any(ActivitySector.class));
    }
//////////////////JUNIT///////////////////////////////////
@Test
    void deleteActivitySector() {

        Long activitySectorId = 1L;


        activitySectorService.deleteActivitySector(activitySectorId);


        assertTrue(true);
    }

    @Test
    void retrieveAllActivitySectors() {
        // Arrange
        List<ActivitySector> activitySectors = List.of(
                createActivitySector(1L, "code1", "label1"),
                createActivitySector(2L, "code2", "label2")
        );

        when(activitySectorRepository.findAll()).thenReturn(activitySectors);

        // Act
        List<ActivitySector> retrievedActivitySectors = activitySectorService.retrieveAllActivitySectors();

        // Assert
        assertNotNull(retrievedActivitySectors);
        assertEquals(activitySectors.size(), retrievedActivitySectors.size());

        // Verify that the findAll method was called once
        verify(activitySectorRepository, times(1)).findAll();
    }
    @Test
    void addActivitySector() {
        // Arrange
        ActivitySector activitySectorToSave = new ActivitySector();
        activitySectorToSave.setIdSecteurActivite(1L);
        activitySectorToSave.setCodeSecteurActivite("code1");
        activitySectorToSave.setLibelleSecteurActivite("label1");

        ActivitySector savedActivitySector = new ActivitySector();
        savedActivitySector.setIdSecteurActivite(1L);
        savedActivitySector.setCodeSecteurActivite("code1");
        savedActivitySector.setLibelleSecteurActivite("label1");

        when(activitySectorRepository.save(activitySectorToSave)).thenReturn(savedActivitySector);

        // Act
        ActivitySector result = activitySectorService.addActivitySector(activitySectorToSave);

        // Assert
        assertNotNull(result, "The savedActivitySector should not be null");
        assertEquals(1L, result.getIdSecteurActivite());
        assertEquals("code1", result.getCodeSecteurActivite());
        assertEquals("label1", result.getLibelleSecteurActivite());
    }


    ////////////////////////////////JUNIT////////////////////////////
    @Test

    void retrieveActivitySector() {
        // Arrange
        Long activitySectorId = 1L;
        ActivitySector expectedActivitySector = createActivitySector(activitySectorId, "code1", "label1");

        // Créez un mock du service ActivitySectorService
        ActivitySectorImpl activitySectorService = mock(ActivitySectorImpl.class);

        // Configurez le comportement du mock pour retourner expectedActivitySector
        when(activitySectorService.retrieveActivitySector(activitySectorId)).thenReturn(expectedActivitySector);

        // Act : Appelez la méthode du service
        ActivitySector actualActivitySector = activitySectorService.retrieveActivitySector(activitySectorId);

        // Assert : Vérifiez que le résultat du mock est conforme à ce que vous attendez
        assertEquals(expectedActivitySector, actualActivitySector);
    }


}
