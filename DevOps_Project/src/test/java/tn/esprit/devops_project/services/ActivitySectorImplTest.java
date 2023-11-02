package tn.esprit.devops_project.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.entities.ActivitySector;
import tn.esprit.devops_project.repositories.ActivitySectorRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

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
        Mockito.verify(activitySectorRepository, Mockito.times(1)).findAll();
    }

    @Test
    void addActivitySector() {
        // Arrange
        ActivitySector activitySector = createActivitySector(1L, "code1", "label1");

        when(activitySectorRepository.save(Mockito.any(ActivitySector.class))).thenReturn(activitySector);

        // Act
        ActivitySector savedActivitySector = activitySectorService.addActivitySector(activitySector);

        // Assert
        assertNotNull(savedActivitySector);
        assertEquals(activitySector, savedActivitySector);

        // Verify that the save method was called once
        Mockito.verify(activitySectorRepository, Mockito.times(1)).save(Mockito.any(ActivitySector.class));
    }


    @Test
    void deleteActivitySector() {
        // Arrange
        Long activitySectorId = 1L;

        // Act
        activitySectorService.deleteActivitySector(activitySectorId);

        // Verify that the deleteById method was called once with the correct id
        Mockito.verify(activitySectorRepository, Mockito.times(1)).deleteById(activitySectorId);
    }

    @Test
    void retrieveActivitySector() {
        // Arrange
        Long activitySectorId = 1L;
        ActivitySector expectedActivitySector = createActivitySector(activitySectorId, "code1", "label1");

        when(activitySectorRepository.findById(activitySectorId)).thenReturn(Optional.of(expectedActivitySector));

        // Act
        ActivitySector actualActivitySector = activitySectorService.retrieveActivitySector(activitySectorId);

        // Assert
        assertEquals(expectedActivitySector, actualActivitySector);
    }

    @Test
    void retrieveActivitySector_ThrowsException() {
        // Arrange
        Long nonExistentActivitySectorId = 99L;

        when(activitySectorRepository.findById(nonExistentActivitySectorId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> activitySectorService.retrieveActivitySector(nonExistentActivitySectorId));

        // Verify that the findById method was called once with the correct id
        Mockito.verify(activitySectorRepository, Mockito.times(1)).findById(nonExistentActivitySectorId);
    }
}
