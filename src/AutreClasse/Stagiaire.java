package AutreClasse;

public class Stagiaire {
    String Raison_Sociale, NomPrenom,niveau,noteDeStage;

    public Stagiaire(String Raison_Sociale, String NomPrenom, String niveau, String noteDeStage) {
        this.Raison_Sociale = Raison_Sociale;
        this.NomPrenom = NomPrenom;
        this.niveau = niveau;
        this.noteDeStage = noteDeStage;
    }

    public String getRaison_Sociale() {
        return Raison_Sociale;
    }

    public void setRaison_Sociale(String Raison_Sociale) {
        this.Raison_Sociale = Raison_Sociale;
    }

    public String getNomPrenom() {
        return NomPrenom;
    }

    public void setNomPrenom(String NomPrenom) {
        this.NomPrenom = NomPrenom;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public String getNoteDeStage() {
        return noteDeStage;
    }

    public void setNoteDeStage(String noteDeStage) {
        this.noteDeStage = noteDeStage;
    }


}
