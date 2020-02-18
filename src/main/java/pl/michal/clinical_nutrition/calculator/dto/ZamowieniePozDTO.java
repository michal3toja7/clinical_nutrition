package pl.michal.clinical_nutrition.calculator.dto;


import lombok.Data;

@Data
public class ZamowieniePozDTO {
    private long id;
    private long zamowienieId;
    private PreparatDTO preparat;
    private double objetosc;
    private double coIleH;
    private double czasWlewu;
    private SposobPodania sposobPodania;
    private String uwagi;

    public enum SposobPodania{WLC, BOL, WLK}
}
