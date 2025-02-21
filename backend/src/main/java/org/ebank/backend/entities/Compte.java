package org.ebank.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.ebank.backend.enums.*;
import lombok.Data;


@Entity
@Builder
public class Compte {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeroCompte;
    private Double solde;

    @Enumerated(EnumType.STRING)
    private TypeCompte type;

    @ManyToOne
    private Client client;

    public Compte(Long id, String numeroCompte, Double solde, TypeCompte type, Client client) {
        this.id = id;
        this.numeroCompte = numeroCompte;
        this.solde = solde;
        this.type = type;
        this.client = client;
    }

    public Compte() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroCompte() {
        return numeroCompte;
    }

    public void setNumeroCompte(String numeroCompte) {
        this.numeroCompte = numeroCompte;
    }

    public Double getSolde() {
        return solde;
    }

    public void setSolde(Double solde) {
        this.solde = solde;
    }

    public TypeCompte getType() {
        return type;
    }

    public void setType(TypeCompte type) {
        this.type = type;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}

