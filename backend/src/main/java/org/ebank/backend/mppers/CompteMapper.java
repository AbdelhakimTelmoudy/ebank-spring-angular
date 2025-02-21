package org.ebank.backend.mppers;

import org.ebank.backend.dto.CompteDTO;
import org.ebank.backend.entities.Client;
import org.ebank.backend.entities.Compte;
import org.ebank.backend.enums.TypeCompte;

public class CompteMapper {
    public static CompteDTO toDTO(Compte compte) {
        return new CompteDTO(compte.getId(), compte.getNumeroCompte(), compte.getSolde(), compte.getType().toString(), compte.getClient().getId());
    }

    public static Compte toEntity(CompteDTO dto, Client client) {
        return new Compte(dto.getId(), dto.getNumeroCompte(), dto.getSolde(), TypeCompte.valueOf(dto.getType()), client);
    }
}

