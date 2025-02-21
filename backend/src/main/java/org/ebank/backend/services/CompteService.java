package org.ebank.backend.services;

import org.ebank.backend.dto.CompteDTO;
import org.ebank.backend.entities.Client;
import org.ebank.backend.entities.Compte;
import org.ebank.backend.enums.TypeCompte;
import org.ebank.backend.exceptions.ResourceNotFoundException;
import org.ebank.backend.mppers.CompteMapper;
import org.ebank.backend.repositories.ClientRepository;
import org.ebank.backend.repositories.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class CompteService {
    @Autowired private CompteRepository compteRepository;
    @Autowired private ClientRepository clientRepository;

    public List<CompteDTO> getAllComptes() {
        return compteRepository.findAll().stream().map(CompteMapper::toDTO).toList();
    }

    public CompteDTO getCompteById(Long id) {
        Compte compte = compteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Compte non trouvé"));
        return CompteMapper.toDTO(compte);
    }

    public CompteDTO createCompte(CompteDTO dto) {
        Client client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new ResourceNotFoundException("Client non trouvé"));

        Compte compte = compteRepository.save(CompteMapper.toEntity(dto, client));
        return CompteMapper.toDTO(compte);
    }

    public void deleteCompte(Long id) {
        if (!compteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Compte non trouvé");
        }
        compteRepository.deleteById(id);
    }
}
