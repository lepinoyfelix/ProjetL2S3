package AutreClasse;

public class Conference {
    String NomEvenement, Ville, Adresse,Date, Heure;

    public Conference(String NomEvenement, String Ville, String Adresse, String Date, String Heure) {
        this.NomEvenement = NomEvenement;
        this.Ville = Ville;
        this.Adresse = Adresse;
        this.Date = Date;
        this.Heure = Heure;
    }

    public String getNomEvenement() {
        return NomEvenement;
    }

    public void setNomEvenement(String nomEvenement) {
        this.NomEvenement = nomEvenement;
    }

    public String getVille() {
        return Ville;
    }

    public void setVille(String ville) {
        this.Ville = ville;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String adresse) {
        this.Adresse = adresse;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        this.Date = date;
    }

    public String getHeure() {
        return Heure;
    }

    public void setHeure(String heure) {
        Heure = heure;
    }
}
