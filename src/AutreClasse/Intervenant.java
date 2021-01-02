package AutreClasse;

public class Intervenant {

    String NomPersone;
    int idPersone;

    public Intervenant ( String NomPersone) {
        this.NomPersone = NomPersone;
        this.idPersone = idPersone;
    }

    public String getNomPersone() {
        return NomPersone;
    }

    public void setNomPersone(String NomPersone) {
        this.NomPersone = NomPersone;
    }

    public int getIdPersone() {
        return idPersone;
    }

    public void setIdPersone(int idPersone) {
        this.idPersone = idPersone;
    }

}
