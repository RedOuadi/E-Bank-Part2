package bank.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TransferDTO {
    private int idTransfer;
    private float montant;
    private Date dateHeure;
    private String description;
}
