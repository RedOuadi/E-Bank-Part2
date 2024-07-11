package bank.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Data
public class CompteBancaireDTO {
    private Long compteId;
    private String typeCompte;
    private float solde;
    private Date dateCreation;
    private int userId;
}

