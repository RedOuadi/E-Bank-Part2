package bank.repository;

import bank.model.Beneficiaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeneficiaireRepository extends JpaRepository<Beneficiaire, Integer> {
}
