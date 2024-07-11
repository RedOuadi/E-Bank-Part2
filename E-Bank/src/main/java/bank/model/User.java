package bank.model;


import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "utilisateur")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String nom;
    private String email;
    private String motDePasse;

    @OneToMany(mappedBy = "user")
    private List<CompteBancaire> comptes;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public List<CompteBancaire> getComptes() {
        return comptes;
    }

    public void setComptes(List<CompteBancaire> comptes) {
        this.comptes = comptes;
    }
}
