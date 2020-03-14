package domain.Repositories;

import domain.Models.Angajat;

public interface AngajatRepository extends CRUDRepository<Integer, Angajat> {
    Iterable<Angajat> filterBy(String username);
}
