package domain.Repositories;

import domain.Models.Cursa;

public interface CursaRepository extends CRUDRepository<Integer, Cursa> {
    Iterable<Cursa> filterByCapacitate(Integer capacitate);
}
