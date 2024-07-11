package bank.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "compteBancaire")
public class CompteBancaire {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int compteId;
    private String typeCompte;
    private float solde;
    private Date dateCreation;
    private String typeCarte;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "compteBancaire", cascade = CascadeType.ALL)
    private List<Transaction> transactions;

    @OneToMany(mappedBy = "compteBancaire", cascade = CascadeType.ALL)
    private List<CarteBancaire> cartesBancaires;

    @OneToMany(mappedBy = "compteBancaire", cascade = CascadeType.ALL)
    private List<Beneficiaire> beneficiaires;

    public CompteBancaire() {}

    public CompteBancaire(String typeCompte, float solde, Date dateCreation, User user) {
        this.typeCompte = typeCompte;
        this.solde = solde;
        this.dateCreation = dateCreation;
        this.user = user;
    }


}

