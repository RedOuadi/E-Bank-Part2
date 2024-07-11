package bank.controller;

import bank.dto.CompteBancaireDTO;
import bank.model.CompteBancaire;
import bank.service.CompteBancaireService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comptes")
public class compteBancaireController {

    @Autowired
    public CompteBancaireService compteBancaireServiceimpl;

    @PostMapping("/add")
    public ResponseEntity<CompteBancaireDTO> createCompteBancaire(@RequestBody CompteBancaireDTO compteBancaireDTO) {
        CompteBancaireDTO createdCompteBancaireDTO = compteBancaireServiceimpl.CreateCompte(compteBancaireDTO);
        return ResponseEntity.ok(createdCompteBancaireDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CompteBancaireDTO>> getAllComptes() {
        List<CompteBancaireDTO> comptes = compteBancaireServiceimpl.getAllComptes();
        return ResponseEntity.ok(comptes);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CompteBancaire> getMovieById(@PathVariable int id) {
        CompteBancaire compteBancaire = compteBancaireServiceimpl.getCompteById(id);
        if (compteBancaire != null) {
            return ResponseEntity.ok(compteBancaire);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompte(@PathVariable int id) {
        if (compteBancaireServiceimpl.deleteCopmte(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
