package org.ebank.backend.controllers;

import org.ebank.backend.dto.CompteDTO;
import org.ebank.backend.services.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comptes")
@CrossOrigin("*")
public class CompteController {
    @Autowired private CompteService compteService;

    @GetMapping
    public List<CompteDTO> getAllComptes() {
        return compteService.getAllComptes();
    }

    @GetMapping("/{id}")
    public CompteDTO getCompteById(@PathVariable Long id) {
        return compteService.getCompteById(id);
    }

    @PostMapping
    public CompteDTO createCompte(@RequestBody  CompteDTO dto) {
        return compteService.createCompte(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCompte(@PathVariable Long id) {
        compteService.deleteCompte(id);
        return ResponseEntity.ok("Compte supprimé avec succès");
    }
}
