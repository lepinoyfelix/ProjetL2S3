package AutreClasse;

public class Entreprise {

    int Num_SIREN, Code_Postal, idEntreprise;
    String Raison_Sociale, ville, adresse, Site_Web, Autre_Info, Competence, Taxe_Apprentissage,DateVersementTaxeAprentissage,tel,fax;


    public Entreprise(String Raison_Sociale, int Num_SIREN, int Code_Postal,String adresse, String ville,  String fax, String tel,String Site_Web, String Autre_Info,  String Taxe_Apprentissage,String DateVersementTaxeAprentissage, String Competence, int idEntreprise) {
        this.Raison_Sociale = Raison_Sociale;
        this.Num_SIREN = Num_SIREN;
        this.Code_Postal = Code_Postal;
        this.ville = ville;
        this.adresse = adresse;
        this.fax = fax;
        this.tel = tel;
        this.Site_Web = Site_Web;
        this.Autre_Info = Autre_Info;
        this.Taxe_Apprentissage = Taxe_Apprentissage;
        this.DateVersementTaxeAprentissage = DateVersementTaxeAprentissage;
        this.Competence = Competence;
        this.idEntreprise = idEntreprise;
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

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTaxe_Apprentissage() {
        return Taxe_Apprentissage;
    }

    public void setTaxe_Apprentissage(String Taxe_Apprentissage) {
        this.Taxe_Apprentissage = Taxe_Apprentissage;
    }

    public String getDateVersementTaxeAprentissage() {
        return DateVersementTaxeAprentissage;
    }

    public void setDateVersementTaxeAprentissage(String DateVersementTaxeAprentissage) {
        this.DateVersementTaxeAprentissage = DateVersementTaxeAprentissage;
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

    public String getCompetence(){ return Competence;}

    public void setCompetence(String Competence) { this.Competence = Competence; }

    public int getIdEntreprise() {
        return idEntreprise;
    }

    public void setIdEntreprise(int idEntreprise) {
        this.idEntreprise = idEntreprise;
    }
}
