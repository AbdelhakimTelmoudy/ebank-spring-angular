package org.ebank.backend.entities;

import jakarta.persistence.*;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
public class Client {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String email;
    private String telephone;

    @OneToMany(mappedBy = "client")
    private List<Compte> comptes;

    public Client() {
    }

    public Client(Long id, String nom, String email, String telephone, List<Compte> comptes) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.telephone = telephone;
        this.comptes = comptes;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setComptes(List<Compte> comptes) {
        this.comptes = comptes;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }

    public List<Compte> getComptes() {
        return comptes;
    }
}
