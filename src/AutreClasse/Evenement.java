package AutreClasse;

import javax.transaction.HeuristicCommitException;
import java.util.Date;

public class Evenement {
    String NomEvenement,CodePostal, Ville, Adresse, Date, Heure,NomPersone, Salle, Batiment, DureeCours,DureeConference,DescriptionAutreEvenement, TypeEvenement, Competence  ;

    public Evenement(String NomEvenement, String CodePostal, String Ville,String Adresse, String Date, String Heure, String NomPersone, String salle, String Batiment, String dureeCours, String DureeConference, String DescriptionAutreEvenement, String TypeEvenement, String Competence) {
        this.NomEvenement = NomEvenement;
        this.CodePostal = CodePostal;
        this.Ville = Ville;
        this.Adresse=Adresse;
        this.Date = Date;
        this.Heure = Heure;
        this.NomPersone = NomPersone;
        this.Salle = salle;
        this.Batiment = Batiment;
        this.DureeCours = dureeCours;
        this.DureeConference =  DureeConference;
        this.DescriptionAutreEvenement=DescriptionAutreEvenement;
        this.TypeEvenement = TypeEvenement;
        this.Competence = Competence;
    }

    public String getNomEvenement() {
        return NomEvenement;
    }

    public void setNomEvenement(String NomEvenement) {
        this.NomEvenement = NomEvenement;
    }

    public String getCodePostal() {
        return CodePostal;
    }

    public void setCodePostal(String CodePostal) {
        this.CodePostal = CodePostal;
    }

    public String getVille() {
        return Ville;
    }

    public void setVille(String Ville) {
        this.Ville = Ville;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String Adresse) {
        this.Adresse = Adresse;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getHeure() {
        return Heure;
    }

    public void setHeure(String Heure) {
        this.Heure = Heure;
    }
    public String getNomPersone() {
        return NomPersone;
    }

    public void setNomPersone(String NomPersone) {
        this.NomPersone = NomPersone;
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

    public String getDureeConference() {
        return DureeConference;
    }

    public void setDureeConference(String DureeConference) {
        this.DureeConference = DureeConference;
    }

    public String getDescriptionAutreEvenement() {
        return DescriptionAutreEvenement;
    }

    public void setDescriptionAutreEvenement(String DescriptionAutreEvenement) {
        this.DescriptionAutreEvenement = DescriptionAutreEvenement;
    }

    public String getTypeEvenement() {
        return TypeEvenement;
    }

    public void setTypeEvenement(String TypeEvenement) {
        this.TypeEvenement = TypeEvenement;
    }

    public String getCompetence() {
        return Competence;
    }

    public void setCompetence(String Competence) {
        this.Competence = Competence;
    }

}
