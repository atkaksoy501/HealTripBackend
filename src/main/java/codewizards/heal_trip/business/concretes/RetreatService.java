package codewizards.heal_trip.business.concretes;

import codewizards.heal_trip.business.DTOs.requests.retreat.AddRetreatRequest;
import codewizards.heal_trip.business.DTOs.requests.retreat.UpdateRetreatRequest;
import codewizards.heal_trip.business.DTOs.responses.department.DepartmentForRetreatResponse;
import codewizards.heal_trip.business.DTOs.responses.doctor.DoctorForDepartmentResponse;
import codewizards.heal_trip.business.DTOs.responses.hospital.HospitalForDepartmentResponse;
import codewizards.heal_trip.business.DTOs.responses.images.GetImageResponse;
import codewizards.heal_trip.business.DTOs.responses.retreat.*;
import codewizards.heal_trip.business.abstracts.IDepartmentService;
import codewizards.heal_trip.business.abstracts.IImageService;
import codewizards.heal_trip.business.abstracts.IRetreatService;
import codewizards.heal_trip.business.rules.RetreatBusinessRules;
import codewizards.heal_trip.core.converter.Base64ToByteConverter;
import codewizards.heal_trip.core.converter.ByteToBase64Converter;
import codewizards.heal_trip.core.utilities.mapping.ModelMapperService;
import codewizards.heal_trip.dataAccess.RetreatDao;
import codewizards.heal_trip.entities.Department;
import codewizards.heal_trip.entities.Hospital;
import codewizards.heal_trip.entities.Retreat;
import codewizards.heal_trip.entities.RetreatImage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RetreatService implements IRetreatService {

    private final RetreatDao retreatDao;
    private final ModelMapperService modelMapperService;
    private final IImageService imageService;
    private final IDepartmentService departmentService;
    private final RetreatBusinessRules retreatBusinessRules;


    public GetRetreatByIdResponse getRetreatById(int retreat_id) {
        retreatBusinessRules.checkIfRetreatExists(retreat_id);
        Retreat retreat = retreatDao.findById(retreat_id).orElse(null);
        GetRetreatByIdResponse response = new GetRetreatByIdResponse();
        response.setId(retreat.getId());
        response.setRetreat_name(retreat.getRetreatName());
        response.setDescription(retreat.getDescription());
        response.setImage(modelMapperService.forResponse().map(retreat.getImage(), GetImageResponse.class));
        DepartmentForRetreatResponse department = new DepartmentForRetreatResponse();
        department.setId(retreat.getDepartment().getId());
        department.setDepartmentName(retreat.getDepartment().getDepartmentName());
        List<HospitalForDepartmentResponse> hospitals = retreat.getDepartment().getHospitals().stream()
                .filter(h -> h.getHospital().isActive())
                .map(h -> {
            HospitalForDepartmentResponse hospital = modelMapperService.forResponse().map(h.getHospital(), HospitalForDepartmentResponse.class);
            hospital.setId(h.getHospital().getId());
            hospital.setDoctors(h.getHospital().getDoctors().stream().map(d -> {
                DoctorForDepartmentResponse doctor = modelMapperService.forResponse().map(d, DoctorForDepartmentResponse.class);
                doctor.setDoctorImage(ByteToBase64Converter.convert(d.getDoctorImage()));
                return doctor;
            }).toList());
            return hospital;
        }).toList();
        department.setHospitals(hospitals);
        response.setDepartment(department);
        return response;
    }

    public AddedRetreatResponse addRetreat(AddRetreatRequest retreat) {
        Retreat dbRetreat = new Retreat();
        dbRetreat.setDescription(retreat.getDescription());
        dbRetreat.setRetreatName(retreat.getRetreat_name());

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
        retreatBusinessRules.checkIfRetreatExists(retreat_id);
        retreatDao.deleteById(retreat_id);
        Retreat retreat = retreatDao.findById(retreat_id).orElse(null);
        return retreat == null;
    }

    public UpdatedRetreatResponse updateRetreat(UpdateRetreatRequest retreat, int retreat_id) {
        retreatBusinessRules.checkIfRetreatExists(retreat_id);
        Retreat dbRetreat = retreatDao.findById(retreat_id).orElse(null);
        if (dbRetreat != null) {
            if (retreat.getRetreat_name() != null)
                dbRetreat.setRetreatName(retreat.getRetreat_name());
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
        retreatBusinessRules.checkIfRetreatsDepartmentExists(departmentId);
        List<Retreat> retreats = retreatDao.findByDepartmentId(departmentId);
        List<GotRetreatByDepartmentIdResponse> response = new ArrayList<>();
        for (Retreat retreat : retreats) {
            response.add(new GotRetreatByDepartmentIdResponse(retreat.getId(), retreat.getRetreatName(), retreat.getDescription(),
                    modelMapperService.forResponse().map(retreat.getImage(), GetImageResponse.class)));
        }
        return response;
    }

    @Override
    public List<GetRetreatByHospitalIdResponse> getRetreatsByHospitalId(int hospitalId) {
        retreatBusinessRules.checkIfRetreatsHospitalExists(hospitalId);
        List<Retreat> retreats = retreatDao.findByDepartmentHospitalsHospitalId(hospitalId);
        return retreats.stream().map(retreat -> modelMapperService.forResponse().map(retreat, GetRetreatByHospitalIdResponse.class)).toList();
    }

    @Override
    public GetRetreatByNameResponse getRetreatByName(String retreat_name) {
        Retreat retreat = retreatDao.findByRetreatNameIgnoreCase(retreat_name);
        GetRetreatByNameResponse response = modelMapperService.forResponse().map(retreat, GetRetreatByNameResponse.class);
        return response;
    }
}
