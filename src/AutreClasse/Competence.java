package AutreClasse;

import javafx.scene.control.CheckBox;

import java.awt.*;

public class Competence {
    String Competence;
    int idCompetence;

    public Competence(String Competence) {
        this.Competence = Competence;
        this.idCompetence = idCompetence;
    }

    public String getCompetence() {
        return Competence;
    }

    public void setCompetence(String Competence) {
        this.Competence = Competence;
    }
    public int getIdCompetence() {
        return idCompetence;
    }

    public void setIdCompetence(int idCompetence) {
        this.idCompetence = idCompetence;
    }
}
