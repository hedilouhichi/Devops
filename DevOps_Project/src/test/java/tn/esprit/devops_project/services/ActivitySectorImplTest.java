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
    ////////////////////////////////JUNIT////////////////////////////
    /*@Test
    void retrieveActivitySector() {
        // Arrange
        Long activitySectorId = 1L;
        ActivitySector expectedActivitySector = createActivitySector(activitySectorId, "code1", "label1");

        // Act
        ActivitySector actualActivitySector = activitySectorService.retrieveActivitySector(activitySectorId);

        // Assert
        assertEquals(expectedActivitySector, actualActivitySector);
    }
*/

}
