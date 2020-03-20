package domain.Repositories;

import domain.Models.DTOBJPartCapa;
import domain.Models.Echipa;

import java.util.Vector;

public interface EchipaRepository extends CRUDRepository<Integer, Echipa> {
    Vector<DTOBJPartCapa> cautare(String numeEchipa);
}
