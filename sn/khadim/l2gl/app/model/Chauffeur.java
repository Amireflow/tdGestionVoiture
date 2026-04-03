package sn.khadim.l2gl.app.model;

import java.util.Calendar;
import java.util.Objects;

public class Chauffeur {
    private final int id;
    private String prenom;
    private String nom;
    private Calendar dateNaissance;
    private Permis permis;
    private Etat etat;

    public Chauffeur(int id, String prenom, String nom, Calendar dateNaissance, Permis permis, Etat etat) {
        this.id = id;
        this.prenom = Objects.requireNonNull(prenom, "prenom");
        this.nom = Objects.requireNonNull(nom, "nom");
        setDateNaissance(dateNaissance);
        this.permis = Objects.requireNonNull(permis, "permis");
        this.etat = Objects.requireNonNull(etat, "etat");
    }

    public int getId() {
        return id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = Objects.requireNonNull(prenom, "prenom");
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = Objects.requireNonNull(nom, "nom");
    }

    public Calendar getDateNaissance() {
        return (Calendar) dateNaissance.clone();
    }

    public void setDateNaissance(Calendar dateNaissance) {
        this.dateNaissance = (Calendar) Objects.requireNonNull(dateNaissance, "dateNaissance").clone();
    }

    public Permis getPermis() {
        return permis;
    }

    public void setPermis(Permis permis) {
        this.permis = Objects.requireNonNull(permis, "permis");
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = Objects.requireNonNull(etat, "etat");
    }

    public int getAge() {
        return DateUtils.getAge(dateNaissance);
    }

    public boolean estDisponible() {
        return etat == Etat.DISPO;
    }

    public boolean doitPartirALaRetraite(int ageRetraite) {
        return getAge() >= ageRetraite;
    }

    public String getNomComplet() {
        return prenom + " " + nom;
    }

    @Override
    public String toString() {
        return "Chauffeur{id="
                + id
                + ", nom='"
                + getNomComplet()
                + "', age="
                + getAge()
                + ", permis="
                + permis
                + ", etat="
                + etat
                + "}";
    }
}
