package AutreClasse;

public class TypeEvenement {
    String typeEvenement;
    int idtypeEvenement;

    public TypeEvenement( String typeEvenement) {
        this.typeEvenement = typeEvenement;
        this.idtypeEvenement = idtypeEvenement;
    }

    public String getTypeEvenement() {
        return typeEvenement;
    }

    public void setTypeEvenement(String typeEvenement) {
        this.typeEvenement = typeEvenement;
    }

    public int getIdtypeEvenement() {
        return idtypeEvenement;
    }

    public void setIdtypeEvenement(int idtypeEvenement) {
        this.idtypeEvenement = idtypeEvenement;
    }
}