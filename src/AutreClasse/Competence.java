package AutreClasse;

import javafx.scene.control.CheckBox;

import java.awt.*;

public class Competence {
    String Technologie;
    private CheckBox select;

    public Competence(String Technologie) {
        this.Technologie = Technologie;
        this.select = new CheckBox();
    }

    public String getTechnologie() {
        return Technologie;
    }

    public void setTechnologie(String Technologie) {
        this.Technologie = Technologie;
    }
    public CheckBox getSelect() {
        return select;
    }

    public void setSelect(CheckBox select) {
        this.select = select;
    }
}
