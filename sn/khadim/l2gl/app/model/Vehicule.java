package sn.khadim.l2gl.app.model;

import java.util.Calendar;
import java.util.Objects;

public class Vehicule {
    private final String immatriculation;
    private String marque;
    private Type type;
    private Etat etat;
    private Calendar dateFabrication;

    public Vehicule(String immatriculation, String marque, Type type, Etat etat, Calendar dateFabrication) {
        this.immatriculation = Objects.requireNonNull(immatriculation, "immatriculation");
        this.marque = Objects.requireNonNull(marque, "marque");
        this.type = Objects.requireNonNull(type, "type");
        this.etat = Objects.requireNonNull(etat, "etat");
        setDateFabrication(dateFabrication);
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = Objects.requireNonNull(marque, "marque");
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = Objects.requireNonNull(type, "type");
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = Objects.requireNonNull(etat, "etat");
    }

    public Calendar getDateFabrication() {
        return (Calendar) dateFabrication.clone();
    }

    public void setDateFabrication(Calendar dateFabrication) {
        this.dateFabrication = (Calendar) Objects.requireNonNull(dateFabrication, "dateFabrication").clone();
    }

    public int getAge() {
        return DateUtils.getAge(dateFabrication);
    }

    public boolean estDisponible() {
        return etat == Etat.DISPO;
    }

    public boolean estAmorti() {
        return getAge() >= 5;
    }

    @Override
    public String toString() {
        return immatriculation
                + " - "
                + marque
                + " - "
                + type
                + " - "
                + etat
                + " - fabrication: "
                + DateUtils.format(dateFabrication);
    }
}
