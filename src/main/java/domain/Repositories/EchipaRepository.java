package domain.Repositories;

import domain.Models.DTOBJPartCapa;
import domain.Models.Echipa;

import java.util.Vector;

public interface EchipaRepository extends CRUDRepository<Integer, Echipa> {
    Iterable<DTOBJPartCapa> cautare(String numeEchipa);
    int FindidByName(String numeEchipa);
}
