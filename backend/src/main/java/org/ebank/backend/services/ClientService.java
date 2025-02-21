package org.ebank.backend.services;

import org.ebank.backend.dto.ClientDTO;
import org.ebank.backend.entities.Client;
import org.ebank.backend.exceptions.DataConflictException;
import org.ebank.backend.exceptions.ResourceNotFoundException;
import org.ebank.backend.mppers.ClientMapper;
import org.ebank.backend.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public List<ClientDTO> getAllClients() {
        return clientRepository.findAll().stream().map(ClientMapper::toDTO).toList();
    }

    public ClientDTO getClientById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client non trouvé avec l'ID : " + id));
        return ClientMapper.toDTO(client);
    }

    public ClientDTO saveClient(ClientDTO clientDTO) {
        Optional<Client> existingClient = clientRepository.findByEmail(clientDTO.getEmail());
        if (existingClient.isPresent()) {
            throw new DataConflictException("L'email " + clientDTO.getEmail() + " est déjà utilisé.");
        }

        Client client = ClientMapper.toEntity(clientDTO);
        client = clientRepository.save(client);
        return ClientMapper.toDTO(client);
    }

    public void deleteClient(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Impossible de supprimer : Client non trouvé avec l'ID " + id);
        }
        clientRepository.deleteById(id);
    }
}


