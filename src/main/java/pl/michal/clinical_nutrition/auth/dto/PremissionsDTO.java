package pl.michal.clinical_nutrition.auth.dto;

import lombok.Data;

@Data

public class PremissionsDTO {
    private long urzytkownikID;
    private long josID;
    private long uprawnienieID;
    private boolean czyAktywny;

}