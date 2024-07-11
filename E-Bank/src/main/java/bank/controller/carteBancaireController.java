package bank.controller;

import bank.dto.CarteBancaireDTO;
import bank.service.CartesBancaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cartes")
public class carteBancaireController {

    @Autowired
    private CartesBancaireService carteBancaireService;

    @PostMapping("/add")
    public ResponseEntity<CarteBancaireDTO> createCompteBancaire(@RequestBody CarteBancaireDTO carteBancaireDTO) {
        CarteBancaireDTO createdCarteBancaireDTO = carteBancaireService.createCarte(carteBancaireDTO);
        return ResponseEntity.ok(createdCarteBancaireDTO);
    }


}
