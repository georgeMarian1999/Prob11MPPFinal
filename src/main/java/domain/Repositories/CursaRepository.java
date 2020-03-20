package domain.Repositories;

import domain.Models.Cursa;
import domain.Models.DTOBJCursa;

import java.util.Vector;

public interface CursaRepository extends CRUDRepository<Integer, Cursa> {
    Vector<DTOBJCursa> GroupByCapacitate();
    int findIdByCapacitate(int capacitate);
}
