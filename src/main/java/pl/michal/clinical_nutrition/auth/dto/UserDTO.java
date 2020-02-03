package pl.michal.clinical_nutrition.auth.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import lombok.Data;

import java.util.Date;

@Data

public class UserDTO {
    private long id;
    private String username;
    private String password;
    private String imiona;
    private String nazwisko;
    private String rodzaj_personelu;
    private long pesel;
    private String NPWZ;
    private String stanowisko;
    private boolean zablokowany;
    private Date wygasniecie_hasla;
    private boolean administrator;
    private String token;
}