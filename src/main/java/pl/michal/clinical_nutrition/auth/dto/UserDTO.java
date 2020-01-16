package pl.michal.clinical_nutrition.auth.dto;

import lombok.Data;

import java.util.Date;

@Data

public class UserDTO {
    private String username;
    private String password;
    private String imiona;
    private String nazwisko;
    private String rodzaj_personelu;
    private int pesel;
    private String NPWZ;
    private String stanowisko;
    private boolean zablokowany;
    private Date wygasniecie_hasla;
}