package tn.esprit.devops_project.services;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import tn.esprit.devops_project.entities.ActivitySector;
import tn.esprit.devops_project.repositories.ActivitySectorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

public class ActivitySectorTest {

    @InjectMocks
    ActivitySectorImpl activitySectorService;

    @Mock
    ActivitySectorRepository activitySectorRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRetrieveAllActivitySectors() {
        // Créer des données de test
        List<ActivitySector> activitySectors = new ArrayList<>();
        activitySectors.add(new ActivitySector(1L, "Code1", "Sector1"));
        activitySectors.add(new ActivitySector(2L, "Code2", "Sector2"));

        // Définir le comportement du mock
        Mockito.when(activitySectorRepository.findAll()).thenReturn(activitySectors);

        // Appeler la méthode du service
        List<ActivitySector> result = activitySectorService.retrieveAllActivitySectors();

        // Vérifier le résultat
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testAddActivitySector() {
        // Créer une instance d'ActivitySector
        ActivitySector activitySector = new ActivitySector(1L, "Code1", "Sector1");

        // Définir le comportement du mock
        Mockito.when(activitySectorRepository.save(any(ActivitySector.class))).thenReturn(activitySector);

        // Appeler la méthode du service
        ActivitySector savedActivitySector = activitySectorService.addActivitySector(activitySector);

        // Vérifier le résultat
        assertNotNull(savedActivitySector);
        assertEquals(activitySector.getIdSecteurActivite(), savedActivitySector.getIdSecteurActivite());
    }

    @Test
    void testDeleteActivitySector() {
        // ID de l'ActivitySector à supprimer
        Long activitySectorId = 1L;

        // Appeler la méthode du service
        activitySectorService.deleteActivitySector(activitySectorId);

        // Vérifier que la méthode de repository a été appelée avec le bon ID
        verify(activitySectorRepository).deleteById(activitySectorId);
    }

    @Test
    void testUpdateActivitySector() {
        // Créer une instance d'ActivitySector à mettre à jour
        ActivitySector updatedActivitySector = new ActivitySector(1L, "NewCode", "NewSector");

        // Définir le comportement du mock
        Mockito.when(activitySectorRepository.save(any(ActivitySector.class))).thenReturn(updatedActivitySector);

        // Appeler la méthode du service
        ActivitySector savedActivitySector = activitySectorService.updateActivitySector(updatedActivitySector);

        // Vérifier le résultat
        assertNotNull(savedActivitySector);
        assertEquals(updatedActivitySector.getIdSecteurActivite(), savedActivitySector.getIdSecteurActivite());
        assertEquals(updatedActivitySector.getCodeSecteurActivite(), savedActivitySector.getCodeSecteurActivite());
        assertEquals(updatedActivitySector.getLibelleSecteurActivite(), savedActivitySector.getLibelleSecteurActivite());
    }

    @Test
    void testRetrieveActivitySector() {
        // ID de l'ActivitySector à récupérer
        Long activitySectorId = 1L;

        // Créer une instance d'ActivitySector pour simuler la réponse du repository
        ActivitySector activitySector = new ActivitySector(activitySectorId, "Code1", "Sector1");

        // Définir le comportement du mock
        Mockito.when(activitySectorRepository.findById(activitySectorId)).thenReturn(Optional.of(activitySector));

        // Appeler la méthode du service
        ActivitySector retrievedActivitySector = activitySectorService.retrieveActivitySector(activitySectorId);

        // Vérifier le résultat
        assertNotNull(retrievedActivitySector);
        assertEquals(activitySectorId, retrievedActivitySector.getIdSecteurActivite());
        assertEquals("Code1", retrievedActivitySector.getCodeSecteurActivite());
        assertEquals("Sector1", retrievedActivitySector.getLibelleSecteurActivite());
    }
}
