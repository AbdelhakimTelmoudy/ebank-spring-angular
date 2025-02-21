package org.ebank.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ebank.backend.enums.TypeCompte;


public class CompteDTO {
    private Long id;
    private String numeroCompte;
    private Double solde;
    private String type; // COURANT ou EPARGNE
    private Long clientId; // ID du client associé

    // Constructeur sans argument
    public CompteDTO() {}

    // Constructeur avec tous les champs
    public CompteDTO(Long id, String numeroCompte, Double solde, String type, Long clientId) {
        this.id = id;
        this.numeroCompte = numeroCompte;
        this.solde = solde;
        this.type = type;
        this.clientId = clientId;
    }

    // Getter et Setter pour id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter et Setter pour numeroCompte
    public String getNumeroCompte() {
        return numeroCompte;
    }

    public void setNumeroCompte(String numeroCompte) {
        this.numeroCompte = numeroCompte;
    }

    // Getter et Setter pour solde
    public Double getSolde() {
        return solde;
    }

    public void setSolde(Double solde) {
        this.solde = solde;
    }

    // Getter et Setter pour type
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // Getter et Setter pour clientId
    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
}

