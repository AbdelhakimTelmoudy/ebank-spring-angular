package org.ebank.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.ebank.backend.enums.TypeTransaction;
import lombok.Data;



@Entity
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double montant;

    @Enumerated(EnumType.STRING)
    private TypeTransaction type;

    @ManyToOne
    private Compte compte;  // Compte source (le compte de l'expéditeur)

    @ManyToOne
    private Compte compteDestination;  // Compte de destination (le compte du destinataire, uniquement pour les virements)

    public Transaction(Long id, Double montant, TypeTransaction type, Compte compte, Compte compteDestination) {
        this.id = id;
        this.montant = montant;
        this.type = type;
        this.compte = compte;
        this.compteDestination = compteDestination;
    }

    public Transaction() {
    }

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

    public TypeTransaction getType() {
        return type;
    }

    public void setType(TypeTransaction type) {
        this.type = type;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    public Compte getCompteDestination() {
        return compteDestination;
    }

    public void setCompteDestination(Compte compteDestination) {
        this.compteDestination = compteDestination;
    }
}

