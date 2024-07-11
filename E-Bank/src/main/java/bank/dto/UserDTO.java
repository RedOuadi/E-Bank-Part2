package bank.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class UserDTO {
    private Long userId;
    private String nom;
    private String email;
    private String MotDePasse;
}

