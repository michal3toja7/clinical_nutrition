package pl.michal.clinical_nutrition.calculator.dto;





public class ZamowieniePozDTO {
    private long id;
    private ZamowienieDTO zamowienieDTO;
    private PreparatDTO preparatDTO;
    private double objetosc;
    private double coIleH;
    private double czasWlewu;
    private SposobPodania sposobPodania;


    public enum SposobPodania{WLC, BOL, WLK}
}
