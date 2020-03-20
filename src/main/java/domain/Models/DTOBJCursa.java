package domain.Models;

public class DTOBJCursa implements Entity<Integer> {
    private Integer idCursa;
    private int capacitate;
    private int Nrinscrisi;

    public DTOBJCursa(int idcursa,int cap,int nr){
        this.idCursa=idcursa;
        this.capacitate=cap;
        this.Nrinscrisi=nr;
    }
    @Override
    public Integer getId() {
        return idCursa;
    }

    @Override
    public void setID(Integer integer) {
        this.idCursa=integer;
    }

    public int getCapacitate() {
        return capacitate;
    }

    public void setCapacitate(int capacitate) {
        this.capacitate = capacitate;
    }

    public int getNrinscrisi() {
        return Nrinscrisi;
    }

    public void setNrinscrisi(int nrinscrisi) {
        Nrinscrisi = nrinscrisi;
    }
}
