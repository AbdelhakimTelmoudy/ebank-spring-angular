package org.ebank.backend.mppers;

import org.ebank.backend.dto.TransactionDTO;
import org.ebank.backend.entities.Compte;
import org.ebank.backend.entities.Transaction;
import org.ebank.backend.enums.TypeTransaction;

public class TransactionMapper {
    public static Transaction toEntity(TransactionDTO dto, Compte compteSource) {
        Transaction transaction = new Transaction();
        transaction.setMontant(dto.getMontant());
        transaction.setType(TypeTransaction.valueOf(dto.getType()));
        transaction.setCompte(compteSource);

        return transaction;
    }

    public static TransactionDTO toDTO(Transaction transaction) {
        return new TransactionDTO(
                transaction.getId(),
                transaction.getMontant(),
                transaction.getType().toString(),
                transaction.getCompte().getId(),
                transaction.getCompteDestination() != null ? transaction.getCompteDestination().getId() : null,
                transaction.getCompte().getClient()!= null ? transaction.getCompte().getClient().getNom() : null
        );
    }
}
