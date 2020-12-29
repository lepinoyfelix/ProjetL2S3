package AutreClasse;

public class Entreprise {

    int Num_SIREN, Code_Postal, fax,tel,DateVErsementTaxeAprentissage,Taxe_Apprentissage;
    String Raison_Sociale, ville, adresse, Site_Web, Autre_Info, technologie;


    public Entreprise(String Raison_Sociale, int Num_SIREN, int Code_Postal,String adresse, String ville,  int fax, int tel,String Site_Web, String Autre_Info, int DateVErsementTaxeAprentissage, int Taxe_Apprentissage, String technologie) {
        this.Raison_Sociale = Raison_Sociale;
        this.Num_SIREN = Num_SIREN;
        this.Code_Postal = Code_Postal;
        this.ville = ville;
        this.adresse = adresse;
        this.fax = fax;
        this.tel = tel;
        this.Site_Web = Site_Web;
        this.Autre_Info = Autre_Info;
        this.DateVErsementTaxeAprentissage = DateVErsementTaxeAprentissage;
        this.Taxe_Apprentissage = Taxe_Apprentissage;
        this.technologie = technologie;
    }

    public int getNum_SIREN() {
        return Num_SIREN;
    }

    public void setNum_SIREN(int numSiren) {
        this.Num_SIREN = Num_SIREN;
    }

    public int getCode_Postal() {
        return Code_Postal;
    }

    public void setCode_Postal(int Code_Postal) {
        this.Code_Postal = Code_Postal;
    }

    public int getFax() {
        return fax;
    }

    public void setFax(int fax) {
        this.fax = fax;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public int getDateVErsementTaxeAprentissage() {
        return DateVErsementTaxeAprentissage;
    }

    public void setDateVErsementTaxeAprentissage(int DateVErsementTaxeAprentissage) {
        this.DateVErsementTaxeAprentissage = DateVErsementTaxeAprentissage;
    }

    public int getTaxe_Apprentissage() {
        return Taxe_Apprentissage;
    }

    public void setTaxe_Apprentissage(int Taxe_Apprentissage) {
        this.Taxe_Apprentissage = Taxe_Apprentissage;
    }

    public String getRaison_Sociale() {
        return Raison_Sociale;
    }

    public void setRaison_Sociale(String Raison_Sociale) {
        this.Raison_Sociale = Raison_Sociale;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getSite_Web() {
        return Site_Web;
    }

    public void setSite_Web(String siteWeb) {
        this.Site_Web = Site_Web;
    }

    public String getAutre_Info() {
        return Autre_Info;
    }

    public void setAutre_Info(String autreInfo) {
        this.Autre_Info = Autre_Info;
    }

    public String getTechnologie(){ return technologie;}

    public void setTechnologie(String technologie) { this.technologie = technologie; }

}
