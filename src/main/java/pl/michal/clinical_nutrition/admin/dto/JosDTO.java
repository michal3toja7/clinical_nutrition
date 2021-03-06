package pl.michal.clinical_nutrition.admin.dto;

import lombok.Data;

@Data
public class JosDTO {
    private long id;
    private String kod;
    private String nazwa;
    private Rodzaj rodzaj;
    private boolean czyAktywny;
    private String nazwaPieczatka;
    private String adresMiasto;
    private String adresUlica;
    private String adresUlicaNumer;
    private String adresKodPocztowy;
    private String email;
    private String telefon;


    public enum Rodzaj{APT,ODD,POR,PRA,IZB,INN};
}
