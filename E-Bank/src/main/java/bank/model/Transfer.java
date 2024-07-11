package bank.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transfer")
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTransfer;
    private float montant;
    private Date dateHeure;
    private String description;

    @ManyToOne
    @JoinColumn(name = "compte_source_id")
    private CompteBancaire compteSource;

    @ManyToOne
    @JoinColumn(name = "compte_dest_id")
    private CompteBancaire compteDest;
}