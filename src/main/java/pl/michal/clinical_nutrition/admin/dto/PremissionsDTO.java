package pl.michal.clinical_nutrition.admin.dto;

import lombok.Data;

@Data

public class PremissionsDTO {
    private long uzytkownikID;
    private long josID;
    private long uprawnienieID;
    private boolean czyAktywny;

}
