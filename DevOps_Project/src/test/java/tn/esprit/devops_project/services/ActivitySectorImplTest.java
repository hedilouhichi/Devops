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
import static org.mockito.ArgumentMatchers.any;

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

        Mockito.when(activitySectorRepository.findAll()).thenReturn(activitySectors);

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

        Mockito.when(activitySectorRepository.save(any(ActivitySector.class))).thenReturn(activitySector);

        // Act
        ActivitySector savedActivitySector = activitySectorService.addActivitySector(activitySector);

        // Assert
        assertNotNull(savedActivitySector);
        assertEquals(activitySector.getIdSecteurActivite(), savedActivitySector.getIdSecteurActivite());
        assertEquals(activitySector.getCodeSecteurActivite(), savedActivitySector.getCodeSecteurActivite());
        assertEquals(activitySector.getLibelleSecteurActivite(), savedActivitySector.getLibelleSecteurActivite());

        // Verify that the save method was called once
        Mockito.verify(activitySectorRepository, Mockito.times(1)).save(any(ActivitySector.class));
    }

    @Test
    void deleteActivitySector() {
        // Arrange
        Long activitySectorId = 1L;

        // Act
        activitySectorService.deleteActivitySector(activitySectorId);

        // Assert
        // Verify that the deleteById method was called once with the correct id
        Mockito.verify(activitySectorRepository, Mockito.times(1)).deleteById(activitySectorId);
    }

    @Test
    void retrieveActivitySector() {
        // Arrange
        Long activitySectorId = 1L;
        ActivitySector activitySector = createActivitySector(activitySectorId, "code1", "label1");

        Mockito.when(activitySectorRepository.findById(activitySectorId)).thenReturn(Optional.of(activitySector));

        // Act
        ActivitySector retrievedActivitySector = activitySectorService.retrieveActivitySector(activitySectorId);

        // Assert
        assertNotNull(retrievedActivitySector);
        assertEquals(activitySectorId, retrievedActivitySector.getIdSecteurActivite());

        // Verify that the findById method was called once with the correct id
        Mockito.verify(activitySectorRepository, Mockito.times(1)).findById(activitySectorId);
    }

    @Test
    void retrieveActivitySector_ThrowsException() {
        // Arrange
        Long nonExistentActivitySectorId = 99L;

        Mockito.when(activitySectorRepository.findById(nonExistentActivitySectorId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> activitySectorService.retrieveActivitySector(nonExistentActivitySectorId));

        // Verify that the findById method was called once with the correct id
        Mockito.verify(activitySectorRepository, Mockito.times(1)).findById(nonExistentActivitySectorId);
    }
}