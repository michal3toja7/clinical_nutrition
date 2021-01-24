package pl.michal.clinical_nutrition.calculator.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "worek_preparat", schema = "app")
public class WorekPreparat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String producent;	//Producent
    @Column(nullable = false)
    private String nazwa;	//Nazwa
    @Column(nullable = false)
    private double objetosc;	//Objętość
    @Column(nullable = false)
    private double aa;	//AA g
    @Column(nullable = false)
    private double azot;	//Azot g
    @Column(nullable = false)
    private double weglowodany;	//Węglowodany  (g)
    @Column(nullable = false)
    private double tluszcze;	//Tłuszcze g
    @Column(nullable = false)
    private double wartoscEnergetycznaCalkowita;	//Wartość energetyczna całkowita (kcal)
    @Column(nullable = false)
    private double wartoscEnergetycznaPozabialkowa;	//Wartość energetyczna pozabiałkowa (kcal)
    @Column(nullable = false)
    private double sod;	//Na (mmol)
    @Column(nullable = false)
    private double potas;	//K (mmol)
    @Column(nullable = false)
    private double magnez;	//Mg (mmol)
    @Column(nullable = false)
    private double wapn;	//Ca (mmol)
    @Column(nullable = false)
    private double fosforany;	//Fosforany(mmol)
    @Column(nullable = false)
    private double chlor;	//Cl (mmol)
    @Column
    private double cynk;	//Cynk (mmol)
    @Column
    private double octany;	//Octany
    @Column(nullable = false)
    private String sposobPodania;	//Sposób podania
    @Column
    private double dipeptiven;	//Dipeptiven (ml)
    @Column
    private double omegaven;	//Omegaven (ml)
    @Column
    private double addamel;	//Addamel (ml)
    @Column
    private double vitalipid;	//Vitalipid N Adult (ml)
    @Column
    private double soluvit;	//Soluvit N (ml)
    @Column
    private double sodMax;	//Na (mmol) MAX
    @Column
    private double potasMax;	//K (mmol) MAX
    @Column
    private double magnezMax;	//Mg (mmol) MAX
    @Column
    private double wapnMax;	//Ca (mmol) MAX
    @Column
    private double fosforanyMax;	//Fosforany(mmol) MAX
    @Column
    private double chlorMax;	//Cl (mmol) MAX
    @Column
    private double cynkMax;	//Cynk (mmol) MAX
    @Column
    private double sodPotasMax;	//Na+K mmol MAX
    @Column
    private double intralipid;	//Intralipid 20%/Smoflipid (Max ml)
    @Column (name = "czy_aktywny", nullable = false)
    @Type(type = "yes_no")
    private boolean czyAktywny;
    @Column
    @CreationTimestamp
    private Date created_on;
    @Column
    @UpdateTimestamp
    private Date updated_on;

}
