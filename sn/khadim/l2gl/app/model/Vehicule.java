package sn.Khadim.l2gl.app.model;

import javax.xml.crypto.Data;
import java.util.Date;

public class Vehicule {
    private String immatriculation;
    private String marque;
    private Type type;
    private Etat etat;
    private Date dateF;


    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public Date getDateF() {
        return dateF;
    }

    public void setDateF(Date dateF) {
        this.dateF = dateF;
    }

    public Vehicule(String immatriculation, String marque, Type type, Etat etat, Date dateF) {
        this.immatriculation = immatriculation;
        this.marque = marque;
        this.type = type;
        this.etat = etat;
        this.dateF = dateF;
    }
}
