package domain.Models;

public class DTOBJPartCapa  {
    private String nume;
    private int capactiate;

    public DTOBJPartCapa(String Nume,int cap){
        this.nume=Nume;
        this.capactiate=cap;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getCapactiate() {
        return capactiate;
    }

    public void setCapactiate(int capactiate) {
        this.capactiate = capactiate;
    }
}
