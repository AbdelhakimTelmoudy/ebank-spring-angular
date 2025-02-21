package org.ebank.backend.dto;

import org.ebank.backend.enums.TypeTransaction;

public class TransactionDTO {
    private Long id;
    private Double montant;
    private String type;
    private Long compteId;
    private Long compteDestinationId;  // Propriété pour le virement
    private String clientNom;

    // Constructeur
    public TransactionDTO(Long id, Double montant, String type, Long compteId, Long compteDestinationId, String clientNom) {
        this.id = id;
        this.montant = montant;
        this.type = type;
        this.compteId = compteId;
        this.compteDestinationId = compteDestinationId;
        this.clientNom = clientNom;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getCompteId() {
        return compteId;
    }

    public void setCompteId(Long compteId) {
        this.compteId = compteId;
    }

    public Long getCompteDestinationId() {  // Ajouter le getter pour le virement
        return compteDestinationId;
    }

    public void setCompteDestinationId(Long compteDestinationId) {  // Ajouter le setter pour le virement
        this.compteDestinationId = compteDestinationId;
    }

    public String getClientNom() {
        return clientNom;
    }

    public void setClientNom(String clientNom) {
        this.clientNom = clientNom;
    }
}
