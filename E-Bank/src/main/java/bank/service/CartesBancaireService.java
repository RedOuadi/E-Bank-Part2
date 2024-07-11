package bank.service;

import bank.dto.CarteBancaireDTO;
import bank.model.CarteBancaire;
import bank.model.CompteBancaire;
import bank.repository.CarteBancaireRepository;
import bank.repository.CompteBancaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartesBancaireService {

    @Autowired
    private CarteBancaireRepository carteBancaireRepository;

    @Autowired
    private CompteBancaireRepository compteBancaireRepository;


    public CarteBancaireDTO createCarte(CarteBancaireDTO carteBancaireDTO) {
        CompteBancaire compteBancaire = compteBancaireRepository.findById(carteBancaireDTO.getCompteId())
                .orElseThrow(() -> new RuntimeException("CompteBancaire not found"));

        CarteBancaire carteBancaire = new CarteBancaire();
        carteBancaire.setCompteBancaire(compteBancaire);
        carteBancaire.setNumeroCarte(carteBancaireDTO.getNumeroCarte());
        carteBancaire.setDateExpiration(carteBancaireDTO.getDateExpiration());
        carteBancaire.setTypeCarte(carteBancaireDTO.getTypeCarte());

        CarteBancaire savedCarteBancaire = carteBancaireRepository.save(carteBancaire);

        CarteBancaireDTO savedCarteBancaireDTO = new CarteBancaireDTO();
        savedCarteBancaireDTO.setCarteId(savedCarteBancaire.getCarteId());
        savedCarteBancaireDTO.setCompteId(savedCarteBancaire.getCompteBancaire().getCompteId());
        savedCarteBancaireDTO.setNumeroCarte(savedCarteBancaire.getNumeroCarte());
        savedCarteBancaireDTO.setDateExpiration(savedCarteBancaire.getDateExpiration());
        savedCarteBancaireDTO.setTypeCarte(savedCarteBancaire.getTypeCarte());

        return savedCarteBancaireDTO;
    }

}
