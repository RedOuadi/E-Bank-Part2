
package bank.service;

import bank.dto.CompteBancaireDTO;
import bank.model.CompteBancaire;
import bank.model.User;
import bank.repository.CompteBancaireRepository;
import bank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompteBancaireService {

    @Autowired
    public CompteBancaireRepository compteBancaireRepository;

    @Autowired
    private UserRepository userRepository;


    public CompteBancaireDTO CreateCompte(CompteBancaireDTO compteBancaireDTO) {
        User user = userRepository.findById(compteBancaireDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        CompteBancaire compteBancaire = new CompteBancaire();
        compteBancaire.setUser(user);
        compteBancaire.setTypeCompte(compteBancaireDTO.getTypeCompte());
        compteBancaire.setSolde(compteBancaireDTO.getSolde());
        compteBancaire.setDateCreation(compteBancaireDTO.getDateCreation());

        CompteBancaire savedCompteBancaire = compteBancaireRepository.save(compteBancaire);

        CompteBancaireDTO savedCompteBancaireDTO = new CompteBancaireDTO();
        savedCompteBancaireDTO.setUserId(savedCompteBancaire.getUser().getUserId());
        savedCompteBancaireDTO.setTypeCompte(savedCompteBancaire.getTypeCompte());
        savedCompteBancaireDTO.setSolde(savedCompteBancaire.getSolde());
        savedCompteBancaireDTO.setDateCreation(savedCompteBancaire.getDateCreation());

        return savedCompteBancaireDTO;
    }


    public List<CompteBancaireDTO> getAllComptes() {
        return compteBancaireRepository.findAll().stream()
                .map(compte -> {
                    CompteBancaireDTO dto = new CompteBancaireDTO();
                    dto.setUserId(compte.getUser().getUserId());
                    dto.setTypeCompte(compte.getTypeCompte());
                    dto.setSolde(compte.getSolde());
                    dto.setDateCreation(compte.getDateCreation());
                    return dto;
                })
                .collect(Collectors.toList());
    }


    public boolean deleteCopmte(int id) {
        if (compteBancaireRepository.existsById(id)) {
            compteBancaireRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public CompteBancaire getCompteById(int id) {
        Optional<CompteBancaire> optionalMovie = compteBancaireRepository.findById(id);
        return optionalMovie.orElse(null);
    }


}