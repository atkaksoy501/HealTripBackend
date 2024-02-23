package codewizards.heal_trip.business;

import codewizards.heal_trip.entities.Retreat;

public interface IRetreatService {

    Retreat getRetreatById(int retreat_id);

    int addRetreat(Retreat retreat);

    boolean deleteRetreat(int retreat_id);

    Retreat updateRetreat(Retreat retreat, int retreat_id);
}
