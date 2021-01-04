package AutreClasse;

public class EntrepriseEvenement {
String NomEvenement,  RAISON_SOCIALE;

    public EntrepriseEvenement(String nomEvenement, String RAISON_SOCIALE) {
        this.NomEvenement = nomEvenement;
        this.RAISON_SOCIALE = RAISON_SOCIALE;
    }

    public String getNomEvenement() {
        return NomEvenement;
    }

    public void setNomEvenement(String nomEvenement) {
        this.NomEvenement = nomEvenement;
    }

    public String getRAISON_SOCIALE() {
        return RAISON_SOCIALE;
    }

    public void setRAISON_SOCIALE(String RAISON_SOCIALE) {
        this.RAISON_SOCIALE = RAISON_SOCIALE;
    }
}
