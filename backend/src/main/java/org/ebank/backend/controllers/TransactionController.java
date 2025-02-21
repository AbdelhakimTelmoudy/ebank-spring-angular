package org.ebank.backend.controllers;

import org.ebank.backend.dto.TransactionDTO;
import org.ebank.backend.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin("*")
public class TransactionController {
    @Autowired private TransactionService transactionService;

    @PostMapping
    public TransactionDTO effectuerTransaction(@RequestBody TransactionDTO dto) {
        return transactionService.effectuerTransaction(dto);
    }

    @GetMapping("/compte/{compteId}")
    public List<TransactionDTO> getTransactionsByCompte(@PathVariable Long compteId) {
        return transactionService.getTransactionsByCompte(compteId);
    }
    @GetMapping("/all")
    public List<TransactionDTO> getAllTransactions() {
        return transactionService.getAllTransactions();
    }
}
