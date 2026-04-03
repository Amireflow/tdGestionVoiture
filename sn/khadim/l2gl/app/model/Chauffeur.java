package sn.Khadim.l2gl.app.model;

public class Chauffeur{
    private int id;
    private String prenom;
    private String nom;
    private Etat etat;

    public Chauffeur(int id, String prenom, String nom, Etat etat, Permis permis) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.etat = etat;
        this.permis = permis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public Permis getPermis() {
        return permis;
    }

    public void setPermis(Permis permis) {
        this.permis = permis;
    }

    private Permis permis;
}
