package pl.michal.clinical_nutrition.calculator.dto;

import lombok.Data;

@Data
public class WorekPreparatDTO {
    private long id;
    private String producent;	//Producent
    private String nazwa;	//Nazwa
    private double objetosc;	//Objętość
    private double aa;	//AA g
    private double azot;	//Azot g
    private double weglowodany;	//Węglowodany  (g)
    private double tluszcze;	//Tłuszcze g
    private double wartoscEnergetycznaCalkowita;	//Wartość energetyczna całkowita (kcal)
    private double wartoscEnergetycznaPozabialkowa;	//Wartość energetyczna pozabiałkowa (kcal)
    private double sod;	//Na (mmol)
    private double potas;	//K (mmol)
    private double magnez;	//Mg (mmol)
    private double wapn;	//Ca (mmol)
    private double fosforany;	//Fosforany(mmol)
    private double chlor;	//Cl (mmol)
    private double cynk;	//Cynk (mmol)
    private double octany;	//Octany
    private String sposobPodania;	//Sposób podania
    private double dipeptiven;	//Dipeptiven (ml)
    private double omegaven;	//Omegaven (ml)
    private double addamel;	//Addamel (ml)
    private double vitalipid;	//Vitalipid N Adult (ml)
    private double soluvit;	//Soluvit N (ml)
    private double sodMax;	//Na (mmol) MAX
    private double potasMax;	//K (mmol) MAX
    private double magnezMax;	//Mg (mmol) MAX
    private double wapnMax;	//Ca (mmol) MAX
    private double fosforanyMax;	//Fosforany(mmol) MAX
    private double chlorMax;	//Cl (mmol) MAX
    private double cynkMax;	//Cynk (mmol) MAX
    private double sodPotasMax;	//Na+K mmol MAX
    private double intralipid;	//Intralipid 20%/Smoflipid (Max ml)
    private boolean czyAktywny;
}
