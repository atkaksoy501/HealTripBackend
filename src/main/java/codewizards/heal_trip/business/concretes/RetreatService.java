package codewizards.heal_trip.business.concretes;

import codewizards.heal_trip.business.DTOs.requests.retreat.AddRetreatRequest;
import codewizards.heal_trip.business.DTOs.requests.retreat.UpdateRetreatRequest;
import codewizards.heal_trip.business.DTOs.responses.images.GetImageResponse;
import codewizards.heal_trip.business.DTOs.responses.retreat.*;
import codewizards.heal_trip.business.abstracts.IDepartmentService;
import codewizards.heal_trip.business.abstracts.IImageService;
import codewizards.heal_trip.business.abstracts.IRetreatService;
import codewizards.heal_trip.core.utilities.mapping.ModelMapperService;
import codewizards.heal_trip.dataAccess.RetreatDao;
import codewizards.heal_trip.entities.Department;
import codewizards.heal_trip.entities.Retreat;
import codewizards.heal_trip.entities.RetreatImage;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RetreatService implements IRetreatService {

    private RetreatDao retreatDao;
    private ModelMapperService modelMapperService;
    private IImageService imageService;
    private IDepartmentService departmentService;


    public GetRetreatByIdResponse getRetreatById(int retreat_id) {
        Retreat retreat = retreatDao.findById(retreat_id).orElse(null);
        return modelMapperService.forResponse().map(retreat, GetRetreatByIdResponse.class);
    }

//    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public AddedRetreatResponse addRetreat(AddRetreatRequest retreat) {
        Retreat dbRetreat = new Retreat();
        dbRetreat.setDescription(retreat.getDescription());
        dbRetreat.setRetreat_name(retreat.getRetreat_name());

        RetreatImage image = imageService.getRetreatImageById(retreat.getImageId());
        dbRetreat.setImage(image);

        Department department = departmentService.getById(retreat.getDepartmentId());
        dbRetreat.setDepartment(department);

        dbRetreat.setCreateDate(LocalDateTime.now());
        dbRetreat = retreatDao.save(dbRetreat);
        AddedRetreatResponse response = modelMapperService.forResponse().map(dbRetreat, AddedRetreatResponse.class);
        return response;
    }

    public boolean deleteRetreat(int retreat_id) {
        retreatDao.deleteById(retreat_id);
        Retreat retreat = retreatDao.findById(retreat_id).orElse(null);
        return retreat == null;
    }

    public UpdatedRetreatResponse updateRetreat(UpdateRetreatRequest retreat, int retreat_id) {
        Retreat dbRetreat = retreatDao.findById(retreat_id).orElse(null);
        if (dbRetreat != null) {
            if (retreat.getRetreat_name() != null)
                dbRetreat.setRetreat_name(retreat.getRetreat_name());
            if (retreat.getDescription() != null)
                dbRetreat.setDescription(retreat.getDescription());
            if (retreat.getImageId() != 0) {
                dbRetreat.setImage(imageService.getRetreatImageById(retreat.getImageId()));
            }
            if (retreat.getDepartmentId() != 0) {
                dbRetreat.setDepartment(departmentService.getById(retreat.getDepartmentId()));
            }
            dbRetreat.setUpdateDate(LocalDateTime.now());
            dbRetreat = retreatDao.save(dbRetreat);
        }
        return modelMapperService.forResponse().map(dbRetreat, UpdatedRetreatResponse.class);
    }

    public Iterable<GetAllRetreatsResponse> getAllRetreats() {
        List<Retreat> retreats =  retreatDao.findAll();
        return retreats.stream().map(retreat -> modelMapperService.forResponse().map(retreat, GetAllRetreatsResponse.class)).toList();
    }

    // get retreats by department id
    public List<GotRetreatByDepartmentIdResponse> getRetreatsByDepartmentId(int departmentId) {
        List<Retreat> retreats = retreatDao.findByDepartmentId(departmentId);
        List<GotRetreatByDepartmentIdResponse> response = new ArrayList<>();
        for (Retreat retreat : retreats) {
            response.add(new GotRetreatByDepartmentIdResponse(retreat.getId(), retreat.getRetreat_name(), retreat.getDescription(),
                    modelMapperService.forResponse().map(retreat.getImage(), GetImageResponse.class)));
        }
        return response;
    }
}
