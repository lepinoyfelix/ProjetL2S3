package AutreClasse;

public class PersonneNomPrenom {
    String NomPersone,PrenomPersone,TelPersone,EmailPersonne,Raison_Sociale,Competence;

    public PersonneNomPrenom(String NomPersone, String PrenomPersone, String TelPersone, String EmailPersonne, String Raison_Sociale, String Competence) {
        this.NomPersone = NomPersone;
        this.PrenomPersone = PrenomPersone;
        this.TelPersone = TelPersone;
        this.EmailPersonne = EmailPersonne;
        this.Raison_Sociale = Raison_Sociale;
        this.Competence = Competence;
    }

    public String getNomPersone() {
        return NomPersone;
    }

    public void setNomPersone(String NomPersone) {
        this.NomPersone = NomPersone;
    }

    public String getPrenomPersone() {
        return PrenomPersone;
    }

    public void setPrenomPersone(String PrenomPersone) {
        this.PrenomPersone = PrenomPersone;
    }

    public String getTelPersone() {
        return TelPersone;
    }

    public void setTelPersone(String TelPersone) {
        this.TelPersone = TelPersone;
    }

    public String getEmailPersonne() {
        return EmailPersonne;
    }

    public void setEmailPersonne(String EmailPersonne) {
        this.EmailPersonne = EmailPersonne;
    }

    public String getRaison_Sociale() { return Raison_Sociale; }

    public void setRaison_Sociale(String Raison_Sociale) {
        this.Raison_Sociale = Raison_Sociale;
    }

    public String getCompetence() { return Competence; }

    public void setCompetence(String Competence) {
        this.Competence = Competence;
    }
}
