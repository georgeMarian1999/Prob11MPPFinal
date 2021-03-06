package domain.Repositories;

import domain.Models.Cursa;
import domain.Models.DTOBJCursa;

import java.util.Vector;

public interface CursaRepository extends CRUDRepository<Integer, Cursa> {
    Iterable<DTOBJCursa> GroupByCapacitate();
    int findIdByCapacitate(int capacitate);
}
