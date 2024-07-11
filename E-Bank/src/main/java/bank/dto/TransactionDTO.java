package bank.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Data
public class TransactionDTO {
    private int compteSourceId;
    private int compteDestinationId;
    private float montant;
    private String typeTransaction;
    private Date dateHeure;
    private String description;
    private int idTransaction;
}
