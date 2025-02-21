package org.ebank.backend.services;

import jakarta.transaction.Transactional;
import org.ebank.backend.dto.TransactionDTO;
import org.ebank.backend.entities.Client;
import org.ebank.backend.entities.Compte;
import org.ebank.backend.entities.Transaction;
import org.ebank.backend.enums.TypeTransaction;
import org.ebank.backend.exceptions.DataConflictException;
import org.ebank.backend.exceptions.ResourceNotFoundException;
import org.ebank.backend.mppers.TransactionMapper;
import org.ebank.backend.repositories.CompteRepository;
import org.ebank.backend.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class TransactionService {
    @Autowired private TransactionRepository transactionRepository;
    @Autowired private CompteRepository compteRepository;

    @Transactional
    public TransactionDTO effectuerTransaction(TransactionDTO dto) {
        Compte compte = compteRepository.findById(dto.getCompteId())
                .orElseThrow(() -> new ResourceNotFoundException("Compte non trouvé"));

        if (dto.getType().equals(TypeTransaction.VIREMENT.toString())) {
            Compte compteDestination = compteRepository.findById(dto.getCompteDestinationId())
                    .orElseThrow(() -> new ResourceNotFoundException("Compte de destination non trouvé"));

            // Effectuer le virement
            compte.setSolde(compte.getSolde() - dto.getMontant());
            compteDestination.setSolde(compteDestination.getSolde() + dto.getMontant());

            // Sauvegarder les modifications dans les comptes
            compteRepository.save(compte);
            compteRepository.save(compteDestination);

            // Enregistrer la transaction
            Transaction transaction = new Transaction(null, dto.getMontant(), TypeTransaction.VIREMENT, compte, compteDestination);
            transactionRepository.save(transaction);
        }
        Transaction transaction = transactionRepository.save(TransactionMapper.toEntity(dto, compte));
        compteRepository.save(compte);
        return TransactionMapper.toDTO(transaction);
    }


    public List<TransactionDTO> getTransactionsByCompte(Long compteId) {
        List<Transaction> transactions = transactionRepository.findByCompteId(compteId);
        return transactions.stream().map(TransactionMapper::toDTO).toList();
    }

    public List<TransactionDTO> getAllTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();
        return transactions.stream().map(TransactionMapper::toDTO).toList();
    }
}
