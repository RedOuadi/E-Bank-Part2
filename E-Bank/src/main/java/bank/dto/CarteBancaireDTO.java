package bank.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CarteBancaireDTO {
    private int carteId;
    private int compteId;
    private String numeroCarte;
    private Date dateExpiration;
    private String typeCarte;
}
