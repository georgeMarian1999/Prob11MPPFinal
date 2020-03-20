package domain.Repositories;

import domain.Models.Participant;

public interface ParticipantRepository extends CRUDRepository<Integer, Participant> {
    int findIdByName(String nume);
    int findMaxId();
}
