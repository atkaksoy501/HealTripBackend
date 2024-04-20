package codewizards.heal_trip.business.abstracts;

import codewizards.heal_trip.business.DTOs.requests.retreat.AddRetreatRequest;
import codewizards.heal_trip.business.DTOs.responses.retreat.AddedRetreatResponse;
import codewizards.heal_trip.business.DTOs.responses.retreat.GetRetreatByIdResponse;
import codewizards.heal_trip.business.DTOs.responses.retreat.GotRetreatByDepartmentIdResponse;
import codewizards.heal_trip.entities.Retreat;

import java.util.List;

public interface IRetreatService {

    GetRetreatByIdResponse getRetreatById(int retreat_id);

    AddedRetreatResponse addRetreat(AddRetreatRequest retreat);

    boolean deleteRetreat(int retreat_id);

    Retreat updateRetreat(Retreat retreat, int retreat_id);

    Iterable<Retreat> getAllRetreats();

    List<GotRetreatByDepartmentIdResponse> getRetreatsByDepartmentId(int departmentId);
}
