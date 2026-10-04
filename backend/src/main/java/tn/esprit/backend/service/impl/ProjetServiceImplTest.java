package tn.esprit.backend.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.backend.entity.Projet;
import tn.esprit.backend.repository.ProjetRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProjetServiceImplTest {

    @Mock
    private ProjetRepository projetRepository;

    @InjectMocks
    private ProjetServiceImpl projetService;

    @Test
    void testAddProjet() {
        Projet projet = Projet.builder()
                .sujet("Projet DevOps")
                .build();

        when(projetRepository.save(projet)).thenReturn(projet);

        Projet result = projetService.addProjet(projet);

        assertNotNull(result);
        assertEquals("Projet DevOps", result.getSujet());
        verify(projetRepository).save(projet);
    }

    @Test
    void testUpdateProjet() {
        Projet projet = Projet.builder()
                .id(1L)
                .sujet("Projet DevOps Updated")
                .build();

        when(projetRepository.save(projet)).thenReturn(projet);

        Projet result = projetService.updateProjet(projet);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Projet DevOps Updated", result.getSujet());
        verify(projetRepository).save(projet);
    }

    @Test
    void testDeleteProjet() {
        Long id = 1L;

        doNothing().when(projetRepository).deleteById(id);

        projetService.deleteProjet(id);

        verify(projetRepository).deleteById(id);
    }

    @Test
    void testGetProjetById() {
        Projet projet = Projet.builder()
                .id(1L)
                .sujet("Projet DevOps")
                .build();

        when(projetRepository.findById(1L))
                .thenReturn(Optional.of(projet));

        Projet result = projetService.getProjetById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Projet DevOps", result.getSujet());

        verify(projetRepository).findById(1L);
    }

    @Test
    void testGetProjetByIdNotFound() {
        when(projetRepository.findById(99L))
                .thenReturn(Optional.empty());

        Projet result = projetService.getProjetById(99L);

        assertNull(result);

        verify(projetRepository).findById(99L);
    }

    @Test
    void testGetAllProjets() {
        Projet projet1 = Projet.builder()
                .id(1L)
                .sujet("Projet 1")
                .build();

        Projet projet2 = Projet.builder()
                .id(2L)
                .sujet("Projet 2")
                .build();

        when(projetRepository.findAll())
                .thenReturn(Arrays.asList(projet1, projet2));

        List<Projet> result = projetService.getAllProjets();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Projet 1", result.get(0).getSujet());
        assertEquals("Projet 2", result.get(1).getSujet());

        verify(projetRepository).findAll();
    }
}