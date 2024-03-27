package codewizards.heal_trip.business.abstracts;

import codewizards.heal_trip.business.DTOs.responses.GotRetreatByDepartmentIdResponse;
import codewizards.heal_trip.entities.Retreat;

import java.util.List;

public interface IRetreatService {

    Retreat getRetreatById(int retreat_id);

    int addRetreat(Retreat retreat);

    boolean deleteRetreat(int retreat_id);

    Retreat updateRetreat(Retreat retreat, int retreat_id);

    Iterable<Retreat> getAllRetreats();

    List<GotRetreatByDepartmentIdResponse> getRetreatsByDepartmentId(int departmentId);
}
