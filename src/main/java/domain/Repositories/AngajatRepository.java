package domain.Repositories;

import domain.Models.Angajat;

public interface AngajatRepository extends CRUDRepository<Integer, Angajat> {
    boolean LocalLogin(String username,String Password);
}
