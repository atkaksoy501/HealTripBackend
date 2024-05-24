package codewizards.heal_trip.business.DTOs.converters;

import codewizards.heal_trip.business.DTOs.requests.department.AddDepartmentRequest;
import codewizards.heal_trip.business.DTOs.requests.department.UpdateDepartmentRequest;
import codewizards.heal_trip.business.DTOs.responses.hospital.GotHospitalByIdResponse;
import codewizards.heal_trip.business.DTOs.responses.retreat.GetRetreatByIdResponse;
import codewizards.heal_trip.business.abstracts.IHospitalService;
import codewizards.heal_trip.business.abstracts.IRetreatService;
import codewizards.heal_trip.core.utilities.mapping.ModelMapperService;
import codewizards.heal_trip.dataAccess.HospitalDepartmentDao;
import codewizards.heal_trip.entities.*;
import jakarta.persistence.EntityManager;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DepartmentDbDtoConverter{
    private final ModelMapperService modelMapperService;
    private final IHospitalService hospitalService;
    private IRetreatService retreatService;
    private final HospitalDepartmentDao hospitalDepartmentDao;
    @Autowired
    private EntityManager entityManager;

    public DepartmentDbDtoConverter(ModelMapperService modelMapperService, IHospitalService hospitalService, HospitalDepartmentDao hospitalDepartmentDao) {
        this.modelMapperService = modelMapperService;
        this.hospitalService = hospitalService;
        this.hospitalDepartmentDao = hospitalDepartmentDao;
    }

    @Autowired
    @Lazy
    public void setRetreatService(IRetreatService retreatService) {
        this.retreatService = retreatService;
    }

    @Transactional
    public Department toDbObj(@NotNull AddDepartmentRequest request) {

        Department dbDepartment = new Department();
        dbDepartment.setDepartmentName(request.getDepartmentName());
        if (request.getHospital_ids() != null) {
            dbDepartment.setHospitals(request.getHospital_ids().stream().map(hospitalId -> {
                GotHospitalByIdResponse hospital = hospitalService.getHospitalById(hospitalId);
                HospitalDepartment hospitalDepartment = new HospitalDepartment();
                hospitalDepartment.setHospital(modelMapperService.forRequest().map(hospital, Hospital.class));
                hospitalDepartment.setDepartment(dbDepartment);
                hospitalDepartment.setCreateDate(LocalDateTime.now());
                return hospitalDepartment;
            }).toList());
        }

        if (request.getRetreat_ids() != null) {
            dbDepartment.setRetreats(request.getRetreat_ids().stream().map(retreatId -> {
                GetRetreatByIdResponse retreat = retreatService.getRetreatById(retreatId);
                return modelMapperService.forRequest().map(retreat, Retreat.class);
            }).toList());
        }

        return dbDepartment;
    }

    @Transactional
    public void toDbObj(Department dbDepartment, UpdateDepartmentRequest request) {
        dbDepartment.setUpdateDate(LocalDateTime.now());
        dbDepartment.setDepartmentName(request.getDepartmentName());
        if (request.getHospital_ids() != null) {
            hospitalDepartmentDao.deleteAllByDepartmentId(dbDepartment.getId());
            dbDepartment.setHospitals(request.getHospital_ids().stream().map(hospitalId -> {
                GotHospitalByIdResponse hospital = hospitalService.getHospitalById(hospitalId);
                HospitalDepartment hospitalDepartment = new HospitalDepartment();
                hospitalDepartment.setHospital(modelMapperService.forRequest().map(hospital, Hospital.class));
                hospitalDepartment.setDepartment(dbDepartment);
                hospitalDepartment.setCreateDate(LocalDateTime.now());
                return hospitalDepartment;
            }).toList());
        }

        if (request.getRetreat_ids() != null) {
            dbDepartment.setRetreats(request.getRetreat_ids().stream().map(retreatId -> {
                GetRetreatByIdResponse retreat = retreatService.getRetreatById(retreatId);
                return modelMapperService.forRequest().map(retreat, Retreat.class);
            }).toList());
        }

    }
}
