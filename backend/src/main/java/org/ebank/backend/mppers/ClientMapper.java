package org.ebank.backend.mppers;

import org.ebank.backend.dto.ClientDTO;
import org.ebank.backend.entities.Client;

import java.util.ArrayList;

public class ClientMapper {
    public static ClientDTO toDTO(Client client) {
        return new ClientDTO(client.getId(), client.getNom(), client.getEmail(), client.getTelephone());
    }

    public static Client toEntity(ClientDTO dto) {
        return new Client(dto.getId(), dto.getNom(), dto.getEmail(), dto.getTelephone(), new ArrayList<>());
    }
}

