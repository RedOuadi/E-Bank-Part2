package bank.service;

import bank.dto.TransactionDTO;
import bank.model.CompteBancaire;
import bank.model.Transaction;
import bank.repository.CompteBancaireRepository;
import bank.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CompteBancaireRepository compteBancaireRepository;


    public TransactionDTO createTransaction(TransactionDTO transactionDTO) {
        Optional<CompteBancaire> optionalCompteSource = compteBancaireRepository.findById(transactionDTO.getCompteSourceId());
        if (optionalCompteSource.isEmpty()) {
            System.err.println("CompteBancaire not found with compteSourceId: " + transactionDTO.getCompteSourceId());
            throw new RuntimeException("CompteBancaire source not found");
        }

        CompteBancaire compteSource = optionalCompteSource.get();
        System.out.println("CompteBancaire source found: " + compteSource.getCompteId());

        // Find the destination account, if provided
        CompteBancaire compteDestination = null;
        if (transactionDTO.getCompteDestinationId() != 0) {
            Optional<CompteBancaire> optionalCompteDestination = compteBancaireRepository.findById(transactionDTO.getCompteDestinationId());
            if (optionalCompteDestination.isEmpty()) {
                System.err.println("CompteBancaire not found with compteDestinationId: " + transactionDTO.getCompteDestinationId());
                throw new RuntimeException("CompteBancaire destination not found");
            }
            compteDestination = optionalCompteDestination.get();
            System.out.println("CompteBancaire destination found: " + compteDestination.getCompteId());
        }

        // Create the transaction
        Transaction transaction = new Transaction();
        transaction.setCompteBancaire(compteSource);
        transaction.setCompteDest(compteDestination);
        transaction.setMontant(transactionDTO.getMontant());
        transaction.setTypeTransaction(transactionDTO.getTypeTransaction());
        transaction.setDateHeure(new Date());

        Transaction savedTransaction = transactionRepository.save(transaction);

        // Prepare the response DTO
        TransactionDTO savedTransactionDTO = new TransactionDTO();
        savedTransactionDTO.setIdTransaction(savedTransaction.getIdTransaction());
        savedTransactionDTO.setCompteSourceId(savedTransaction.getCompteBancaire().getCompteId());
        if (savedTransaction.getCompteDest() != null) {
            savedTransactionDTO.setCompteDestinationId(savedTransaction.getCompteDest().getCompteId());
        }
        savedTransactionDTO.setMontant(savedTransaction.getMontant());
        savedTransactionDTO.setTypeTransaction(savedTransaction.getTypeTransaction());
        savedTransactionDTO.setDateHeure(savedTransaction.getDateHeure());

        return savedTransactionDTO;
    }
}

