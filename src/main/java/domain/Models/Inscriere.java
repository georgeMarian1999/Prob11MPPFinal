package domain.Models;

public class Inscriere {
    private Integer idParticipant;
    private Integer idCursa;

    public Inscriere(Integer idParticipant,Integer idCursa){
        this.idParticipant=idParticipant;
        this.idCursa=idCursa;
    }

    public Integer getIdParticipant() {
        return idParticipant;
    }

    public Integer getIdCursa() {
        return idCursa;
    }

    public void setIdParticipant(Integer idParticipant) {
        this.idParticipant = idParticipant;
    }

    public void setIdCursa(Integer idCursa) {
        this.idCursa = idCursa;
    }
}
