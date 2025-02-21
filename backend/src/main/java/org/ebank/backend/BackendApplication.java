package org.ebank.backend;

import org.ebank.backend.entities.Client;
import org.ebank.backend.entities.Compte;
import org.ebank.backend.entities.Transaction;
import org.ebank.backend.enums.TypeCompte;
import org.ebank.backend.enums.TypeTransaction;
import org.ebank.backend.repositories.ClientRepository;
import org.ebank.backend.repositories.CompteRepository;
import org.ebank.backend.repositories.TransactionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Bean
    public CommandLineRunner insertData(ClientRepository clientRepository,
                                        CompteRepository compteRepository,
                                        TransactionRepository transactionRepository) {
        return args -> {
            // Création de 5 clients avec leurs informations de base (nom, email, téléphone)
            Client client1 = clientRepository.save(new Client(null, "Client1", "client1@email.com", "1234567890", new ArrayList<>()));
            Client client2 = clientRepository.save(new Client(null, "Client2", "client2@email.com", "0987654321", new ArrayList<>()));
            Client client3 = clientRepository.save(new Client(null, "Client3", "client3@email.com", "1122334455", new ArrayList<>()));
            Client client4 = clientRepository.save(new Client(null, "Client4", "client4@email.com", "2233445566", new ArrayList<>()));
            Client client5 = clientRepository.save(new Client(null, "Client5", "client5@email.com", "3344556677", new ArrayList<>()));

            // Création de 5 comptes associés à des clients
            compteRepository.save(new Compte(null, "FR1234567890", 1000.00, TypeCompte.COURANT, client1));
            compteRepository.save(new Compte(null, "FR0987654321", 2000.00, TypeCompte.EPARGNE, client2));
            compteRepository.save(new Compte(null, "FR1122334455", 3000.00, TypeCompte.COURANT, client3));
            compteRepository.save(new Compte(null, "FR2233445566", 4000.00, TypeCompte.EPARGNE, client4));
            compteRepository.save(new Compte(null, "FR3344556677", 5000.00, TypeCompte.COURANT, client5));

            List<Compte> comptes = compteRepository.findAll();

            // Générer 30 opérations aléatoires
            Random random = new Random();
            for (int i = 0; i < 30; i++) {
                Compte compteSource = comptes.get(random.nextInt(comptes.size())); // Choisir un compte source
                TypeTransaction typeTransaction = TypeTransaction.values()[random.nextInt(TypeTransaction.values().length)]; // Choisir un type de transaction (Dépôt, Retrait, Virement)
                Double montant = 50 + (1000 * random.nextDouble()); // Montant aléatoire entre 50 et 1000

                // Si c'est un retrait, vérifier si le compte a suffisamment de fonds
                if (typeTransaction == TypeTransaction.RETRAIT && compteSource.getSolde() < montant) {
                    continue; // Si les fonds sont insuffisants, passer à l'itération suivante
                }

                // Si c'est un virement, choisir un compte de destination
                Compte compteDestination = null;
                if (typeTransaction == TypeTransaction.VIREMENT) {
                    compteDestination = comptes.get(random.nextInt(comptes.size())); // Choisir un compte de destination
                    if (compteDestination.equals(compteSource)) {
                        continue; // Si le compte source et le compte destination sont les mêmes, passer à l'itération suivante
                    }
                }

                // Effectuer la transaction (ajuster les soldes des comptes et enregistrer la transaction)
                if (typeTransaction == TypeTransaction.DEPOT) {
                    compteSource.setSolde(compteSource.getSolde() + montant); // Dépôt sur le compte
                    transactionRepository.save(new Transaction(null, montant, TypeTransaction.DEPOT, compteSource, compteSource));
                } else if (typeTransaction == TypeTransaction.RETRAIT) {
                    compteSource.setSolde(compteSource.getSolde() - montant); // Retrait du compte
                    transactionRepository.save(new Transaction(null, montant, TypeTransaction.RETRAIT, compteSource, compteSource));
                } else if (typeTransaction == TypeTransaction.VIREMENT) {
                    // Débiter le compte source et créditer le compte destination
                    compteSource.setSolde(compteSource.getSolde() - montant);
                    compteDestination.setSolde(compteDestination.getSolde() + montant);
                    transactionRepository.save(new Transaction(null, montant, TypeTransaction.VIREMENT, compteSource, compteDestination));
                    transactionRepository.save(new Transaction(null, montant, TypeTransaction.VIREMENT, compteSource, compteDestination));
                }

                // Sauvegarder les comptes mis à jour
                compteRepository.save(compteSource);
                if (compteDestination != null) {
                    compteRepository.save(compteDestination);
                }
            }
        };
    }

}