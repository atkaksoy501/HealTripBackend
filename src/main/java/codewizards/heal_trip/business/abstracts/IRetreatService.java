package codewizards.heal_trip.business.abstracts;

import codewizards.heal_trip.business.DTOs.requests.retreat.AddRetreatRequest;
import codewizards.heal_trip.business.DTOs.requests.retreat.GetRetreatByNameRequest;
import codewizards.heal_trip.business.DTOs.requests.retreat.UpdateRetreatRequest;
import codewizards.heal_trip.business.DTOs.responses.retreat.*;
import codewizards.heal_trip.entities.Retreat;

import java.util.List;

public interface IRetreatService {

    GetRetreatByIdResponse getRetreatById(int retreat_id);

    AddedRetreatResponse addRetreat(AddRetreatRequest retreat);

    boolean deleteRetreat(int retreat_id);

    UpdatedRetreatResponse updateRetreat(UpdateRetreatRequest retreat, int retreat_id);

    Iterable<GetAllRetreatsResponse> getAllRetreats();

    List<GotRetreatByDepartmentIdResponse> getRetreatsByDepartmentId(int departmentId);

    List<GetRetreatByHospitalIdResponse> getRetreatsByHospitalId(int hospitalId);

    List<GetRetreatByNameResponse> getRetreatByName(GetRetreatByNameRequest retreatNames);
}
