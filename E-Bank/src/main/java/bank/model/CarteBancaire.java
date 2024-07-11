package bank.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "carteBancaire")
public class CarteBancaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int carteId;

    @Column(nullable = false)
    private String numeroCarte;

    @Column(nullable = false)
    private Date dateExpiration;

    @Column(nullable = false)
    private String typeCarte;


    @ManyToOne
    @JoinColumn(name = "compte_id", nullable = false)
    private CompteBancaire compteBancaire;
}
