package AutreClasse;

public class Cours {
    String Salle, Batiment, DureeCours;

    public Cours(String salle, String batiment, String dureeCours) {
        this.Salle = salle;
        this.Batiment = Batiment;
        this.DureeCours = dureeCours;
    }

    public String getSalle() {
        return Salle;
    }

    public void setSalle(String salle) {
        this.Salle = salle;
    }

    public String getBatiment() {
        return Batiment;
    }

    public void setBatiment(String Batiment) {
        this.Batiment = Batiment;
    }

    public String getDureeCours() {
        return DureeCours;
    }

    public void setDureeCours(String dureeCours) {
        this.DureeCours = dureeCours;
    }
}
