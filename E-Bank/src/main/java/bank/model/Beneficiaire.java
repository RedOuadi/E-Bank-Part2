package bank.model;

import jakarta.persistence.*;


@Entity
@Table(name = "beneficiaire")
public class Beneficiaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private String detailsCompte;

    @ManyToOne
    @JoinColumn(name = "compte_id")
    private CompteBancaire compteBancaire;

}